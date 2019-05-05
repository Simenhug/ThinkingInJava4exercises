package concurrency;

//: concurrency/ExchangerDemo34.java
//Exercise 34: (1) Modify ExchangerDemo34.java to use your own class instead of Skinny.
import java.util.concurrent.*;
import java.util.*;
import net.mindview.util.*;

//class Skinny {
//    private volatile double d; // Prevent optimization
//    private static int counter = 0;
//    private final int id = counter++;
//    public Skinny() {
//        // Expensive, interruptible operation:
//        for(int i = 1; i < 10000; i++) {
//            d += (Math.PI + Math.E) / (double)i;
//        }
//    }
//    public void operation() { System.out.println(this + " losing weight"); }
//    public String toString() { return "Skinny id: " + id; }
//}

class ExchangerProducer<T> implements Runnable {
    private Generator<T> generator;
    private Exchanger<List<T>> exchanger;
    private List<T> holder;
    ExchangerProducer(Exchanger<List<T>> exchg,
                      Generator<T> gen, List<T> holder) {
        exchanger = exchg;
        generator = gen;
        this.holder = holder;
    }
    public void run() {
        try {
            while(!Thread.interrupted()) {
                for(int i = 0; i < ExchangerDemo34.size; i++)
                    holder.add(generator.next());
                // Exchange full for empty:
                holder = exchanger.exchange(holder);
            }
        } catch(InterruptedException e) {
            // OK to terminate this way.
        }
    }
}

class ExchangerConsumer<T> implements Runnable {
    private Exchanger<List<T>> exchanger;
    private List<T> holder;
    private volatile T value;
    ExchangerConsumer(Exchanger<List<T>> ex, List<T> holder){
        exchanger = ex;
        this.holder = holder;
    }
    public void run() {
        try {
            while(!Thread.interrupted()) {
                holder = exchanger.exchange(holder);
                for(T x : holder) {
                    value = x; // Fetch out value
                    holder.remove(x); // OK for CopyOnWriteArrayList
                }
            }
        } catch(InterruptedException e) {
            // OK to terminate this way.
        }
        System.out.println("Final value: " + value);
    }
}

class ExchangerDemo34 {
    static int size = 10;
    static int delay = 5; // Seconds
    public static void main(String[] args) throws Exception {
        if(args.length > 0)
            size = new Integer(args[0]);
        if(args.length > 1)
            delay = new Integer(args[1]);
        ExecutorService exec = Executors.newCachedThreadPool();
        Exchanger<List<Skinny>> xc = new Exchanger<List<Skinny>>();
        List<Skinny>
                producerList = new CopyOnWriteArrayList<Skinny>(),
                consumerList = new CopyOnWriteArrayList<Skinny>();
        exec.execute(new ExchangerProducer<Skinny>(xc,
                BasicGenerator.create(Skinny.class), producerList));
        exec.execute(
                new ExchangerConsumer<Skinny>(xc,consumerList));
        TimeUnit.SECONDS.sleep(delay);
        exec.shutdownNow();
    }
} /* Output: (Sample)
Final value: Skinny id: 29999
*///:~
public class E34 {
    public static void main(String[] args) {
        try {
            ExchangerDemo34.main(args);
        } catch (Exception e) {
        }
    }
}
