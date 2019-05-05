package concurrency;

import java.net.*;
import java.nio.*;
import java.nio.channels.*;
import java.util.concurrent.*;
import java.io.*;
import static net.mindview.util.Print.*;

//Exercise 18: (2) Create a non-task class with a method that calls sleep( ) for a long interval.
// Create a task that calls the method in the non-task class. In main( ), start the task,
// then call interrupt( ) to terminate it. Make sure that the task shuts down safely.
class NonTask{
    public static void sleepLongTime(){
        print("non-task starting sleep now");
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            print("non-task sleep interrupted");
        }
    }
}
class IsTask implements Runnable{
    @Override
    public void run() {
        print("running sleep() in a task");
        NonTask.sleepLongTime();
    }
}
public class E18 {
    public static void main(String[] args) {
        IsTask it = new IsTask();
        ExecutorService exec = Executors.newCachedThreadPool();
        Future<?> future = exec.submit(it);
        print("main thread waiting 1 second before interrupt()");
        try{
            TimeUnit.SECONDS.sleep(1);
        }catch (InterruptedException e){
            print("main thread interrupted");
        }
        future.cancel(true);
        exec.shutdown();
        System.exit(0);
    }
}
