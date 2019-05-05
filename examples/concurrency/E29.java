package concurrency;

//: concurrency/ToastOMatic.java
// A toaster that uses queues.
// Exercise 29: (8) Modify ToastOMatic.java to create peanut butter and jelly on toast sandwiches using two 
// separate assembly lines (one for peanut butter, the second for jelly, then merging the two lines).

import java.util.concurrent.*;
import java.util.*;
import static net.mindview.util.Print.*;

class Toast {
    public enum Status { DRY, BUTTERED, JAMMED, BUTTERJAMMED }
    private Status status = Status.DRY;
    private final int id;
    public Toast(int idn) { id = idn; }
    public void butter() { status = Status.BUTTERED; }
    public void jam() { status = Status.JAMMED; }
    public void combo() { status = Status.BUTTERJAMMED; }
    public Status getStatus() { return status; }
    public int getId() { return id; }
    public String toString() {
        return "Toast " + id + ": " + status;
    }
}

class ToastQueue extends LinkedBlockingQueue<Toast> {}

class Toaster implements Runnable {
    private ToastQueue dryQueue;
    private int count = 0;
    private Random rand = new Random(47);
    public Toaster(ToastQueue tq) { dryQueue = tq; }
    public void run() {
        try {
            while(!Thread.interrupted()) {
                TimeUnit.MILLISECONDS.sleep(
                        100 + rand.nextInt(600));
                // Make toast
                Toast t = new Toast(count++);
                print(t);
                // Insert into queue
                dryQueue.put(t);
            }
        } catch(InterruptedException e) {
            print("Toaster interrupted");
        }
        print("Toaster off");
    }
}

// Apply butter to toast:
class Butterer implements Runnable {
    private ToastQueue dryQueue, butteredQueue;
    public Butterer(ToastQueue dry, ToastQueue buttered) {
        dryQueue = dry;
        butteredQueue = buttered;
    }
    public void run() {
        try {
            while(!Thread.interrupted()) {
                // Blocks until next piece of toast is available:
                Toast t = dryQueue.take();
                t.butter();
                butteredQueue.put(t);
                print(t);
            }
        } catch(InterruptedException e) {
            print("Butterer interrupted");
        }
        print("Butterer off");
    }
}

// Apply jam to buttered toast:
class Jammer implements Runnable {
    private ToastQueue dryQueue, jammedQueue;
    public Jammer(ToastQueue toasted, ToastQueue jammed) {
        dryQueue = toasted;
        jammedQueue = jammed;
    }
    public void run() {
        try {
            while(!Thread.interrupted()) {
                // Blocks until next piece of toast is available:
                Toast t = dryQueue.take();
                t.jam();
                jammedQueue.put(t);
                print(t);
            }
        } catch(InterruptedException e) {
            print("Jammer interrupted");
        }
        print("Jammer off");
    }
}

// Consume the toast:
class Eater implements Runnable {
    private ToastQueue butteredQueue, jammedQueue, finishedQueue;
    //private int counter = 0;
    public Eater(ToastQueue buttered, ToastQueue jammed, ToastQueue combo) {
        butteredQueue = buttered;
        jammedQueue = jammed;
        finishedQueue = combo;
    }
    public void run() {
        try {
            while(!Thread.interrupted()) {
                Toast b = butteredQueue.take();
                Toast j = jammedQueue.take();
                b.combo();
                j.combo();
                finishedQueue.put(b);
                finishedQueue.put(j);
                print("Chomp! " + b + " " + j);
            }
        } catch(InterruptedException e) {
            print("Eater interrupted");
        }
        print("Eater off");
    }
}

public class E29 {
    public static void main(String[] args) throws Exception {
        ToastQueue dryQueue = new ToastQueue(),
                butteredQueue = new ToastQueue(),
                jammedQueue = new ToastQueue(),
                finishedQueue = new ToastQueue();
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new Toaster(dryQueue));
        exec.execute(new Butterer(dryQueue, butteredQueue));
        exec.execute(new Jammer(dryQueue, jammedQueue));
        exec.execute(new Eater(butteredQueue,jammedQueue, finishedQueue));
        TimeUnit.SECONDS.sleep(5);
        exec.shutdownNow();
    }
} /* (Execute to see output) *///:~
