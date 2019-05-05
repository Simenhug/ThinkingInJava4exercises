package concurrency;

//: concurrency/Restaurant.java
// The producer-consumer approach to task cooperation.

//Exercise 27: (2) Modify Restaurant.java to use explicit Lock and Condition objects
import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static net.mindview.util.Print.*;

class Meal {
    private final int orderNum;
    public Meal(int orderNum) { this.orderNum = orderNum; }
    public String toString() { return "Meal " + orderNum; }
}

class WaitPerson implements Runnable {
    private Restaurant restaurant;
    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();
    public WaitPerson(Restaurant r) { restaurant = r; }
    public void run() {
        lock.lock();
        try {
            while(!Thread.interrupted()) {
                while(restaurant.meal == null)
                    condition.await();// ... for the chef to produce a meal
                print("Waitperson got " + restaurant.meal);
                restaurant.chef.lock.lock();
                try {
                    restaurant.meal = null;
                    restaurant.chef.condition.signalAll(); // Ready for another
                } finally {
                    restaurant.chef.lock.unlock();
                }
            }
        } catch(InterruptedException e) {
            print("WaitPerson interrupted");
        } finally {
            lock.unlock();
        }
    }
}

class Chef implements Runnable {
    private Restaurant restaurant;
    private int count = 0;
    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();
    public Chef(Restaurant r) { restaurant = r; }
    public void run() {
        lock.lock();
        try {
            while(!Thread.interrupted()) {
                while(restaurant.meal != null)
                    condition.await(); // ... for the meal to be taken
                if(++count == 10) {
                    print("Out of food, closing");
                    restaurant.exec.shutdownNow();
                }
                printnb("Order up! ");
                restaurant.waitPerson.lock.lock();
                try {
                    restaurant.meal = new Meal(count);
                    restaurant.waitPerson.condition.signalAll();
                } finally {
                    restaurant.waitPerson.lock.unlock();
                }
                TimeUnit.MILLISECONDS.sleep(100);
            }
        } catch(InterruptedException e) {
            print("Chef interrupted");
        } finally {
            lock.unlock();
        }
    }
}

class Restaurant {
    Meal meal;
    ExecutorService exec = Executors.newCachedThreadPool();
    WaitPerson waitPerson = new WaitPerson(this);
    Chef chef = new Chef(this);
    public Restaurant() {
        exec.execute(chef);
        exec.execute(waitPerson);
    }
    public static void main(String[] args) {
        new Restaurant();
    }
}

public class E27 {
    public static void main(String[] args) {
        Restaurant.main(args);
    }
}

