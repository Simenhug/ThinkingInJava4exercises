//: holding/SimpleCollection.java
package holding;
import java.util.*;
//Exercise 2: (1) Modify SimpleCollection.java to use a Set for c.
public class SimpleCollection {
  public static void main(String[] args) {
    Collection<Integer> c = new ArrayList<Integer>();
    for(int i = 0; i < 10; i++)
      c.add(i); // Autoboxing
    for(Integer i : c)
      System.out.print(i + ", ");
    Collection<String> myLove = new HashSet<String>(){};
    for (int i = 0; i < 10; i++) {
      myLove.add("Della " + i);
    }
    for (String s : myLove) {
      System.out.println(s);
    }
  }
} /* Output:
0, 1, 2, 3, 4, 5, 6, 7, 8, 9,
*///:~
