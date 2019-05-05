//: concurrency/MoreBasicThreads.java
// Adding more threads.
package concurrency;

//Exercise 8: (1) Modify MoreBasicThreads.java so that all the threads are daemon threads,
// and verify that the program ends as soon as main( ) is able to exit.

import java.util.concurrent.TimeUnit;

public class MoreBasicThreads {
  public static void main(String[] args) throws Exception{
    for(int i = 0; i < 5; i++) {
      Thread Della = new Thread(new LiftOff());
      Della.setDaemon(true);
      Della.start();
    }
    System.out.println("Waiting for LiftOff");
    //TimeUnit.SECONDS.sleep(2);
  }
} /* Output: (Sample)
Waiting for LiftOff
#0(9), #1(9), #2(9), #3(9), #4(9), #0(8), #1(8), #2(8), #3(8), #4(8), #0(7), #1(7), #2(7), #3(7), #4(7), #0(6), #1(6), #2(6), #3(6), #4(6), #0(5), #1(5), #2(5), #3(5), #4(5), #0(4), #1(4), #2(4), #3(4), #4(4), #0(3), #1(3), #2(3), #3(3), #4(3), #0(2), #1(2), #2(2), #3(2), #4(2), #0(1), #1(1), #2(1), #3(1), #4(1), #0(Liftoff!), #1(Liftoff!), #2(Liftoff!), #3(Liftoff!), #4(Liftoff!),
*///:~
