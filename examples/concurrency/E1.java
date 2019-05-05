package concurrency;

import static net.mindview.util.Print.print;

//Exercise 1: (2) Implement a Runnable. Inside run( ), print a message, and then call yield( ).
// Repeat this three times, and then return from run( ).
// Put a startup message in the constructor and a shutdown message when the task terminates.
// Create a number of these tasks and drive them using threads.

class Eb implements Runnable{
    int countdown = 3;
    static int count = 0;
    final int id = count++;
    @Override
    public void run() {
        while (countdown-- > 0) {
            print("# " + id + " waiting on " + countdown);
            Thread.yield();
        }
        print("# " + id + " job done");
        return;
    }
    public Eb(){
        print("creating Eb object " + id);
    }
}
public class E1 implements Runnable{
    static int count = 0;
    final int id = count++;
    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            print("# " + id + " executing " + i + " step");
            Thread.yield();
        }
        print("# " + id + " job done");
        return;
    }
    public E1(){
        print("creating E1 object " + id);
    }

    public static void main(String[] args) {
//        for (int i = 0; i < 3; i++) {
//            new Thread(new E1()).start();
//        }
        for (int i = 0; i < 3; i++) {
            new Thread(new Eb()).start();
        }
    }
}
