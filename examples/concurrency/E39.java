package concurrency;

//: concurrency/FastSimulation.java
//Exercise 39: (6) Does FastSimulation.java make reasonable assumptions? Try changing the array to ordinary ints
// instead of AtomicInteger and using Lock mutexes. Compare the performance between the two versions of the program.
import java.util.concurrent.*;
import java.util.concurrent.atomic.*;
import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

import static net.mindview.util.Print.*;

class FastSimulation {
    static final int N_ELEMENTS = 100;
    static final int N_GENES = 30;
    static final int N_EVOLVERS = 50;
    private static ExecutorService exec = Executors.newFixedThreadPool(N_EVOLVERS);
    private static CountDownLatch latch = new CountDownLatch(N_EVOLVERS);
    static final AtomicInteger[][] GRID =
            new AtomicInteger[N_ELEMENTS][N_GENES];
    static Random rand = new Random(47);
//    public FastSimulation(ExecutorService executorService) {
//        this.exec = executorService;
//    }
    static class Evolver implements Runnable {
        public void run() {
            while(!Thread.interrupted()) {
                // Randomly select an element to work on:
                int element = rand.nextInt(N_ELEMENTS);
                for(int i = 0; i < N_GENES; i++) {
                    int previous = element - 1;
                    if(previous < 0) previous = N_ELEMENTS - 1;
                    int next = element + 1;
                    if(next >= N_ELEMENTS) next = 0;
                    int oldvalue = GRID[element][i].get();
                    // Perform some kind of modeling calculation:
                    int newvalue = oldvalue +
                            GRID[previous][i].get() + GRID[next][i].get();
                    newvalue /= 3; // Average the three values
                    if(!GRID[element][i]
                            .compareAndSet(oldvalue, newvalue)) {
                        // Policy here to deal with failure. Here, we
                        // just report it and ignore it; our model
                        // will eventually deal with it.
                        print("Old value changed from " + oldvalue);
                    }
                    latch.countDown();
                }
            }
        }
    }
    public static long test() throws Exception {
        for(int i = 0; i < N_ELEMENTS; i++)
            for(int j = 0; j < N_GENES; j++)
                GRID[i][j] = new AtomicInteger(rand.nextInt(1000));
        long start = System.nanoTime();
        for(int i = 0; i < N_EVOLVERS; i++)
            exec.execute(new Evolver());
        TimeUnit.SECONDS.sleep(5);
        latch.await();
        exec.shutdownNow();
        long runTime = System.nanoTime() - start;
        runTime /= 50;
        return runTime;
    }
} /* (Execute to see output) *///:~

class IntFastSimulation {
    static final int N_ELEMENTS = 100;
    static final int N_GENES = 30;
    static final int N_EVOLVERS = 50;
    private static ExecutorService exec = Executors.newFixedThreadPool(N_EVOLVERS);
    private static CountDownLatch latch = new CountDownLatch(N_EVOLVERS);
    static final Integer[][] GRID =
            new Integer[N_ELEMENTS][N_GENES];
    static Random rand = new Random(47);
//    public IntFastSimulation(ExecutorService executorService) {
//        this.exec = executorService;
//    }
    static class Evolver implements Runnable {
        private ReentrantLock lock = new ReentrantLock();
        public void run() {
            while(!Thread.interrupted()) {
                // Randomly select an element to work on:
                int element = rand.nextInt(N_ELEMENTS);
                for(int i = 0; i < N_GENES; i++) {
                    try {
                        lock.lock();
                        int previous = element - 1;
                        if (previous < 0) previous = N_ELEMENTS - 1;
                        int next = element + 1;
                        if (next >= N_ELEMENTS) next = 0;
                        int oldvalue = GRID[element][i];
                        // Perform some kind of modeling calculation:
                        int newvalue = oldvalue +
                                GRID[previous][i] + GRID[next][i];
                        newvalue /= 3; // Average the three values
                        latch.countDown();
                    } finally {
                        lock.unlock();
                    }
                }
            }
        }
    }
    public static long test() throws Exception {
        for(int i = 0; i < N_ELEMENTS; i++)
            for(int j = 0; j < N_GENES; j++)
                GRID[i][j] = new Integer(rand.nextInt(1000));
        long start = System.nanoTime();
        for(int i = 0; i < N_EVOLVERS; i++) {
            exec.execute(new Evolver());
        }
        TimeUnit.SECONDS.sleep(5);
        latch.await();
        exec.shutdownNow();
        long runTime = System.nanoTime() - start;
        runTime /= 50;
        return runTime;
    }
}

public class E39 {
    public static void runTest() throws Exception{
        long atomicTestTime = FastSimulation.test();
        long intTestTime = IntFastSimulation.test();
        print("atomic test time: " + atomicTestTime +
                " int test time: " + intTestTime);
    }
    public static void main(String[] args) throws Exception {
        runTest();
    }
}
