package concurrency;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
// Exercise 33: (7) Modify GreenhouseScheduler33.java so that it uses a DelayQueue instead of a ScheduledExecutor.

import java.util.ArrayList;
import java.util.*;
import static java.util.concurrent.TimeUnit.*;
import static net.mindview.util.Print.print;
import java.util.Calendar;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

class DelayedTask implements Delayed, Runnable{
    private Runnable task;
    private final int delta;
    private final long trigger;

    public DelayedTask(Runnable task, int delayMilliseconds) {
        this.task = task;
        delta = delayMilliseconds;
        trigger = System.nanoTime() + NANOSECONDS.convert(delta, MILLISECONDS);
    }
    public DelayedTask(Runnable task, String repeat, long nextTrigger) {
        this.task = task;
        delta = 0;
        trigger = nextTrigger;
    }
    @Override
    public void run() {
        task.run();
    }

    @Override
    public int compareTo(Delayed other) {
        DelayedTask that = (DelayedTask)other;
        if(trigger < that.trigger) return -1;
        if(trigger > that.trigger) return 1;
        return 0;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(trigger - System.nanoTime(), NANOSECONDS);
    }

    @Override
    public String toString() {
        return task.toString() + " trigger " + trigger;
    }
}
class DelayedTaskScheduler implements Runnable{
    private long nextTrigger;
    private long initialDelay;
    private long period;
    private Runnable task;
    DelayQueue<DelayedTask> scheduler;

    public DelayedTaskScheduler(Runnable task, long initialDelay, long period, DelayQueue<DelayedTask> queue) {
        this.task = task;
        this.initialDelay = NANOSECONDS.convert(initialDelay, MILLISECONDS);
        this.period = NANOSECONDS.convert(period, MILLISECONDS);
        scheduler = queue;
    }

    @Override
    public void run() {
        nextTrigger = System.nanoTime() + initialDelay;
        try {
            while (!Thread.interrupted()) {
                scheduler.put(new DelayedTask(task, "repeat", nextTrigger));
                nextTrigger += period;
            }
        } finally {
            print("TaskScheduler " + task + " interrupted");
        }
    }
}
class DelayedTaskConsumer implements Runnable{
    private DelayQueue<DelayedTask> queue;
    public DelayedTaskConsumer(DelayQueue<DelayedTask> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                queue.take().run();
            }
        } catch (InterruptedException e) {
            print("DelayedTask Queue finished");
        }
    }
}
class GreenhouseScheduler33 {
    private volatile boolean light = false;
    private volatile boolean water = false;
    private String thermostat = "Day";
    ExecutorService delayTaskScheduler;
    DelayQueue<DelayedTask> scheduler = new DelayQueue<>();
    //ScheduledThreadPoolExecutor scheduler = new ScheduledThreadPoolExecutor(10);
    private Calendar lastTime = Calendar.getInstance();
    private float lastTemp;
    private int tempDirection;
    private float lastHumidity;
    private int humidityDirection;
    private Random rand;
    List<GreenhouseScheduler33.DataPoint> data;

    public GreenhouseScheduler33(ExecutorService exec) {
        delayTaskScheduler = exec;
        this.lastTime.set(12, 30);
        this.lastTime.set(13, 0);
        this.lastTemp = 65.0F;
        this.tempDirection = 1;
        this.lastHumidity = 50.0F;
        this.humidityDirection = 1;
        this.rand = new Random(47L);
        this.data = Collections.synchronizedList(new ArrayList());
    }

    public synchronized String getThermostat() {
        return this.thermostat;
    }

