package UI;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2015/1/1.
 */
public class mocker {

    public static int clock = 0;

    public int getResValue(String name, int clock) {
        if (name.equals("temp")) {
            return (int) (Math.random() * 30);
        } else if (name.equals("hum")) {
            return (int) (Math.random() * 100);
        }
        return 0;
    }

    public List<?> getResValue(String name) {
        if (name.equals("temp")) {
            List<Double> ret = new ArrayList<Double>();
            for (int i = 0; i < shell.clock; i++) {
                double temp = (double) (Math.random() * 100);
                ret.add(temp);
            }
            return ret;
        }
        return null;
    }

    public void setResValue(String name, Object value) {

    }

    public static void ticktock(float clockCount) {
        clock += clockCount;
    }
}
