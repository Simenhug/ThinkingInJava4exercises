package concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static net.mindview.util.Print.print;

//Exercise 15: (1) Create a class with three methods containing critical sections that all synchronize on the same object.
// Create multiple tasks to demonstrate that only one of these methods can run at a time.
// Now modify the methods so that each one synchronizes on a different object and show that all three
// methods can be running at once.

//Exercise 16: (1) Modify Exercise 15 to use explicit Lock objects.
public class E16 {
    private Object dtarget = new Object();
    private Object atarget = new Object();
    private Object ltarget = new Object();
    private Lock lock = new ReentrantLock();
    private Lock lock1 = new ReentrantLock();
    private Lock lock2 = new ReentrantLock(); //using three different locks
    public void Della(){
        lock.lock();
        try {
            for (int i = 0; i < 20; i++) {
                print("Della");
                Thread.yield();
            }
        }finally {
            lock.unlock();
        }
    }
    public void Abby(){
        lock1.lock();
        try {
            for (int i = 0; i < 20; i++) {
                print("Abby");
                Thread.yield();
            }
        }finally {
            lock1.unlock();
        }
    }
    public void Lora(){
        lock2.lock();
        try {
            for (int i = 0; i < 20; i++) {
                print("Lora");
                Thread.yield();
            }
        }finally {
            lock2.unlock();
        }
    }

    public static void main(String[] args) {
        E16 subject = new E16();
        new Thread(){
            @Override
            public void run() {
                subject.Della();
            }
        }.start();
        new Thread(){
            @Override
            public void run() {
                subject.Abby();
            }
        }.start();
        subject.Lora();
    }
}
