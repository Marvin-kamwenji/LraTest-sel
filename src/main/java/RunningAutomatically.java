
import LRA.LRATest;

import java.util.Timer;
import java.util.TimerTask;

public class RunningAutomatically{
    public static void main(String [] args){
        Timer timer = new Timer();
        TimerTask task = new LRATest();
        timer.schedule(task, 5000, 3000);
    }
}
