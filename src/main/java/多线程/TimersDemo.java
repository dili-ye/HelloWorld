package 多线程;

import java.util.*;
import java.util.concurrent.*;
public class TimersDemo implements Runnable {
    private static int timers = 0;
    private static int tasks = 0;
    public void run() {
        try {
            while(timers < 4000) { // create 4000 Timers
                ++timers;
                Timer t = new Timer();
                t.schedule(new TimerTask() {
                    public void run() {
                        ++tasks;
                        if(timers % 100 == 0)
                            System.out.println(timers + " timers did "
                                    + tasks + " tasks");
                    }
                }, 0);
                try {
                    TimeUnit.MILLISECONDS.sleep(1); // time to do task
                } catch(InterruptedException e) {
                    System.out.println("Sleep interrupted");
                }
                t.cancel();
            }
        } finally {
            System.out.println("Done. " + timers + " timers completed "
                    + tasks + " tasks");
        }
    }
    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new TimersDemo());
        exec.shutdown();
    }
}
