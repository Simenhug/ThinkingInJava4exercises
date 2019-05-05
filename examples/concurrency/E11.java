package concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static net.mindview.util.Print.print;

//Exercise 11: (3) Create a class containing two data fields, and a method that manipulates those fields in a
// multistep process so that, during the execution of that method, those fields are in an "improper state"
// (according to some definition that you establish). Add methods to read the fields, and create multiple
// threads to call the various methods and show that the data is visible in its "improper state." Fix the
// problem using the synchronized keyword.
class Target {
    private volatile boolean canceled = false;
    private int Alita;
    private String Della;
    public void cancel(){canceled = true;}
    public boolean isCanceled() {
        return canceled;
    }
    public synchronized int nextInt(){
        Alita += 44;
        Alita += 55;
        return Alita;
    }
    public synchronized String nextString(){
        Della = null;
        Thread.yield();
        Della = "The angel who loves Simen";
        return Della;
    }
}
class Checker implements Runnable{
    private Target target;
    public Checker(Target tartar) {
        target = tartar;
    }
    public void run(){
        while (!target.isCanceled()) {
            int alita = target.nextInt();
            String della = target.nextString();
            if (della == null) {
                print("Della must be in love with Simen");
                target.cancel();
            }
            if (alita % 99 != 0) {
                print("alita is not in the 99 realm: " + alita);
                target.cancel();
            }
        }
    }
    public static void test(Target tg){
        print("Control+C to exist");
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            executorService.execute(new Checker(tg));
        }
        executorService.shutdown();
    }
}
public class E11{
    public static void main(String[] args) {
        Target target = new Target();
        Checker.test(target);
    }
}