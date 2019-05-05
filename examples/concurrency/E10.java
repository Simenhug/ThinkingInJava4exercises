package concurrency;

//Exercise 2: (2) Following the form of generics/Fibonacci.java, create a task that produces a
// sequence of n Fibonacci numbers, where n is provided to the constructor of the task.
// Create a number of these tasks and drive them using threads.

//Exercise 5: (2) Modify Exercise 2 so that the task is a Callable that sums the values of all the Fibonacci numbers.
// Create several tasks and display the results.

//Exercise 10: (4) Modify Exercise 5 following the example of the ThreadMethod class,
// so that runTask( ) takes an argument of the number of Fibonacci numbers to sum,
// and each time you call runTask( ) it returns the Future produced by the call to submit( ).
import net.mindview.util.*;
import java.util.*;
import java.util.concurrent.*;

import static net.mindview.util.Print.*;

class Fibonacci implements Generator<Integer>, Callable<Integer> {
    static int fibons = 0;
    final int id = fibons++;
    ExecutorService service;
    private int count = 0;
    private int size;
    List<Integer> integers = new ArrayList<Integer>();
    public Integer next() { return fib(count++); }
    private int fib(int n) {
        if(n < 2) return 1;
        return fib(n-2) + fib(n-1);
    }
    public Fibonacci(){
        print("creating Fibonacci " + id);
    }
    public Integer call() {
        int sum = 0;
        for (int i = 0; i < size; i++) {
            int nextFib = next();
            sum += nextFib;
            Thread.yield();
        }
        print("Fibonacci " + id + " : " + "done");
        return sum;
    }
    public Future<Integer> runTask(int size) {
        this.size = size;
        if (service == null) {
            service = Executors.newCachedThreadPool();
        }
        try {
            return service.submit(this);
        }finally {
            service.shutdown();
        }
    }
}

public class E10 {
    public static void main(String[] args) {
        ArrayList<Future<Integer>> futures = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            futures.add(new Fibonacci().runTask(i+5));
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
            }
        }
    }
}
