package 多线程;
import java.util.concurrent.*;

class A implements Runnable {
    private volatile boolean signal = false;
    public synchronized void run() { // method calling wait() must own monitor
        try {
            while(!signal) { 	// wait() should always
                // be used in a loop
                System.out.println("A.run() wait()");
                // TimeUnit.MILLISECONDS.sleep(1000);
                wait();
                signal = true;
                System.out.println("A is done waiting");
            }
        } catch(InterruptedException e) {
            System.out.println("A run() interrupted");
        } finally {
            System.out.println("Leaving A.run()");
        }
    }
    public synchronized void message() {
        System.out.println("Hi from A");
    }
}

class B implements Runnable {
    private A a;
    public A getA() { return a; }
    B(A a) { this.a = a; }
    public void run() {
        try {
            TimeUnit.MILLISECONDS.sleep(2000);
            synchronized(a) { // method calling notifyAll() must own monitor
                System.out.println("B.run() a.notifyAll()");
                a.notifyAll();
            }
        } catch(InterruptedException e) {
            System.out.println("B sleep interrupted");
        }
    }
}

public class Ex21 {
    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        B b = new B(new A());
        exec.execute(b.getA());
        try {
            TimeUnit.MILLISECONDS.sleep(100);
        } catch(InterruptedException e) {
            System.out.println("main() sleep interrupted");
        }
        b.getA().message();
        exec.execute(b);
        try {
            TimeUnit.MILLISECONDS.sleep(2500);
        } catch(InterruptedException e) {
            System.out.println("main() sleep interrupted");
        }
        exec.shutdownNow();
    }
}