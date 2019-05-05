package concurrency;
import java.util.concurrent.*;
import java.util.*;
import static net.mindview.util.Print.*;

class Philosopher31 implements Runnable {
    private Chopstick left;
    private Chopstick right;
    private LinkedBlockingQueue<Chopstick> bin;
    private final int id;
    private final int ponderFactor;
    private Random rand = new Random(47);
    private void pause() throws InterruptedException {
        if(ponderFactor == 0) return;
        TimeUnit.MILLISECONDS.sleep(rand.nextInt(ponderFactor * 250));
    }
    public Philosopher31(Chopstick left, Chopstick right,
                         LinkedBlockingQueue<Chopstick> bin, int ident, int ponder) {
        this.left = left;
        this.right = right;
        this.bin = bin;
        id = ident;
        ponderFactor = ponder;
    }
    public void run() {
        try {
            while(!Thread.interrupted()) {
                print(this + " " + "thinking");
                pause();
                // Philosopher becomes hungry
                print(this + " taking first, right chopstick");
                right = bin.take();
                right.take();
                print(this + " taking second, left chopstick");
                left = bin.take();
                left.take();
                print(this + " eating");
                pause();
                print(this + " returning right");
                bin.put(right);
                right.drop();
                print(this + " returning left");
                bin.put(left);
                right.drop();
            }
        } catch(InterruptedException e) {
            print(this + " " + "exiting via interrupt");
        }
    }
    public String toString() { return "Philosopher " + id; }
}

public class E31web {
    public static void main(String[] args) throws Exception {
        int ponder = 5;
        if(args.length > 0)
            ponder = Integer.parseInt(args[0]);
        int size = 5;
        if(args.length > 1)
            size = Integer.parseInt(args[1]);
        ExecutorService exec = Executors.newCachedThreadPool();
        // chopstick bin:
        LinkedBlockingQueue<Chopstick> bin = new LinkedBlockingQueue<Chopstick>();
        Chopstick[] sticks = new Chopstick[size];
        for(int i = 0; i < size; i++) {
            sticks[i] = new Chopstick();
            bin.put(sticks[i]);
        }
        for(int i = 0; i < size; i++)
            exec.execute(new Philosopher31(sticks[i], sticks[(i + 1) % size], bin, i, ponder));
        if(args.length == 3 && args[2].equals("timeout"))
            TimeUnit.SECONDS.sleep(5);
        else {
            System.out.println("Press 'Enter' to quit");
            System.in.read();
        }
        exec.shutdownNow();
    }
}