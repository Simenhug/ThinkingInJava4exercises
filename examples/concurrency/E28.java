package concurrency;

//: concurrency/TestBlockingQueues.java
// {RunByHand}

// Exercise 28: (3) Modify TestBlockingQueues.java by adding a new task that places LiftOff on the BlockingQueue,
// instead of doing it in main( ).
import java.util.concurrent.*;
import java.io.*;
import static net.mindview.util.Print.*;

class LiftOffRunner implements Runnable {
    private BlockingQueue<LiftOff> rockets;
    private ExecutorService exec = Executors.newCachedThreadPool();
    public LiftOffRunner(BlockingQueue<LiftOff> queue) {
        rockets = queue;
    }
    @Override
    public void run() {
        try {
            while(!Thread.interrupted()) {
                TimeUnit.MILLISECONDS.sleep(700);
                LiftOff rocket = rockets.take();
                exec.execute(rocket); // Use this thread
            }
        } catch(InterruptedException e) {
            print("Waking from take()");
            exec.shutdownNow();
        }finally {
            exec.shutdownNow();
        }
        print("Exiting LiftOffRunner");
    }
}

class LiftOffLoader implements Runnable {
    private BlockingQueue<LiftOff> rockets;
    public LiftOffLoader(BlockingQueue<LiftOff> queue) { rockets = queue; }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                TimeUnit.MILLISECONDS.sleep(700);
                print("loading to " + rockets.getClass());
                rockets.put(new LiftOff(5));
            }
        } catch (InterruptedException e) {
            print("Interrupted during put()");
        }
    }
}

class TestBlockingQueues {
    static void getkey() {
        try {
            // Compensate for Windows/Linux difference in the
            // length of the result produced by the Enter key:
            new BufferedReader(
                    new InputStreamReader(System.in)).readLine();
        } catch(java.io.IOException e) {
            throw new RuntimeException(e);
        }
    }
    static void getkey(String message) {
        print(message);
        getkey();
    }
    static void
    test(String msg, BlockingQueue<LiftOff> queue) {
        print(msg);
        LiftOffRunner runner = new LiftOffRunner(queue);
        LiftOffLoader loader = new LiftOffLoader(queue);
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(loader);
        exec.execute(runner);
        getkey("Press 'Enter' (" + msg + ")");
        try {
            TimeUnit.SECONDS.sleep(1);
            exec.shutdownNow();
            System.exit(0);
        } catch (InterruptedException e) {
            print();
        }
        print("Finished " + msg + " test");
    }
    public static void main(String[] args) {
        test("LinkedBlockingQueue", // Unlimited size
                new LinkedBlockingQueue<LiftOff>());
        test("ArrayBlockingQueue", // Fixed size
                new ArrayBlockingQueue<LiftOff>(3));
        test("SynchronousQueue", // Size of 1
                new SynchronousQueue<LiftOff>());
    }
}

public class E28 {
    public static void main(String[] args) {
        TestBlockingQueues.main(args);
    }
}