    public synchronized void setThermostat(String value) {
        this.thermostat = value;
    }

//    public void schedule(Runnable event, long delay) {
//        this.scheduler.schedule(event, delay, TimeUnit.MILLISECONDS);
//    }
    public void schedule(Runnable event, long delay){
        scheduler.put(new DelayedTask(event, (int)delay));
    }

//    public void repeat(Runnable event, long initialDelay, long period) {
//        this.scheduler.scheduleAtFixedRate(event, initialDelay, period, TimeUnit.MILLISECONDS);
//    }

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        GreenhouseScheduler33 gh = new GreenhouseScheduler33(exec);
        gh.schedule(gh.new Terminate(), 5000L);
        exec.execute(new DelayedTaskScheduler(gh.new Bell(), 0L, 1000L, gh.scheduler));
        exec.execute(new DelayedTaskScheduler(gh.new ThermostatNight(), 0L, 2000L, gh.scheduler));
        exec.execute(new DelayedTaskScheduler(gh.new LightOn(), 0L, 200L, gh.scheduler));
        exec.execute(new DelayedTaskScheduler(gh.new LightOff(), 0L, 400L, gh.scheduler));
        exec.execute(new DelayedTaskScheduler(gh.new WaterOn(), 0L, 600L, gh.scheduler));
        exec.execute(new DelayedTaskScheduler(gh.new WaterOff(), 0L, 800L, gh.scheduler));
        exec.execute(new DelayedTaskScheduler(gh.new ThermostatDay(), 0L, 1400L, gh.scheduler));
        exec.execute(new DelayedTaskScheduler(gh.new CollectData(), 500L, 500L, gh.scheduler));
        exec.execute(new DelayedTaskConsumer(gh.scheduler));
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
        } finally {
            System.exit(0);
        }
    }

    class CollectData implements Runnable {
        CollectData() {
        }

        public void run() {
            System.out.println("Collecting data");
            GreenhouseScheduler33 var1 = GreenhouseScheduler33.this;
            synchronized(GreenhouseScheduler33.this) {
                GreenhouseScheduler33.this.lastTime.set(12, GreenhouseScheduler33.this.lastTime.get(12) + 30);
                if (GreenhouseScheduler33.this.rand.nextInt(5) == 4) {
                    GreenhouseScheduler33.this.tempDirection = -GreenhouseScheduler33.this.tempDirection;
                }

                GreenhouseScheduler33.this.lastTemp = GreenhouseScheduler33.this.lastTemp + (float)GreenhouseScheduler33.this.tempDirection * (1.0F + GreenhouseScheduler33.this.rand.nextFloat());
                if (GreenhouseScheduler33.this.rand.nextInt(5) == 4) {
                    GreenhouseScheduler33.this.humidityDirection = -GreenhouseScheduler33.this.humidityDirection;
                }

                GreenhouseScheduler33.this.lastHumidity = GreenhouseScheduler33.this.lastHumidity + (float)GreenhouseScheduler33.this.humidityDirection * GreenhouseScheduler33.this.rand.nextFloat();
                GreenhouseScheduler33.this.data.add(new GreenhouseScheduler33.DataPoint((Calendar)GreenhouseScheduler33.this.lastTime.clone(), GreenhouseScheduler33.this.lastTemp, GreenhouseScheduler33.this.lastHumidity));
            }
        }
    }

    static class DataPoint {
        final Calendar time;
        final float temperature;
        final float humidity;

        public DataPoint(Calendar d, float temp, float hum) {
            this.time = d;
            this.temperature = temp;
            this.humidity = hum;
        }

        public String toString() {
            return this.time.getTime() + String.format(" temperature: %1$.1f humidity: %2$.2f", this.temperature, this.humidity);
        }
    }

    class Terminate implements Runnable {
        Terminate() {
        }

        public void run() {
            System.out.println("Terminating");
            GreenhouseScheduler33.this.delayTaskScheduler.shutdownNow();
            (new Thread() {
                public void run() {
                    Iterator var1 = GreenhouseScheduler33.this.data.iterator();

                    while(var1.hasNext()) {
                        GreenhouseScheduler33.DataPoint d = (GreenhouseScheduler33.DataPoint)var1.next();
                        System.out.println(d);
                    }

                    for (DelayedTask dt : GreenhouseScheduler33.this.scheduler) {
                        print(dt);
                    }

                }
            }).start();
        }
    }

    class Bell implements Runnable {
        Bell() {
        }

        public void run() {
            System.out.println("Bing!");
        }
    }

    class ThermostatDay implements Runnable {
        ThermostatDay() {
        }

        public void run() {
            System.out.println("Thermostat to day setting");
            GreenhouseScheduler33.this.setThermostat("Day");
        }
    }

    class ThermostatNight implements Runnable {
        ThermostatNight() {
        }

        public void run() {
            System.out.println("Thermostat to night setting");
            GreenhouseScheduler33.this.setThermostat("Night");
        }
    }

    class WaterOff implements Runnable {
        WaterOff() {
        }

        public void run() {
            System.out.println("Turning greenhouse water off");
            GreenhouseScheduler33.this.water = false;
        }
    }

    class WaterOn implements Runnable {
        WaterOn() {
        }

        public void run() {
            System.out.println("Turning greenhouse water on");
            GreenhouseScheduler33.this.water = true;
        }
    }

    class LightOff implements Runnable {
        LightOff() {
        }

        public void run() {
            System.out.println("Turning off lights");
            GreenhouseScheduler33.this.light = false;
        }
    }

    class LightOn implements Runnable {
        LightOn() {
        }

        public void run() {
            System.out.println("Turning on lights");
            GreenhouseScheduler33.this.light = true;
        }
    }
}

public class E33 {
    public static void main(String[] args) {
        GreenhouseScheduler33.main(args);
    }
}