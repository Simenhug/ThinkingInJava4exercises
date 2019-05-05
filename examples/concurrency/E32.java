package concurrency;

//: concurrency/OrnamentalGarden.java
//Exercise 32: (7) Use a CountDownLatch to solve the problem of correlating the results from the Entrances
// in OrnamentalGarden.java. Remove the unnecessary code from the new version of the example.
import java.util.concurrent.*;
import java.util.*;
import static net.mindview.util.Print.*;

class Count {
    private int count = 0;
    private Random rand = new Random(47);
    // Remove the synchronized keyword to see counting fail:
    public synchronized int increment() {
        int temp = count;
        if(rand.nextBoolean()) // Yield half the time
            Thread.yield();
        return (count = ++temp);
    }
    public synchronized int value() { return count; }
}

class Entrance implements Runnable {
    private static Count count = new Count();
    private static List<Entrance> entrances =
            new ArrayList<Entrance>();
    private int number = 0;
    private final CountDownLatch latch;
    // Doesn't need synchronization to read:
    private final int id;
    public Entrance(int id, CountDownLatch lat) {
        this.id = id;
        this.latch = lat;
        // Keep this task in a list. Also prevents
        // garbage collection of dead tasks:
        entrances.add(this);
    }
    public void run() {
        try {
            while (!Thread.interrupted()) {
                synchronized (this) {
                    ++number;
                }
                print(this + " Total: " + count.increment());
                TimeUnit.MILLISECONDS.sleep(100);
            }
        } catch(InterruptedException e) {
                print("sleep interrupted");
        } finally {
                latch.countDown();
        }
        print("Stopping " + this);
    }
    public synchronized int getValue() { return number; }
    public String toString() {
        return "Entrance " + id + ": " + getValue();
    }
    public static int getTotalCount() {
        return count.value();
    }
    public static int sumEntrances() {
        int sum = 0;
        for(Entrance entrance : entrances)
            sum += entrance.getValue();
        return sum;
    }
}

public class E32 implements Runnable {
    private final CountDownLatch latch;
    public E32() {
        this.latch = new CountDownLatch(10);
    }
    @Override
    public void run() {
        ExecutorService exec = Executors.newCachedThreadPool();
        for(int i = 0; i < 10; i++)
            exec.execute(new Entrance(i, latch));
        // Run for a while, then stop and collect the data:
        try {
            TimeUnit.SECONDS.sleep(3);
            exec.shutdownNow();
            latch.await();
            if (!exec.awaitTermination(250, TimeUnit.MILLISECONDS))
                print("Some tasks were not terminated!");
            print("Total: " + Entrance.getTotalCount());
            print("Sum of Entrances: " + Entrance.sumEntrances());
        } catch (InterruptedException e) {
            print("Main thread interrupted");
        }
    }

    public static void main(String[] args) throws Exception {
        new Thread(new E32()).start();
    }
}

