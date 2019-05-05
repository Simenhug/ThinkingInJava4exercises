//: concurrency/AtomicityTest.java
package concurrency;
import java.util.concurrent.*;

//Exercise 12: (3) Repair AtomicityTest.java using the synchronized keyword.
// Can you demonstrate that it is now correct?
class AtomicityTest implements Runnable {
    private int i = 0;
    public synchronized int getValue() { return i; }
    private synchronized void evenIncrement() { i++; i++; }
    public void run() {
        while(true)
            evenIncrement();
    }
}
public class E12{
    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        AtomicityTest at = new AtomicityTest();
        exec.execute(at);
        System.out.println("Control + C to exit");
        while(true) {
            int value = at.getValue();
            if(value % 2 != 0) {
                System.out.println(value);
                System.out.println("aborting");
                System.exit(0);
            }
        }
    }
}
