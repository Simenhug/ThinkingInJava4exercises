package concurrency;

//Exercise 2: (2) Following the form of generics/Fibonacci.java, create a task that produces a
// sequence of n Fibonacci numbers, where n is provided to the constructor of the task.
// Create a number of these tasks and drive them using threads.

//Exercise 4: (1) Repeat Exercise 2 using the different types of executors shown in this section.
import net.mindview.util.*;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static net.mindview.util.Print.*;

class Fibonacci implements Generator<Integer>, Runnable {
    static int fibons = 0;
    final int id = fibons++;
    private int count = 0;
    private int length = 0;
    List<Integer> integers = new ArrayList<Integer>();
    public Integer next() { return fib(count++); }
    private int fib(int n) {
        if(n < 2) return 1;
        return fib(n-2) + fib(n-1);
    }
    public Fibonacci(int length){
        this.length = length;
        print("creating Fibonacci " + id);
    }
    public void run(){
        for(int i = 0; i < length; i++) {
            int nextFib = this.next();
            integers.add(nextFib);
            printnb("Fibonacci " + id+ " : " + integers + " \n");
            Thread.yield();
        }
        print("Fibonacci " + id + " : " + "done");
    }
}

public class E4 {
    public static void main(String[] args) {
        ExecutorService cached = Executors.newCachedThreadPool();
        for (int i = 0; i < 4; i++) {
            cached.execute(new Fibonacci(10));
        }
        cached.shutdown();
        ExecutorService fixed = Executors.newFixedThreadPool(4);
        for (int i = 0; i < 4; i++) {
            fixed.execute(new Fibonacci(10));
        }
        fixed.shutdown();
        ExecutorService single = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 4; i++) {
            single.execute(new Fibonacci(10));
        }
        single.shutdown();
    }
}
