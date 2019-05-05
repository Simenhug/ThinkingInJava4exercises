package concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static net.mindview.util.Print.print;

//Exercise 1: (2) Implement a Runnable. Inside run( ), print a message, and then call yield( ).
// Repeat this three times, and then return from run( ).
// Put a startup message in the constructor and a shutdown message when the task terminates.
// Create a number of these tasks and drive them using threads.

//Exercise 3: (1) Repeat Exercise 1 using the different types of executors shown in this section.
public class E3 implements Runnable{
    static int count = 0;
    final int id = count++;
    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            print("# " + id + " executing " + i + " step");
            Thread.yield();
        }
        print("# " + id + " job done");
        return;
    }
    public E3(){
        print("creating E1 object " + id);
    }

    public static void main(String[] args) {
        ExecutorService cached = Executors.newCachedThreadPool();
        for (int i = 0; i < 3; i++) {
            cached.execute(new E3());
        }
        cached.shutdown();
        ExecutorService fixed = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 3; i++) {
            fixed.execute(new E3());
        }
        fixed.shutdown();
        ExecutorService single = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 3; i++) {
            single.execute(new E3());
        }
        single.shutdown();
    }
}
