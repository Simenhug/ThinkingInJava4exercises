package concurrency;
import static net.mindview.util.Print.*;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

//Exercise 6: (2) Create a task that sleeps for a random amount of time between 1 and 10 seconds,
// then displays its sleep time and exits. Create and run a quantity (given on the command line) of these tasks.
class SleepingDella implements Runnable{
    int sleepTime;
    static int count = 0;
    final int id = count++;
    public SleepingDella(Random random) {
        sleepTime = random.nextInt(1000);
    }
    public void run(){
        try {
            TimeUnit.MILLISECONDS.sleep(sleepTime);
            print("Della " + id + " slept " + sleepTime + " milliseconds");
        } catch (InterruptedException e) {
            print(e);
        }
    }
}
public class E6 {
    public static void main(String[] args) {
        ExecutorService simen = Executors.newCachedThreadPool();
        Random random = new Random(99);
        try {
            for (int i = 0; i < Integer.parseInt(args[0]); i++) {
                simen.execute(new SleepingDella(random));
            }
        } finally {
            simen.shutdown();
        }
    }
}
