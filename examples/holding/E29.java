package holding;


import java.util.PriorityQueue;

//Exercise 29: (2) Create a simple class that inherits from Object and contains no members,
// and show that you cannot successfully add multiple elements of that class to a PriorityQueue. This issue will be fully explained in the Containers in Depth chapter.
class Simple extends Object{
}
public class E29 {
    public static void main(String[] args) {
        PriorityQueue<Simple> realSimple = new PriorityQueue<>();
        for (int i = 0; i < 10; i++) {
            //realSimple.offer(new Simple());
        }
    }
}

//Output:
//Exception in thread "main" java.lang.ClassCastException: holding.Simple cannot be cast to java.lang.Comparable
//        at java.util.PriorityQueue.siftUpComparable(PriorityQueue.java:653)
//        at java.util.PriorityQueue.siftUp(PriorityQueue.java:648)
//        at java.util.PriorityQueue.offer(PriorityQueue.java:345)
//        at holding.E29.main(E29.java:14)