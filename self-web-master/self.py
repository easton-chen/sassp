# -*- coding: utf-8 -*-
"""
    Self Adaption System Website
    Author: Xiao Ning
"""

import os
import sqlite3
from flask import Flask, request, session, g, redirect, url_for, abort, \
     render_template, flash
from werkzeug import secure_filename
from ResPool import client, res_manager, utils, Xutils
import json
from contextlib import closing

from flask_wtf import Form
from wtforms import StringField,SubmitField,PasswordField
from wtforms.validators import DataRequired

import pymysql

class MyForm(Form):
    user = StringField('Username', validators=[DataRequired()])
    psd = PasswordField('Password',validators=[DataRequired()])
    submit = SubmitField('submit')

# create our little application :)
UPLOAD_FOLDER = './static'
ALLOWED_EXTENSIONS = set(['json'])


app = Flask(__name__)
app.config['UPLOAD_FOLDER'] = UPLOAD_FOLDER

res_rule_id = 0
goal_list = []
software_list = []
all_res = []
all_property = []
goal_data = []
show_goal = []
show_software = []
show_property = []
show_environment = []
current_user = ''
file_path = {}
password = "******"

@app.route('/')
@app.route('/environment_main')
def view_environment_main():
    all_config_file = []

    if(current_user != ''):
        '''
        pymysql.connect(host,user,password,database)
        '''
        db = pymysql.connect("localhost", "root", password, "test")
        cursor = db.cursor()
        sql = "select * from user_config where username='"+current_user+"';"
        cursor.execute(sql)
        all_user_config = cursor.fetchall()
        #print all_config
        configID=[]
        for rows in all_user_config:
            configID.append(rows[1])
        sql="select * from config_file where configID in "+str(tuple(configID))+";"
        #print sql
        cursor.execute(sql)
        config_result=cursor.fetchall()
        for row in config_result:
            config_line ={}
            config_line['username'] = current_user
            config_line['configID'] = row[0]
            config_line['res_file'] = row[1]
            config_line['property_file'] = row[2]
            config_line['goal_file'] = row[3]
            config_line['software_file'] = row[4]
            all_config_file.append(config_line)
    return render_template('environment_main.html',config_file=all_config_file)

@app.route('/environment_list')
def view_environment_list():

    environment_name = request.args.get("environment_name",'default')
    return render_template('environment_list.html',environment_name=environment_name,show_goal=show_goal,show_environment=show_environment,show_software=show_software,show_property=show_property)

@app.route('/agent_choose')
def view_agent_choose():
    return render_template('agent_choose.html')

@app.route('/runtime_choose')
def view_choose():
    cur = g.db.execute('select distinct environment from entries')
    environments = [row for row in cur.fetchall()]
    entries = []
    for j in environments:
        i = j[0]
        print i
        num = g.db.execute('select count(*) from entries where environment = (?)', [i]).fetchall()[0][0]
        entries.append([i,num])
    return render_template('runtime_choose.html', entries=entries)

@app.route('/set_environment', methods=['POST'])
def op_set_environment():
    global res_list,goal_list,res_rule_id
    environment_name = request.form.get("environment_name",'default')
    cur = g.db.execute('select name, format, initial, delay, next, rule from entries where environment = (?) order by id desc', [environment_name])
    setting_res = [row for row in cur.fetchall()]
    for n, f, i, d, ni, r in setting_res:
        i = json.loads(i)
        model = {"format":f, "initial":i}
        update = {"delay":d, "next":ni, "rule":json.JSONDecoder().decode(r)}
        print "===========",n,model,update
        client.add_res(n,model,{'Default': utils.warp_update(update)})
        res_list.append(n)

    goal_list = filter(lambda x : 'goal' in x, res_list)
    for res in res_list:
        if 'rule_id' in res:
            res_rule_id = res
    return redirect(url_for('view_agent_choose'))

@app.route('/add_res_from_file',methods=['GET','POST'])
def op_add_res_from_file():
    file = request.files['res_file']
    if file and allowed_file(file.filename):
        filename = secure_filename(file.filename)
        filepath = os.path.join(app.config['UPLOAD_FOLDER'], filename)
        file.save(filepath)
        global file_path
        file_path['res_file'] = filepath
        res_list = Xutils.get_res_list_from_environment_json(filepath)
        for res in res_list:
            print res
            client.add_res(*res)
            all_res.append(res[0])
            show_environment.append({"label":res[0].encode()})
        print client.get_all_res_value(all_res,-1)
    return redirect(url_for('view_environment_list'))



@app.route('/add_property_from_file',methods=['POST'])
def op_add_property_from_file():
    file = request.files['property_file']
    if file and allowed_file(file.filename):
        filename = secure_filename(file.filename)
        filepath = os.path.join(app.config['UPLOAD_FOLDER'], filename)
        file.save(filepath)
        global file_path
        file_path['property_file'] = filepath
        property_list = Xutils.get_res_list_from_property_json(filepath)
        for res in property_list:
            client.add_res(*res)
            all_property.append(res[0])
            show_property.append({"label":res[0].encode()})
    return redirect(url_for('view_environment_list'))

