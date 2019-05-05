package concurrency;

//Exercise 2: (2) Following the form of generics/Fibonacci.java, create a task that produces a
// sequence of n Fibonacci numbers, where n is provided to the constructor of the task.
// Create a number of these tasks and drive them using threads.

//Exercise 5: (2) Modify Exercise 2 so that the task is a Callable that sums the values of all the Fibonacci numbers.
// Create several tasks and display the results.
import net.mindview.util.*;
import java.util.*;
import java.util.concurrent.*;

import static net.mindview.util.Print.*;

class Fibonacci implements Generator<Integer>, Callable<Integer> {
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
    public Integer call(){
        int sum = 0;
        for(int i = 0; i < length; i++) {
            int nextFib = this.next();
            sum += nextFib;
            Thread.yield();
        }
        print("Fibonacci " + id + " : " + "done");
        return sum;
    }
}

public class E5 {
    public static void main(String[] args) {
        ExecutorService cached = Executors.newCachedThreadPool();
        ArrayList<Future<Integer>> futures = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            futures.add(cached.submit(new Fibonacci(i + 5)));
        }
        for (Future<Integer> ints : futures) {
            try {
                print(ints.get());
            } catch (InterruptedException e) {
                print(e);
                return;
            } catch (ExecutionException e) {
                print(e);
                return;
            } finally {
                cached.shutdown();;
            }
        }
    }
}
