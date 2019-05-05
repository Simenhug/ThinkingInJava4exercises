//: holding/Statistics.java
// Simple demonstration of HashMap.
package holding;
import java.util.*;

import static net.mindview.util.Print.print;

//Exercise 23: (4) Starting with Statistics.java,
// create a program that runs the test repeatedly
// and looks to see if any one number tends to appear more than the others in the results.
public class Statistics {
  public static void mostOccurred(Map map){
      Integer key = 0;
      Integer value = 0;
      Iterator iterator = map.keySet().iterator();
      while (iterator.hasNext()) {
          Integer currentKey = (Integer)iterator.next();
          if ((Integer)map.get(currentKey) > value) {
              key = currentKey;
              value = (Integer)map.get(currentKey);
          }
      }
      print("most occurred " + key + ": " + value);
  }
  public static void main(String[] args) {
    Random rand = new Random(47);
    Map<Integer,Integer> m =
      new HashMap<Integer,Integer>();
    int frequencyCheck = 0;
    for(int i = 0; i < 10000; i++) {
      // Produce a number between 0 and 20:
      int r = rand.nextInt(20);
      frequencyCheck++;
      Integer freq = m.get(r);
      m.put(r, freq == null ? 1 : freq + 1);
      if (frequencyCheck%100 == 0){
          mostOccurred(m);
      }
    }
    System.out.println(m);
  }
} /* Output:
{15=497, 4=481, 19=464, 8=468, 11=531, 16=533, 18=478, 3=508, 7=471, 12=521, 17=509, 2=489, 13=506, 9=549, 6=519, 1=502, 14=477, 10=513, 5=503, 0=481}
*///:~