def get_goal_list(data):
    for tmp in data["goal"]:
        goal_list.append("room:" + tmp["name"])
        if "goal" in tmp.keys():
            get_goal_list(tmp)

def get_show_goal(data):
    my_list = []
    for tmp in data["goal"]:
        new_one = dict()
        new_one["label"] = "room:" + tmp["name"].encode()
        if "goal" in tmp.keys():
            new_one["children"] = get_show_goal(tmp)
        if "related_property" in tmp.keys():
            new_one["children"] = []
            if type(tmp["related_property"])==list:
                for i in tmp["related_property"]:
                    new_one["children"].append({"label":i["name"].encode()})
            else:
                i = tmp["related_property"]
                new_one["children"].append({"label":i["name"].encode()})
        my_list.append(new_one)
    # czy add something
    #print "for understand"
    #print my_list
    return my_list


@app.route('/add_goal_from_file',methods=['POST'])
def op_add_goal_from_file():
    global goal_data,show_goal
    file = request.files['goal_file']
    if file and allowed_file(file.filename):
        filename = secure_filename(file.filename)
        filepath = os.path.join(app.config['UPLOAD_FOLDER'], filename)
        file.save(filepath)
        global file_path
        file_path['goal_file'] = filepath
        with open(filepath, "r") as fin:
            goal_context = fin.read()
            client.add_res("room:goal_model",{"initial":goal_context},None)
            goal_data = json.load(open(filepath))
            get_goal_list(goal_data)
            show_goal = get_show_goal(goal_data)
            #print "show goal list:"
            #print goal_list
    return redirect(url_for('view_environment_list'))

def get_software_list(data):
    for tmp in data:
        if 'children' in tmp:
            get_software_list(tmp['children'])
        elif 'label' in tmp:
            software_list.append(tmp['label'])


def get_show_software(data):
    my_list = []
    for tmp in data.keys():
        new_one = dict()
        new_one["label"] = tmp.encode()
        for i in data[tmp].keys():
            if i=="range" or i=="impact":
                continue
            if not ("children" in new_one) :
                new_one["children"] = []
            new_one["children"].append({"label":i.encode()})
        my_list.append(new_one)
    return my_list


@app.route('/add_software_from_file',methods=['POST'])
def op_add_software_from_file():
    global show_software
    file = request.files['software_file']
    if file and allowed_file(file.filename):
        filename = secure_filename(file.filename)
        filepath = os.path.join(app.config['UPLOAD_FOLDER'], filename)
        file.save(filepath)
        global file_path
        file_path['software_file'] = filepath
        with open(filepath, "r") as fin:
            context = fin.read()
            client.add_res("room:software_model",{"initial":context},None)
            software_data = json.load(open(filepath))["software"]["feature"]
            # add

            #print software_data
            show_software =get_show_software(software_data)
            get_software_list(show_software)
            #print "show software list:"
            #print software_list
            #print show_software
    return redirect(url_for('view_environment_list'))

@app.route('/runtime_main')
def view_runtime():
    return render_template('runtime.html')

@app.route('/runtime_detail')
def view_list():
    res_vals = client.get_all_res_value(all_res, client.get_clock())
    property_vals = client.get_all_res_value(all_property, -1)
    goal_vals = client.get_all_res_value(goal_list, -1)
    software_vals = client.get_all_res_value(software_list,-1)
    #add
    #print 'show clock'
    #print client.get_clock()
    return render_template('list.html',
                           resources=res_vals,
                           property=property_vals,
                           goal=goal_vals,
                           software=software_vals,
                           clock=client.get_clock())

def allowed_file(filename):
    return '.' in filename and \
           filename.rsplit('.', 1)[1] in ALLOWED_EXTENSIONS

@app.route('/update_value',methods=['GET','POST'])
def get_new_value():
    res_vals = client.get_all_res_value(all_res, -1)
    property_vals = client.get_all_res_value(all_property, -1)
    goal_vals = client.get_all_res_value(goal_list, -1)
    software_vals = client.get_all_res_value(software_list, -1)
    return json.dumps([res_vals,property_vals,goal_vals,software_vals])

@app.route('/tick',methods=['GET', 'POST'])
def op_tick():
    clock = client.ticktock()
    return json.dumps({'clock':clock,'rule_id':client.get_res_value(res_rule_id)})

@app.route('/ticktock_to_next_update',methods=['GET', 'POST'])
def op_ticktock_to_next_update():
    clock = client.ticktock_to_next_update(True)
    return json.dumps({'clock':clock,'rule_id':client.get_res_value(res_rule_id)})


@app.route('/goal_values',methods=['GET', 'POST'])
def op_get_goal_values():
    goal_vals = client.get_all_res_values(goal_list)
    #print "goal_values:",goal_vals
    ret = []
    for name, vals in goal_vals.items():
        ret.append({
            'name':name,
            'data':vals
        })
    return json.dumps(ret)

