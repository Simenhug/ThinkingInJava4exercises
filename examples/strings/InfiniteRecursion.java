//: strings/InfiniteRecursion.java
// Accidental recursion.
// {RunByHand}
package strings;
//Exercise 2: (1) Repair InfiniteRecursion.java.
import java.util.*;

public class InfiniteRecursion {
  public String toString() {
    //return " InfiniteRecursion address: " + this + "\n";
    //"this" calls the overridden toString() method, thus causing infinite recursion
    return " InfiniteRecursion address: " + super.toString() + "\n";
  }
  public static void main(String[] args) {
    List<InfiniteRecursion> v =
      new ArrayList<InfiniteRecursion>();
    for(int i = 0; i < 10; i++)
      v.add(new InfiniteRecursion());
    System.out.println(v);
  }
} ///:~
