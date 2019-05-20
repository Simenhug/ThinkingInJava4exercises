package concurrency;

//Exercise 38: (3) Using the approach in CarBuilder.java, model the house-building story that was given in this chapter.

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.*;

import static net.mindview.util.Print.print;
import static net.mindview.util.Print.printnb;

//dig a base, build frames, Assemble: add plumbing, power, air conditioning, final decoration, done.
class House {
    private static int count;
    private final int id = count++;
    private boolean
    frame = false, plumbing = false, power = false, airConditioning = false, decoration = false;

    @Override
    public String toString() {
        return "House " + id + " [" + " frames: " + frame
                + " plumbing: " + plumbing
                + " power: " + power
                + " air conditioning: " + airConditioning
                + " decoration: " + decoration + " ]";
    }
    public synchronized void buildFrame(){
        frame = true;
    }
    public synchronized void buildPlumbing(){
        plumbing = true;
    }
    public synchronized void buildPower(){
        power = true;
    }
    public synchronized void buildAC(){
        airConditioning = true;
    }
    public synchronized void decorating(){
        decoration = true;
    }
}

class HouseQueue extends LinkedBlockingQueue<House> {}

class GroundDigger implements Runnable{
    private HouseQueue bases;
    public GroundDigger(HouseQueue base) {
        this.bases = base;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                House house = new House();
                bases.put(house);
                print(house + " finished ground digging");
                //Need some time to dig a base
                TimeUnit.MILLISECONDS.sleep(500);
            }
        } catch (InterruptedException e) {
            print("Ground digger interrupted");
        }
    }
}

class FrameBuilder implements Runnable{
    private HouseQueue bases;
    private HouseQueue frames;

    public FrameBuilder(HouseQueue bases, HouseQueue frames) {
        this.bases = bases;
        this.frames = frames;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                House base = bases.take();
                base.buildFrame();
                frames.put(base);
                print(base + " finished frame building");
            }
        } catch (InterruptedException e) {
            print("Frame biulder interrupted");
        }
    }
}

class Constructor implements Runnable{
    //four methods, build plumbing, power, AC, decorating. create a worker pool, cyclic barrier,
    //pool.hire(specific worker, this), queue frames, queue finished
    private HouseQueue frames, finished;
    CyclicBarrier barrier = new CyclicBarrier(5);
    public CyclicBarrier barrier() { return barrier; }
    BuilderPool builderPool;
    private House house;
    public House house() { return house; }
    public Constructor(BuilderPool bp, HouseQueue frames, HouseQueue finished) {
        this.builderPool = bp;
        this.frames = frames;
        this.finished = finished;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                house = frames.take();
                builderPool.hire(PlumbingBuilder.class, this);
                builderPool.hire(ACBuilder.class, this);
                builderPool.hire(PowerBuilder.class, this);
                builderPool.hire(DecorationBuilder.class, this);
                barrier.await();
                finished.put(house);
            }
        } catch (InterruptedException e) {
            print("Exiting Constructor via interrupt");
        } catch (BrokenBarrierException e) {
            throw new RuntimeException(e);
        }
        print("Constructor off");
    }
}

class Reporter implements Runnable {
    //finished queue, prints finished houses
    private HouseQueue finished;
    public Reporter(HouseQueue finished) {
        this.finished = finished;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                House house = finished.take();
                print(house + " is finished and ready for sale");
            }
        } catch (InterruptedException e) {
            print("Exiting Reporter via interrupt");
        }
    }
}

abstract class Builder implements Runnable {
    private BuilderPool pool;
    public Builder(BuilderPool p) { pool = p; }
    protected Constructor constructor;
    public Builder assignConstructor(Constructor constructor) {
        this.constructor = constructor;
        return this;
    }
    private boolean engage = false;
    public synchronized void engage() {
        engage = true;
        //print(this + " power on");
        notifyAll();
    }
    abstract protected void buildHouse();

    @Override
    public void run() {
        try {
            powerDown();
            while (!Thread.interrupted()) {
                //print(this + " before building: " + constructor);
                buildHouse();
                constructor.barrier.await();
                powerDown();
            }
        } catch (InterruptedException e) {
            print("Exiting " + this + " via interrupt");
        } catch (BrokenBarrierException e) {
            throw new RuntimeException(e);
        }
        print(this + " off");
    }
    private synchronized void powerDown() throws InterruptedException {
        engage = false;
        constructor = null;
        pool.release(this);
        while (engage == false) {
            wait();
        }
    }

    @Override
    public String toString() {
        return getClass().getName();
    }
}

class PlumbingBuilder extends Builder {
    public PlumbingBuilder(BuilderPool pool) { super(pool); }
    @Override
    protected void buildHouse() {
        print(this + " installing pluming system");
        constructor.house().buildPlumbing();
    }
}

class ACBuilder extends Builder {
    public ACBuilder(BuilderPool pool) { super(pool); }
    @Override
    protected void buildHouse() {
        print(this + " installing air conditioning system");
        constructor.house().buildAC();
    }
}

class PowerBuilder extends Builder {
    public PowerBuilder(BuilderPool pool) { super(pool); }
    @Override
    protected void buildHouse() {
        print(this + " installing power system");
        constructor.house().buildPower();
    }
}

class DecorationBuilder extends Builder {
    public DecorationBuilder(BuilderPool pool) { super(pool); }
    @Override
    protected void buildHouse() {
        print(this + " installing indoor design decorations");
        constructor.house().decorating();
    }
}

class BuilderPool {
    private Set<Builder> pool = new HashSet<>();
    public synchronized void add(Builder builder) {
        pool.add(builder);
        notifyAll();
    }

    public synchronized void
    hire(Class<? extends Builder> builderType, Constructor constructor)
        throws InterruptedException {
        for (Builder builder : pool) {
            if (builder.getClass().equals(builderType)) {
                pool.remove(builder);
                builder.assignConstructor(constructor);
                //print(builder + " assigned with " + constructor);
                builder.engage();
                return;
            }
        }
        wait();
        hire(builderType, constructor);
    }
    public synchronized void release(Builder builder) { add(builder); }
}
public class E38 {
    public static void main(String[] args) throws Exception{
        HouseQueue bases = new HouseQueue(),
                frames = new HouseQueue(),
                finished = new HouseQueue();
        ExecutorService exec = Executors.newCachedThreadPool();
        BuilderPool builderPool = new BuilderPool();
        exec.execute(new PlumbingBuilder(builderPool));
        exec.execute(new ACBuilder(builderPool));
        exec.execute(new PowerBuilder(builderPool));
        exec.execute(new DecorationBuilder(builderPool));
        exec.execute(new Constructor(builderPool, frames, finished));
        exec.execute(new Reporter(finished));
        exec.execute(new FrameBuilder(bases, frames));
        exec.execute(new GroundDigger(bases));
        TimeUnit.SECONDS.sleep(7);
        exec.shutdownNow();
    }
}
