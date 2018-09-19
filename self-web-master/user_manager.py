from flask_login import UserMixin
import pymysql

class User(UserMixin):
    def __init__(self):
        self.id = None
        self.username = None

    def __repr__(self):
        return '<User %r>' % self.username

class User_manager:
    db_psd = 'czy888'
    db_name = 'test'
    def __init__(self):
        pass
    
    @classmethod
    def authorize(self, username, psd):
        user = User()
        # Open database connection
        db = pymysql.connect("localhost", "root", self.db_psd, self.db_name)
        # prepare a cursor object using cursor() method
        cursor = db.cursor()
        # execute SQL query using execute() method.
        sql = "SELECT * FROM admin_tbl \
            WHERE name = '%s' AND psd = '%s';" % (username, psd)
        cursor.execute(sql)
        # Fetch a single row using fetchone() method.
        res = cursor.fetchone()
        db.close()
        if res:
            user.id = res[0]
            user.username = res[1]
            return True, user
        else:
            return False, user

    @classmethod
    def get(self, id):
        user = User()
        db = pymysql.connect("localhost", "root", self.db_psd, self.db_name)
        # prepare a cursor object using cursor() method
        cursor = db.cursor()
        # execute SQL query using execute() method.
        sql = "SELECT name FROM admin_tbl \
            WHERE id = '%s';" % id
        cursor.execute(sql)
        res = cursor.fetchone()
        if res:
            user.id = id
            user.username = res[0]
            return user
        else:
            return None

