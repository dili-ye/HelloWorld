package 多线程.ex22;

import java.util.concurrent.*;

class Meal {
    private final int orderNum;
    public Meal(int orderNum) { this.orderNum = orderNum; }
    public String toString() { return "Meal " + orderNum; }
}

class WaitPerson26 implements Runnable {
    private Restaurant26 restaurant;
    protected boolean clean = true;
    protected Meal m; // 
    public WaitPerson26(Restaurant26 r) { restaurant = r; }
    public void run() {
        try {
            while(!Thread.interrupted()) {
                synchronized(this) {
                    while(restaurant.meal == null)
                        wait(); // ... for the chef to produce a meal
                }
                m = restaurant.meal;
                System.out.println("WaitPerson got " + m);
                synchronized(restaurant.chef) {
                    restaurant.meal = null;
                    restaurant.chef.notifyAll(); // ready for another
                }
                System.out.println("WaitPerson delivered " + m);
                synchronized(restaurant.busBoy) {
                    clean = false;
                    restaurant.busBoy.notifyAll(); // for cleanup
                }
            }
        } catch(InterruptedException e) {
            System.out.println("WaitPerson interrupted");
        }
    }
}

class Chef26 implements Runnable {
    private Restaurant26 restaurant;
    private int count = 0;
    public Chef26(Restaurant26 r) { restaurant = r; }
    public void run() {
        try {
            while(!Thread.interrupted()) {
                synchronized(this) {
                    while(restaurant.meal != null)
                        wait(); // ... for the meal to be taken
                }
                if(++count == 10) {
                    System.out.println("Out of food, closing");
                    restaurant.exec.shutdownNow();
                    return;
                }
                System.out.println("Order up! ");
                synchronized(restaurant.waitPerson) {
                    restaurant.meal = new Meal(count);
                    restaurant.waitPerson.notifyAll();
                }
                TimeUnit.MILLISECONDS.sleep(100);
            }
        } catch(InterruptedException e) {
            System.out.println("Chef interrupted");
        }
    }
}

class BusBoy26 implements Runnable {
    private Restaurant26 restaurant;
    public BusBoy26(Restaurant26 r) { restaurant = r; }
    public void run() {
        try {
            while(!Thread.interrupted()) {
                synchronized(this) {
                    while(restaurant.waitPerson.clean)
                        wait();
                }
                System.out.println("BusBoy cleaning up " + restaurant.waitPerson.m);
                TimeUnit.MILLISECONDS.sleep(500);
                restaurant.waitPerson.clean = true;
            }
        } catch(InterruptedException e) {
            System.out.println("BusBoy interrupted");
        }
    }
}

public class Restaurant26 {
    Meal meal;
    ExecutorService exec = Executors.newCachedThreadPool();
    WaitPerson26 waitPerson = new WaitPerson26(this);
    BusBoy26 busBoy = new BusBoy26(this);
    Chef26 chef = new Chef26(this);
    public Restaurant26() {
        exec.execute(chef);
        exec.execute(waitPerson);
        exec.execute(busBoy);
    }
    public static void main(String[] args) {
        new Restaurant26();
    }
}