package concurrency;

//Exercise 2: (2) Following the form of generics/Fibonacci.java, create a task that produces a
// sequence of n Fibonacci numbers, where n is provided to the constructor of the task.
// Create a number of these tasks and drive them using threads.

import net.mindview.util.*;
import java.util.*;
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

public class E2 {
    public static void main(String[] args) {
        for (int i = 0; i < 4; i++) {
            new Thread(new Fibonacci(7)).start();
        }
    }
}
