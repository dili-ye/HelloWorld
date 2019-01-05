package 多线程;

import java.util.concurrent.*;

class Meal {
    private final int orderNum;
    public Meal(int orderNum) { this.orderNum = orderNum; }
    public String toString() { return "Meal " + orderNum; }
}

class WaitPerson25 implements Runnable {
    private Restaurant25 restaurant;
    public WaitPerson25(Restaurant25 r) { restaurant = r; }
    public void run() {
        try {
            while(!Thread.interrupted()) {
                synchronized(this) {
                    while(restaurant.meal == null)
                        wait(); // ... for the chef to produce a meal
                }
                System.out.println("WaitPerson25 got " + restaurant.meal);
                synchronized(restaurant.chef) {
                    restaurant.meal = null;
                    restaurant.chef.notifyAll(); // ready for another
                }
            }
        } catch(InterruptedException e) {
            System.out.println("WaitPerson25 interrupted");
        }
    }
}

class Chef25 implements Runnable {
    private Restaurant25 restaurant;
    private int count = 0;
    public Chef25(Restaurant25 r) { restaurant = r; }
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
                    return; // now Chef25 won't make another meal
                }
                System.out.println("Order up! ");
                synchronized(restaurant.waitPerson) {
                    restaurant.meal = new Meal(count);
                    restaurant.waitPerson.notifyAll();
                }
                TimeUnit.MILLISECONDS.sleep(100);
            }
        } catch(InterruptedException e) {
            System.out.println("Chef25 interrupted");
        }
    }
}

public class Restaurant25 {
    Meal meal;
    ExecutorService exec = Executors.newCachedThreadPool();
    WaitPerson25 waitPerson = new WaitPerson25(this);
    Chef25 chef = new Chef25(this);
    public Restaurant25() {
        exec.execute(chef);
        exec.execute(waitPerson);
    }
    public static void main(String[] args) {
        new Restaurant25();
    }
}