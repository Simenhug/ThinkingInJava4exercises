package concurrency;

import java.util.concurrent.*;
import static net.mindview.util.Print.*;
//Exercise 24: (1) Solve a single-producer, single-consumer problem using wait( ) and notifyAll( ).
// The producer must not overflow the receiver’s buffer, which can happen if the producer is faster than the consumer.
// If the consumer is faster than the producer, then it must not read the same data more than once.
// Do not assume anything about the relative speeds of the producer or consumer.
class Product{
    private final int productId;
    public Product(int id) {
        productId = id;
    }
    @Override
    public String toString(){
        return "Product number " + productId;
    }
}
class Producer implements Runnable{
    private Market market;
    private int count = 0;
    public Producer(Market mar){
        market = mar;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                synchronized (this) {
                    while (market.product != null) {
                        wait();
                    }
                }
                if (++count == 10) {
                    print("let's call it a day");
                    market.exec.shutdownNow();
                }
                print("making new product " + count);
                synchronized (market.consumer) {
                    market.product = new Product(count);
                    market.consumer.notifyAll();
                }
            }
        } catch (InterruptedException e) {
            print("producer interrupted exception");
        }
    }
}
class Consumer implements Runnable{
    private Market market;
    public Consumer(Market mar){
        market = mar;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                synchronized (this) {
                    while (market.product == null) {
                        wait();
                    }
                }
                print("delivering product " + market.product);
                synchronized (market.producer) {
                    market.product = null;
                    market.producer.notifyAll();
                }
            }
        } catch (InterruptedException e) {
            print("consumer interrupted exception");
        }
    }
}
class Market {
    Product product;
    Producer producer = new Producer(this);
    Consumer consumer = new Consumer(this);
    ExecutorService exec = Executors.newCachedThreadPool();
    public Market(){
        exec.execute(producer);
        exec.execute(consumer);
    }
}
public class E24 {
    public static void main(String[] args) {
        new Market();
    }
}
