package concurrency;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static net.mindview.util.Print.print;

//Exercise 14: (4) Demonstrate that java.util.Timer scales to large numbers
// by creating a program that generates many
// Timer objects that perform some simple task when the timeout completes.
public class E14 implements Runnable{
    private int timer;
    public E14(int time){
        timer = time;
    }
    @Override
    public void run() {
        new Timer().schedule(new TimerTask() {
            public void run() {
                print("I've been waiting for you! " + timer);
                Thread.yield();
            }
        }, timer);
    }

    public static void main(String[] args) {
        Random random = new Random(99);
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i =0; i < 10; i++) {
            exec.execute(new E14(random.nextInt(10000)));
        }
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            print(e);
        }finally {
            exec.shutdown();
            System.exit(0);
        }
    }
}