@app.route('/login',methods=['GET','POST'])
def login():
    login_form = MyForm(csrf_enabled=False)
    if login_form.validate_on_submit():
        user = login_form.data['user']
        psd = login_form.data['psd']
        print user
        print psd
        # Open database connection
        db = pymysql.connect("localhost", "root", password, "test")
        # prepare a cursor object using cursor() method
        cursor = db.cursor()
        # execute SQL query using execute() method.
        sql = "select psd from admin_tbl \
            where name = '%s';" % user
        cursor.execute(sql)
        # Fetch a single row using fetchone() method.
        cor_psd = cursor.fetchone()
        #print cor_psd[0]
        if cor_psd != None and psd == cor_psd[0]:
            global  current_user
            current_user = user
            return redirect(url_for('view_environment_main'))
        else:
            return render_template('login.html',message='Bad username or password',form=login_form,name_cn=u'登录',name_en='login')
    return render_template('login.html',form=login_form,name_cn=u'登录',name_en='login')

@app.route('/register',methods=['GET','POST'])
def register():
    register_form = MyForm(csrf_enabled=False)
    if register_form.validate_on_submit():
        user = register_form.data['user']
        psd = register_form.data['psd']

        # Open database connection
        db = pymysql.connect("localhost", "root", password, "test")
        # prepare a cursor object using cursor() method
        cursor = db.cursor()
        # execute SQL query using execute() method.
        sql = "select psd from admin_tbl \
            where name = '%s';" % user
        cursor.execute(sql)
        # Fetch a single row using fetchone() method.
        if cursor.fetchone() != None:
            return render_template('login.html', message='username has existed', form=register_form,name_cn=u'注册',name_en='register')
        else:
            sql = "insert into admin_tbl \
                  (name,psd) \
                  values \
                  ('%s','%s');" % \
                  (user,psd)
            print sql
            try:
                # 执行sql语句
                cursor.execute(sql)
                # 执行sql语句
                db.commit()
            except:
                # 发生错误时回滚
                db.rollback()

            return render_template('environment_main.html')
    return render_template('login.html',form=register_form,name_cn=u'注册',name_en='register')

@app.route('/save_config',methods=['GET'])
def save_config():
    global file_path
    if not check_filepath():
        return "not enough files!"
    # Open database connection
    db = pymysql.connect("localhost", "root", password, "test")
    # prepare a cursor object using cursor() method
    cursor = db.cursor()
    sql = "INSERT INTO config_file(res_file, \
        property_file, goal_file, software_file) \
        VALUES ('"+file_path['res_file']+"','"+file_path['property_file']+"','"\
          +file_path['goal_file']+"','"+file_path['software_file'] +"');"
    try:
        # Execute the SQL command
        cursor.execute(sql)
        # Commit your changes in the database
        db.commit()
    except:
        # Rollback in case there is any error
        db.rollback()
   # print sql
    if current_user == '':
        return "not login"
    sql = "select configID from config_file\
        where configID = (select max(configID) from config_file);"
    cursor.execute(sql)
    configID = cursor.fetchone()
    sql = "insert into user_config(username, configID) values ('"+current_user+"',"+str(configID[0])+");"
    try:
        cursor.execute(sql)
        db.commit()
    except:
        db.rollback()

    return "saved!"

def check_filepath():
    global file_path
    if file_path.has_key('res_file') and file_path.has_key('property_file') and file_path.has_key('goal_file') and file_path.has_key('res_file') :
        return True
    else:
        return False

@app.route('/use_config',methods=['GET'])
def use_config():
    #print "reach use_config"
    #print request.args
    res_file_path=request.args.get('res_file')
    property_file_path = request.args.get('property_file')
    goal_file_path = request.args.get('goal_file')
    software_file_path = request.args.get('software_file')
    res_list = Xutils.get_res_list_from_environment_json(res_file_path)
    for res in res_list:
        print res
        client.add_res(*res)
        all_res.append(res[0])
        #show_environment.append({"label": res[0].encode()})
    print client.get_all_res_value(all_res, -1)

    property_list = Xutils.get_res_list_from_property_json(property_file_path)
    for res in property_list:
        client.add_res(*res)
        all_property.append(res[0])
        #show_property.append({"label": res[0].encode()})

    with open(goal_file_path, "r") as fin:
        goal_context = fin.read()
        client.add_res("room:goal_model", {"initial": goal_context}, None)
        goal_data = json.load(open(goal_file_path))
        get_goal_list(goal_data)
        show_goal = get_show_goal(goal_data)

    with open(software_file_path, "r") as fin:
        context = fin.read()
        client.add_res("room:software_model", {"initial": context}, None)
        software_data = json.load(open(software_file_path))["software"]["feature"]
        # add
        # print software_data
        show_software = get_show_software(software_data)
        get_software_list(show_software)
    return "ok"


if __name__ == '__main__':
    client.reset_res_pool()
    with app.app_context():
        app.run(port=5000,threaded=True)