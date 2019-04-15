package 多线程.ex22;

import java.util.concurrent.TimeUnit;

public class ThreadTest extends Thread {
    @Override
    public void run() {
        for (int i =0;i<=1000;i++){
            if(i==1000){
                try {
                    Thread.sleep(1009);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("hello "+ Thread.currentThread().getName());
            }
        }
    }
}

class Test{
    public static void main(String[] args) {
        ThreadTest threadTest = new ThreadTest();
        threadTest.start();
        threadTest.run();
    }
}
