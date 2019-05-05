package concurrency;

//: concurrency/WebServerSimulation.java
// Using queues and multithreading.
// {Args: 5}

//Exercise 35: (8) Modify WebServerSimulation.java so that it represents Web clients making requests of a fixed
// number of servers. The goal is to determine the load that the group of servers can handle.
import java.util.concurrent.*;
import java.util.*;

// Read-only objects don't require synchronization:
class WebClient {
    private final int serviceTime;
    public WebClient(int tm) { serviceTime = tm; }
    public int getServiceTime() { return serviceTime; }
    public String toString() {
        return "[" + serviceTime + "]";
    }
}

// Teach the WebClient line to display itself:
class WebClientLine extends LinkedBlockingQueue<WebClient> {
    public WebClientLine(int currentLineSize) {
        super(currentLineSize);
    }
    public WebClientLine(){}
    public String toString() {
        if(this.size() == 0)
            return "[Empty]";
        StringBuilder result = new StringBuilder();
        for(WebClient WebClient : this)
            result.append(WebClient);
        return result.toString();
    }
}

// Randomly add WebClients to a queue:
class WebClientGenerator implements Runnable {
    private WebClientLine WebClients;
    private static Random rand = new Random(99);
    private volatile static int newRequestInterval = rand.nextInt(300);
    public WebClientGenerator(WebClientLine cq) {
        WebClients = cq;
    }
    public void run() {
        try {
            while(!Thread.interrupted()) {
                TimeUnit.MILLISECONDS.sleep(newRequestInterval);
                WebClients.put(new WebClient(rand.nextInt(1000)));
                //System.out.println("new request generated");
            }
        } catch(InterruptedException e) {
            System.out.println("WebClientGenerator interrupted");
        }
        System.out.println("WebClientGenerator terminating");
    }
    public synchronized void raiseInterval(int stepUp){
        newRequestInterval += stepUp;
        System.out.println("new load interval " + getCurrentInterval());
    }
    public synchronized void dropInterval(int stepDown){
        newRequestInterval -= stepDown;
        System.out.println("new load interval " + getCurrentInterval());
    }
    public synchronized int getCurrentInterval(){
        return newRequestInterval;
    }
    public synchronized void forceSetInterval(){
        newRequestInterval = 50;
    }
}

class Server implements Runnable {
    private static int counter = 0;
    private final int id = counter++;
    private WebClientLine WebClients;
    public Server(WebClientLine cq) { WebClients = cq; }
    public void run() {
        try {
            while(!Thread.interrupted()) {
                WebClient WebClient = WebClients.take();
                TimeUnit.MILLISECONDS.sleep(
                        WebClient.getServiceTime());
            }
        } catch(InterruptedException e) {
            System.out.println(this + "interrupted");
        }
        System.out.println(this + "terminating");
    }

    public String toString() { return "Server " + id + " "; }
    public String shortString() { return "S" + id; }
}

class LoadManager implements Runnable {
    private ExecutorService exec;
    private WebClientLine WebClients;
    private WebClientGenerator generator;
    private int lastLoad;
    private int adjustmentPeriod;
    private static Random rand = new Random(47);
    public LoadManager(ExecutorService e,
                         WebClientLine WebClients, WebClientGenerator generator, int adjustmentPeriod) {
        exec = e;
        this.WebClients = WebClients;
        this.generator = generator;
        this.adjustmentPeriod = adjustmentPeriod;
        this.lastLoad = WebClients.size();
        Server 
                S1 = new Server(WebClients),
                S2 = new Server(WebClients),
                S3 = new Server(WebClients),
                S4 = new Server(WebClients);
        exec.execute(S1);
        exec.execute(S2);
        exec.execute(S3);
        exec.execute(S4);
        System.out.println("constructor done!");
    }
    public void adjustServerLoad() {
        int currentSize = WebClients.size();
        int newRequestsAdded = currentSize - lastLoad;
        System.out.println("current size " + currentSize + " current load " + lastLoad);
        System.out.println("size - load " + (currentSize-lastLoad));
        if (currentSize <4) {
            generator.forceSetInterval();
        }
        switch (newRequestsAdded) {
            case 0: return;
            case 1: generator.raiseInterval(25); return;
            case 2: generator.raiseInterval(50); return;
            case -1: generator.dropInterval(25); return;
            case -2: generator.dropInterval(50); return;
        }
        if (currentSize - lastLoad > 2 && generator.getCurrentInterval() < 300) {
            generator.raiseInterval(75);
            return;
        }
        if (lastLoad - currentSize > 2 && generator.getCurrentInterval() > 75) {
            generator.dropInterval(75);
            return;
        }
    }
    public void run() {
        try {
            while(!Thread.interrupted()) {
                TimeUnit.MILLISECONDS.sleep(adjustmentPeriod);
                adjustServerLoad();
                lastLoad = WebClients.size();
                System.out.println("Web requests: " + WebClients + " current load: " + lastLoad);
                System.out.println("current interval: " + generator.getCurrentInterval());
                System.out.println();
            }
        } catch(InterruptedException e) {
            System.out.println(this + "interrupted");
        }
        System.out.println(this + "terminating");
    }
    public String toString() { return "LoadManager "; }
}

class WebServerSimulation {
    static final int INITIAL_LINE_SIZE = 50;
    static final int ADJUSTMENT_PERIOD = 1000;
    private static Random rand = new Random(66);
    public static void main(String[] args) throws Exception {
        ExecutorService exec = Executors.newCachedThreadPool();
        WebClientLine WebClients =
                new WebClientLine();
        for (int i = 0; i < INITIAL_LINE_SIZE; i++) {
            WebClients.put(new WebClient(rand.nextInt(1000)));
        }
        WebClientGenerator generator = new WebClientGenerator(WebClients);
        exec.execute(generator);
        exec.execute(new LoadManager(
                exec, WebClients, generator, ADJUSTMENT_PERIOD));
        if(args.length > 0) // Optional argument
            TimeUnit.SECONDS.sleep(new Integer(args[0]));
        else {
            System.out.println("Press 'Enter' to quit");
            System.in.read();
        }
        exec.shutdownNow();
    }
}

public class E35 {
    public static void main(String[] args) {
        try {
            WebServerSimulation.main(args);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

