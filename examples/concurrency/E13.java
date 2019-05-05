//: concurrency/SerialNumberChecker.java
// Operations that may seem safe are not,
// when threads are present.
// {Args: 4}
package concurrency;
import java.util.concurrent.*;

//Exercise 13: (1) Repair SerialNumberChecker.java using the synchronized keyword.
// Can you demonstrate that it is now correct?
// Reuses storage so we don't run out of memory:
class CircularSet {
    private volatile int[] array;
    private int len;
    private volatile int index = 0;
    public CircularSet(int size) {
        array = new int[size];
        len = size;
        // Initialize to a value not produced
        // by the SerialNumberGenerator:
        for(int i = 0; i < size; i++)
            array[i] = -1;
    }
    public synchronized void add(int i) {
        array[index] = i;
        // Wrap index and write over old elements:
        index = ++index % len;
    }
    public synchronized boolean contains(int val) {
        for(int i = 0; i < len; i++)
            if(array[i] == val) return true;
        return false;
    }
}

class SerialNumberChecker {
    private static final int SIZE = 10;
    public static int getSIZE() {
        return SIZE;
    }
    private volatile static CircularSet serials =
            new CircularSet(1000);
    public static ExecutorService exec =
            Executors.newCachedThreadPool();
    static class SerialChecker implements Runnable {
        public void run() {
            while(true) {
                int serial =
                        SerialNumberGenerator.nextSerialNumber();
                if(serials.contains(serial)) {
                    System.out.println("Duplicate: " + serial);
                    System.out.println("Aborting");
                    System.exit(0);
                }
                serials.add(serial);
            }
        }
    }
}
public class E13{
    public static void main(String[] args) throws Exception {
        SerialNumberChecker snc = new SerialNumberChecker();
        for(int i = 0; i < snc.getSIZE(); i++)
            snc.exec.execute(new SerialNumberChecker.SerialChecker());
        // Stop after n seconds if there's an argument:
        if(args.length > 0) {
            TimeUnit.SECONDS.sleep(new Integer(args[0]));
            System.out.println("No duplicates detected");
            System.exit(0);
        }
    }
}