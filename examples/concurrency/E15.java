package concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static net.mindview.util.Print.print;

//Exercise 15: (1) Create a class with three methods containing critical sections that all synchronize on the same object.
// Create multiple tasks to demonstrate that only one of these methods can run at a time.
// Now modify the methods so that each one synchronizes on a different object and show that all three
// methods can be running at once.
public class E15 {
    private Object dtarget = new Object();
    private Object atarget = new Object();
    private Object ltarget = new Object();
    public void Della(){
        synchronized (dtarget) {
            for (int i = 0; i < 20; i++) {
                print("Della");
               Thread.yield();
            }
        }
    }
    public void Abby(){
        synchronized (atarget) {
            for (int i = 0; i < 20; i++) {
                print("Abby");
                Thread.yield();
            }
        }
    }
    public void Lora(){
        synchronized (ltarget) {
            for (int i = 0; i < 20; i++) {
                print("Lora");
                Thread.yield();
            }
        }
    }

    public static void main(String[] args) {
        E15 subject = new E15();
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
