//: concurrency/SimplePriorities.java
// Shows the use of thread priorities.
package concurrency;
import java.util.concurrent.*;

//Exercise 9: (3) Modify SimplePriorities.java so that a custom ThreadFactory sets the priorities of the threads.
class DellaThreadFactory implements ThreadFactory{
    int priority;
    public Thread newThread(Runnable runnable) {
        Thread t = new Thread(runnable);
        t.setPriority(priority);
        return t;
    }
    public DellaThreadFactory(Integer priority) {
        this.priority = priority;
    }
}
public class E9 implements Runnable {
    private int countDown = 5;
    private volatile double d; // No optimization
    private int priority;
    public E9() {}
    public String toString() {
        return Thread.currentThread() + ": " + countDown;
    }
    public void run() {
        while(true) {
            // An expensive, interruptable operation:
            for(int i = 1; i < 100000; i++) {
                d += (Math.PI + Math.E) / (double)i;
                if(i % 1000 == 0)
                    Thread.yield();
            }
            System.out.println(this);
            if(--countDown == 0) return;
        }
    }
    public static void main(String[] args) {
        ExecutorService execMax = Executors.newCachedThreadPool(new DellaThreadFactory(Thread.MAX_PRIORITY));
        ExecutorService execMin = Executors.newCachedThreadPool(new DellaThreadFactory(Thread.MIN_PRIORITY));
        for(int i = 0; i < 5; i++)
            execMin.execute(
                    new E9());
        execMax.execute(
                new E9());
        execMin.shutdown();
        execMax.shutdown();
    }
} /* Output: (70% match)
Thread[pool-1-thread-6,10,main]: 5
Thread[pool-1-thread-6,10,main]: 4
Thread[pool-1-thread-6,10,main]: 3
Thread[pool-1-thread-6,10,main]: 2
Thread[pool-1-thread-6,10,main]: 1
Thread[pool-1-thread-3,1,main]: 5
Thread[pool-1-thread-2,1,main]: 5
Thread[pool-1-thread-1,1,main]: 5
Thread[pool-1-thread-5,1,main]: 5
Thread[pool-1-thread-4,1,main]: 5
...
*///:~
