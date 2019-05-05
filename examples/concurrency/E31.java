package concurrency;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//
//Exercise 31: (8) Change DeadlockingDiningPhilosophers.java so that when a philosopher is done with its chopsticks,
// it drops them into a bin. When a philosopher wants to eat, it takes the next two available chopsticks from the bin.
// Does this eliminate the possibility of deadlock? Can you reintroduce deadlock by simply reducing the number of
// available chopsticks?

import net.mindview.util.Print;

import java.util.Random;
import java.util.concurrent.*;

class Chopstick {
    private boolean taken = false;
    public synchronized
    void take() throws InterruptedException {
        while(taken)
            wait();
        taken = true;
    }
    public synchronized void drop() {
        taken = false;
        notifyAll();
    }
} ///:~

class Philosopher31 implements Runnable {
    private static BlockingQueue<Chopstick> stickBin;
    private final int id;
    private final int ponderFactor;
    private Random rand = new Random(47L);

    private void pause() throws InterruptedException {
        if (this.ponderFactor != 0) {
            TimeUnit.MILLISECONDS.sleep((long)this.rand.nextInt(this.ponderFactor * 250));
        }
    }

    public Philosopher31(BlockingQueue<Chopstick> bin, int ident, int ponder) {
        stickBin = bin;
        this.id = ident;
        this.ponderFactor = ponder;
    }

    public void run() {
        while(true) {
            try {
                if (!Thread.interrupted()) {
                    Print.print(this + " thinking");
                    this.pause();
                    Chopstick first = stickBin.take();
                    first.take();
                    Print.print(this + " grabbing first one " + stickBin.size());
                    Chopstick second = stickBin.take();
                    second.take();
                    Print.print(this + " grabbing second one " + stickBin.size());
                    Print.print(this + " eating");
                    this.pause();
                    stickBin.put(first);
                    first.drop();
                    Print.print(this + " putting back first one " + stickBin.size());
                    stickBin.put(second);
                    second.drop();
                    Print.print(this + " putting back first one " + stickBin.size());
                    continue;
                }
            } catch (InterruptedException var2) {
                Print.print(this + " exiting via interrupt");
            }

            return;
        }
    }

    public String toString() {
        return "Philosopher " + this.id;
    }
}

public class E31 {

    public static void main(String[] args) throws Exception {
        int ponder = 5;
        if (args.length > 0) {
            ponder = Integer.parseInt(args[0]);
        }

        int size = 5;
        if (args.length > 1) {
            size = Integer.parseInt(args[1]);
        }

        ExecutorService exec = Executors.newCachedThreadPool();
        BlockingQueue<Chopstick> stickBin = new LinkedBlockingDeque<>();

        for(int i = 0; i < size; ++i) {
            stickBin.put(new Chopstick());
        }

        for(int i = 0; i < size; ++i) {
            exec.execute(new Philosopher31(stickBin, i, ponder));
        }

        if (args.length == 3 && args[2].equals("timeout")) {
            TimeUnit.SECONDS.sleep(5L);
        } else {
            System.out.println("Press 'Enter' to quit");
            System.in.read();
        }

        exec.shutdownNow();
    }
}
