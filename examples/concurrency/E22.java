package concurrency;

import java.util.concurrent.*;
import static net.mindview.util.Print.*;

//Exercise 22: (4) Create an example of a busy wait. One task sleeps for a while and then sets a flag to true.
// The second task watches that flag inside a while loop (this is the busy wait) and when the flag becomes true,
// sets it back to false and reports the change to the console. Note how much wasted time the program spends
// inside the busy wait, and create a second version of the program that uses wait( ) instead of the busy wait.
class Waiter{
    private volatile boolean flag = false;

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
    public void performer(){
        try {
            print("Action waiting for the flag");
            while (!flag) {
            }
            flag = false;
            print("flag set back to false");
            print("flag is set and action made");
        } finally {
            print("ending performance");
        }
    }
    public synchronized void waitformer(){
        try {
            print("Action waiting for the flag");
            while (!flag) {
                wait();
                flag = false;
                print("flag set back to false");
                print("flag is set and action made");
            }
        } catch (InterruptedException e) {
            print(e);
        } finally {
            print("ending performance");
        }
    }
}
class Action{
    private Waiter waiter;
    public Action(Waiter wait){
        waiter = wait;
    }
    public void flagger(){
        try {
            synchronized (waiter) {
                print("sleeping before setting the flag to true");
                TimeUnit.SECONDS.sleep(1);
                waiter.setFlag(true);
                print("flag set to true");
                waiter.notifyAll();
            }
        } catch (InterruptedException e) {
            print(e);
        }
    }

}
public class E22 {
    public static void main(String[] args) {
        Waiter waiter = new Waiter();
        Action action = new Action(waiter);
        Thread t1 = new Thread(){
            @Override
            public void run() {
                waiter.performer();
            }
        };
        Thread t2 = new Thread(){
            @Override
            public void run() {
                action.flagger();
            }
        };
        t1.start();
        t2.start();
        try {
            TimeUnit.SECONDS.sleep(3);
            t1.interrupt();
            t2.interrupt();
        } catch (InterruptedException e) {
            print(e);
        }
        Thread t3 = new Thread(){
            @Override
            public void run() {
                waiter.waitformer();
            }
        };
        t3.start();
        Thread t4 = new Thread(){
            @Override
            public void run() {
                action.flagger();
            }
        };
        t4.start();
        try {
            TimeUnit.SECONDS.sleep(3);
            t3.interrupt();
            t4.interrupt();
        } catch (InterruptedException e) {
            print(e);
        }
    }
}
