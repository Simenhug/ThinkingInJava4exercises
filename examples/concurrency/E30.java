package concurrency;

//: concurrency/PipedIO.java
// Using pipes for inter-task I/O
//Exercise 30: (1) Modify PipedIO.java to use a BlockingQueue instead of a pipe.
import java.util.concurrent.*;
import java.io.*;
import java.util.*;
import static net.mindview.util.Print.*;

class Sender implements Runnable {
    private Random rand = new Random(47);
    private BlockingQueue<Character> out;
    public Sender (BlockingQueue<Character> queue) { out = queue; }
    public BlockingQueue<Character> getPipedWriter() { return out; }
    public void run() {
        try {
            while(true)
                for(char c = 'A'; c <= 'z'; c++) {
                    out.put(c);
                    TimeUnit.MILLISECONDS.sleep(rand.nextInt(500));
                }
        } catch(InterruptedException e) {
            print(e + " Sender sleep interrupted");
        }
    }
}

class Receiver implements Runnable {
    private BlockingQueue<Character> in;
    public Receiver(Sender sender) {
        in = sender.getPipedWriter();
    }
    public void run() {
        try {
            while(true) {
                // Blocks until characters are there:
                printnb("Read: " + (char)in.take() + ", ");
            }
        } catch(InterruptedException e) {
            print(e + " Receiver read exception");
        }
    }
}

public class E30 {
    public static void main(String[] args) throws Exception {
        Sender sender = new Sender(new LinkedBlockingDeque<>());
        Receiver receiver = new Receiver(sender);
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(sender);
        exec.execute(receiver);
        TimeUnit.SECONDS.sleep(7);
        exec.shutdownNow();
    }
} /* Output: (65% match)
Read: A, Read: B, Read: C, Read: D, Read: E, Read: F, Read: G, Read: H, Read: I, Read: J, Read: K, Read: L, Read: M, java.lang.InterruptedException: sleep interrupted Sender sleep interrupted
java.io.InterruptedIOException Receiver read exception
*///:~

