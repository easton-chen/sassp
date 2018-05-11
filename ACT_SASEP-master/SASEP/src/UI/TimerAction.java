package UI;

/**
 * Created by admin on 2014/12/29.
 */

import com.skyspace.client.IResPoolClient;
import com.skyspace.client.ResPoolClient;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.wb.swt.SWTResourceManager;
import org.swtchart.Chart;
import org.swtchart.IAxis;
import org.swtchart.ILineSeries;
import org.swtchart.ISeries;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TimerTask;
import java.util.Timer;
import java.util.concurrent.ConcurrentHashMap;
import java.util.List;


public class TimerAction extends TimerTask{

    private ConcurrentHashMap<String, Object> res_value = new ConcurrentHashMap<String,Object>();

    public volatile boolean isStop = false;

    private Integer targetClock;

    public Thread thread = new Thread(new Runnable() {
        @Override
        public void run() {
            while (!isStop && (targetClock == null || targetClock > shell.clock)) {
                long start = System.currentTimeMillis();
                shell.client.ticktock(1);
                put_value(res_value,"temperature", shell.client.getResValue("temperature", shell.clock, Integer.class));
                put_value(res_value,"brightness", shell.client.getResValue("brightness", shell.clock));
                put_value(res_value,"humidity", shell.client.getResValue("humidity", shell.clock));
                put_value(res_value,"volume", shell.client.getResValue("volume", shell.clock));
                put_value(res_value,"power", shell.client.getResValue("power", shell.clock));
                put_value(res_value,"airquality", shell.client.getResValue("airquality", shell.clock));
                put_value(res_value,"season", shell.client.getResValue("season", shell.clock));
                put_value(res_value,"weather", shell.client.getResValue("weather", shell.clock));
                put_value(res_value,"time", shell.client.getResValue("time", shell.clock));
                put_value(res_value,"computer.brightness", shell.client.getResValue("computer.brightness", shell.clock));
                put_value(res_value,"computer.volume", shell.client.getResValue("computer.volume", shell.clock));
                put_value(res_value,"tv.brightness", shell.client.getResValue("tv.brightness", shell.clock));
                put_value(res_value,"tv.volume", shell.client.getResValue("tv.volume", shell.clock));
                put_value(res_value,"room:rule_id", shell.client.getResValue("room:rule_id", shell.clock));
                put_value(res_value,"somebodyhome",shell.client.getResValue("somebodyhome", shell.clock, Integer.class));
                put_value(res_value,"window",shell.client.getResValue("window", shell.clock, Integer.class));
                put_value(res_value,"blind",shell.client.getResValue("blind", shell.clock, Integer.class));
                put_value(res_value,"light.power",shell.client.getResValue("light.power", shell.clock, Integer.class));
                put_value(res_value,"heater.power",shell.client.getResValue("heater.power", shell.clock, Integer.class));
                put_value(res_value,"computer.power",shell.client.getResValue("computer.power", shell.clock, Integer.class));
                put_value(res_value,"aircondition.power",shell.client.getResValue("aircondition.power", shell.clock, Integer.class));
                put_value(res_value,"aircondition.mode",shell.client.getResValue("aircondition.mode", shell.clock));
                put_value(res_value,"tv.power",shell.client.getResValue("tv.power", shell.clock, Integer.class));
                put_value(res_value,"outside.temperature",shell.client.getResValue("outside.temperature", shell.clock, Integer.class));
                System.out.println("RESVALUE-DEAMON:"+res_value);
                shell.clock += 1;
                //System.out.println("************************\n"+shell.clock+"********\n");
                while (start + shell.clock_step > System.currentTimeMillis()) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    });

    public void put_value(ConcurrentHashMap<String, Object> _, String key, Object value) {
        if (value != null) {
            res_value.put(key, value);
        } else {
            System.out.println("put null value, key="+key);
        }
    }

    public TimerAction(Integer targetClock) {
        this.targetClock = targetClock;
    }

    public void run(){
        shell.display.syncExec(new Runnable() {
            @Override
            public void run() {
                try{

                    shell.text_1.setText(String.valueOf(shell.clock));
                    
                    //shell.text_tem.setText("asd");
                    double x;
                    Integer y;
                    //x = (Double) res_value.get("brightness");
                    //y = (int)(x+0.5);
              
                    shell.text_bri.setText(String.valueOf(res_value.get("brightness")));
                    shell.text_tem.setText(String.valueOf(res_value.get("temperature")));
                    shell.text_pow.setText(String.valueOf(res_value.get("power")));
                    shell.text_hum.setText(String.valueOf(res_value.get("humidity")));
                    x = (Double) res_value.get("volume");
                    y = (int)(x+0.5);
                    shell.text_vol.setText(String.valueOf(y));

                    shell.text_air.setText(String.valueOf(res_value.get("airquality")));
                    shell.text_season.setText(String.valueOf(res_value.get("season")));
                    shell.text_weather.setText(String.valueOf(res_value.get("weather")));
                    shell.text_time.setText(String.valueOf(res_value.get("time")));
                    shell.text_4.setText(String.valueOf(res_value.get("outside.temperature")));
                    shell.text_5.setText(String.valueOf(res_value.get("computer.brightness")));
                    shell.text_6.setText(String.valueOf(res_value.get("computer.volume")));
                    shell.text_9.setText(String.valueOf(res_value.get("tv.brightness")));
                    shell.text_10.setText(String.valueOf(res_value.get("tv.volume")));

                    showImage("somebodyhome");
                    showImage("window");
                    showImage("blind");
                    showImage("light.power");
                    showImage("heater.power");
                    showImage("computer.power");
                    showImage("aircondition.power");
                    showImage("aircondition.mode");
                    showImage("tv.power");
                    //rule
                    String s = String.valueOf(res_value.get("room:rule_id"));
                    String tmp = "Rule_ID:\n";
                    String[] rule_str = s.split("##");
                    for(int i=0;i<rule_str.length;i++)
                    {
                        tmp = tmp + "           " + rule_str[i] + "\n";
                        shell.text.setText(tmp);
                    }
                    System.out.println("RESVALUE-UI:"+res_value);

                    //chart
                    String sc = shell.combo_2.getText();
                    sc = "room:"+sc;
                    List<Double> data = null;
                    if(sc!=null) {
                        data = shell.client.getResValue(sc, Double[].class);System.out.println(sc+"datasize:" + data.size());
                    }
                    if(data == null)
                    {
                        mocker mock = new mocker();
                        data = (List<Double>) mock.getResValue("temp");
                    }
                    double[] chart_y = new double[data.size()];
                    int count = 0;
                    for(Double xc:data){
                        chart_y[count] = xc.doubleValue();
                        count++;
                    }
                    shell.ySeries = chart_y;
                    shell.chart.setBounds(0, 19, 579, 251);
                    ILineSeries lineSeries = (ILineSeries) shell.chart.getSeriesSet()
                            .createSeries(ISeries.SeriesType.LINE, "line series");
                    lineSeries.setYSeries(shell.ySeries);
                    shell.chart.redraw();
                    shell.chart.getAxisSet().adjustRange();
                    //shell.chart.redraw();
                    double sum =0;
                    for(int i=0;i<chart_y.length;i++)
                    {
                       // System.out.println(chart_y[i]);
                         sum=sum+shell.ySeries[i];
                         System.out.println(shell.ySeries[i]);
                    }
                    shell.text_aver.setText(String.format("%.2f", sum / chart_y.length));

                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

    public void showImage(String name) {
        if (name.equals("somebodyhome")) {
            Integer t = (Integer) res_value.get("somebodyhome");
            if (t == null) {
                System.out.println("somebodyhome is null!");
                shell.btnSomebody_1.setImage(null);
               // return;
            }
            if (t == 0) {
                shell.btnSomebody_1.setImage(SWTResourceManager.getImage(".\\imagePF\\nobody.jpg"));
            } else {
                shell.btnSomebody_1.setImage(SWTResourceManager.getImage(".\\imagePF\\somebody.jpg"));
            }
        }
        if(name.equals("window")){
            Integer t = (Integer) res_value.get("window");
            if (t == null) {
                System.out.println("window is null!");
                shell.button.setImage(null);
               // return;
            }
            if (t == 0) {
                shell.button.setImage(SWTResourceManager.getImage(".\\imagePF\\guanchl.jpg"));
                shell.txtOn.setText("Off");
            } else {
                shell.button.setImage(SWTResourceManager.getImage(".\\imagePF\\kaichl.jpg"));
                shell.txtOn.setText("On");
            }
        }
        if(name.equals("blind")){
            Integer t = (Integer) res_value.get("blind");
            if (t == null) {
                System.out.println("blind is null!");
                shell.button_2.setImage(null);
               // return;
            }
            if (t == 0) {
                shell.button_2.setImage(SWTResourceManager.getImage(".\\imagePF\\blindoff.jpg"));
                shell.txtOff.setText("Off");
            } else {
                shell.button_2.setImage(SWTResourceManager.getImage(".\\imagePF\\blindon.jpg"));
                shell.txtOff.setText("On");
            }
        }
        if(name.equals("light.power")){
            Integer t = (Integer) res_value.get("light.power");
            if (t == null) {
                System.out.println("light.power is null!");
                shell.button_4.setImage(null);
               // return;
            }
            if (t == 0) {
                shell.button_4.setImage(SWTResourceManager.getImage(".\\imagePF\\guandeng.jpg"));
                shell.text_3.setText("Off");
            } else {
                shell.button_4.setImage(SWTResourceManager.getImage(".\\imagePF\\kaideng.jpg"));
                shell.text_3.setText("On");
            }
        }
        if(name.equals("heater.power")){
            Integer t = (Integer) res_value.get("heater.power");
            if (t == null) {
                System.out.println("light.power is null!");
                shell.button_5.setImage(null);
              //  return;
            }
            if (t == 0) {
                shell.button_5.setImage(SWTResourceManager.getImage(".\\imagePF\\heateroff.jpg"));
                shell.txtOff_1.setText("Off");
            } else {
                shell.button_5.setImage(SWTResourceManager.getImage(".\\imagePF\\heateron.jpg"));
                shell.txtOff_1.setText("On");
            }
        }
        if(name.equals("computer.power")){
            Integer t = (Integer) res_value.get("computer.power");
            if (t == null) {
                System.out.println("computer.power is null!");
                shell.button_7.setImage(null);
               // return;
            }
            if (t == 0) {
                shell.button_7.setImage(SWTResourceManager.getImage(".\\imagePF\\pcclose.jpg"));
                shell.txtOff_2.setText("Off");
            } else {
                shell.button_7.setImage(SWTResourceManager.getImage(".\\imagePF\\pcopen.jpg"));
                shell.txtOff_2.setText("On");
            }
        }
        if(name.equals("aircondition.power")){
            Integer t = (Integer) res_value.get("aircondition.power");
            if (t == null) {
                System.out.println("aicondition.power is null!");
                shell.button_9.setImage(null);
               // return;
            }
            if (t == 0) {
                shell.button_9.setImage(SWTResourceManager.getImage(".\\imagePF\\airclose.jpg"));
                shell.txtOff_3.setText("Off");
            } else {
                shell.button_9.setImage(SWTResourceManager.getImage(".\\imagePF\\airopen.jpg"));
                shell.txtOff_3.setText("On");            }
        }
        if(name.equals("aircondition.mode")){
            String s = String.valueOf(res_value.get("aircondition.mode"));
            if (s == null) {
                System.out.println("aicondition.mode is null!");
                shell.button_11.setImage(null);
               // return;
            }
            if (s.equals("cool")) {
                shell.button_11.setImage(SWTResourceManager.getImage(".\\imagePF\\cool.jpg"));
            } else {
                shell.button_11.setImage(SWTResourceManager.getImage(".\\imagePF\\heat.jpg"));
            }
        }
        if(name.equals("tv.power")){
            Integer t = (Integer) res_value.get("tv.power");
            if (t == null) {
                System.out.println("tv.power is null!");
                shell.button_12.setImage(null);
               // return;
            }
            if (t == 0) {
                shell.button_12.setImage(SWTResourceManager.getImage(".\\imagePF\\tvoff.jpg"));
                shell.txtOff_4.setText("Off");
            } else {
                shell.button_12.setImage(SWTResourceManager.getImage(".\\imagePF\\tvon.jpg"));
                shell.txtOff_4.setText("On");            }
        }
    }
}


