package concurrency;

//: concurrency/Restaurant.java
// The producer-consumer approach to task cooperation.
// Exercise 26: (8) Add a BusBoy class to Restaurant.java. After the meal is delivered,
// the WaitPerson should notify the BusBoy to clean up.
import java.util.concurrent.*;
import static net.mindview.util.Print.*;

class Meal {
    private final int orderNum;
    public Meal(int orderNum) { this.orderNum = orderNum; }
    public String toString() { return "Meal " + orderNum; }
}

class WaitPerson implements Runnable {
    private Restaurant restaurant;
    public WaitPerson(Restaurant r) { restaurant = r; }
    public void run() {
        try {
            while(!Thread.interrupted()) {
                synchronized(this) {
                    while(restaurant.meal == null || restaurant.fetched == 1)
                        wait(); // ... for the chef to produce a meal and for the bus boy to deliver the last meal
                }
                print("Waitperson got " + restaurant.meal);
                print("Waitperson giving " + restaurant.meal + " to BusBoy");
                synchronized (restaurant.busBoy) {
                    restaurant.fetched = 1;
                    restaurant.busBoy.notifyAll();
                }
            }
        } catch(InterruptedException e) {
            print("WaitPerson interrupted");
        }
    }
}

class Chef implements Runnable {
    private Restaurant restaurant;
    private int count = 0;
    public Chef(Restaurant r) { restaurant = r; }
    public void run() {
        try {
            while(!Thread.interrupted()) {
                synchronized(this) {
                    while(restaurant.meal != null)
                        wait(); // ... for the meal to be taken
                }
                if(++count == 10) {
                    print("Out of food, closing");
                    restaurant.exec.shutdownNow();
                }
                printnb("Order up! ");
                synchronized(restaurant.waitPerson) {
                    restaurant.meal = new Meal(count);
                    print(restaurant.meal + " ready.");
                    restaurant.waitPerson.notifyAll();
                }
                TimeUnit.MILLISECONDS.sleep(100);
            }
        } catch(InterruptedException e) {
            print("Chef interrupted");
        }
    }
}

class BusBoy implements Runnable {
    private Restaurant restaurant;
    public BusBoy(Restaurant r){
        restaurant = r;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                synchronized (this) {
                    while (restaurant.fetched == 0) {
                        wait();
                    }
                    print("Busboy delivering " + restaurant.meal);
                }
                synchronized (restaurant.waitPerson) {
                    restaurant.fetched = 0;
                    restaurant.waitPerson.notifyAll();
                }
                synchronized(restaurant.chef) {
                    restaurant.meal = null;
                    restaurant.chef.notifyAll(); // Ready for another
                }
            }
        } catch (InterruptedException e) {
            print("Busboy interrupted");
        }
    }
}

class Restaurant {
    Meal meal;
    volatile int fetched = 0;
    ExecutorService exec = Executors.newCachedThreadPool();
    WaitPerson waitPerson = new WaitPerson(this);
    Chef chef = new Chef(this);
    BusBoy busBoy = new BusBoy(this);
    public Restaurant() {
        exec.execute(chef);
        exec.execute(waitPerson);
        exec.execute(busBoy);
    }
    public static void main(String[] args) {
        new Restaurant();
    }
}

public class E26 {
    public static void main(String[] args) {
        Restaurant.main(args);
    }
}
