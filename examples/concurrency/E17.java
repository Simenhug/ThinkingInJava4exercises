package concurrency;

import java.util.concurrent.*;
import java.util.*;
import static net.mindview.util.Print.*;

//Exercise 17: (2) Create a radiation counter that can have any number of remote sensors.
class Radiation{
    private volatile int intensity = 0;
    private Random random = new Random(99);
    public synchronized int getIntensity(){ return intensity;}
    public synchronized int increment(){
        return ++intensity;
    }
}
class Sensors implements Runnable{
    private volatile static Radiation radiation = new Radiation();
    private int level = 0;
    private final int id;
    private static volatile Boolean canceled = false;

    public static synchronized void cancel(){canceled = true;}
    @Override
    public void run() {
        while (!canceled) {
            synchronized (this) {
                ++level;
                radiation.increment();
                print(this + " total radiation intensity: " + radiation.getIntensity());
            }
        }
    }
    @Override
    public String toString(){
        return "Sensor " + id + " level: " + level;
    }

    public Sensors(int idn) {
        id = idn;
    }
}
public class E17 {
    public static void main(String[] args) {
        ExecutorService execu = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            execu.execute(new Sensors(i));
        }
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            print(e);
        }
        Sensors.cancel();
        execu.shutdown();
        print("Radiation scan completed");
    }
}
