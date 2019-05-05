package concurrency;

import java.util.concurrent.*;
import static net.mindview.util.Print.print;
import static net.mindview.util.Print.printnb;

//Exercise 21: (2) Create two Runnables, one with a run( ) that starts and calls wait( ).
// The second class should capture the reference of the first Runnable object.
// Its run( ) should call notifyAll( ) for the first task after some number of
// seconds have passed so that the first task can display a message. Test your classes using an Executor.
class Simen implements Runnable{
    private volatile boolean accepted = false;

    @Override
    public synchronized void run() {
        try {
            while (!accepted) {
                print("Would you like to be my other half?");
                wait();
                print("She said yes!");
            }
        } catch (InterruptedException e) {
            print(e);
        }
    }
    public synchronized void accept(){
        accepted = true;
    }
}
class Della implements Runnable{
    private Simen simen;

    public Della(Simen simem) {
        simen = simem;
    }

    @Override
    public void run() {
        try {
            synchronized (simen) {
                print("Della's blushing ... she lowered her head");
                TimeUnit.SECONDS.sleep(1);
                simen.notifyAll();
                simen.accept();
            }
        } catch (InterruptedException e) {
            print(e);
        }
    }
}
public class E21 {
    public static void main(String[] args) {
        Simen simen = new Simen();
        Della della = new Della(simen);
        Thread Sim = new Thread(simen);
        Thread Del = new Thread(della);
        Sim.start();
        Del.start();
        try {
            TimeUnit.SECONDS.sleep(2);
            Sim.interrupt();
            Del.interrupt();
        } catch (InterruptedException e) {
            print(e);
        }
    }
}
