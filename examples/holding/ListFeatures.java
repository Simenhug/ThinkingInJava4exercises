//: holding/ListFeatures.java
package holding;
import java.lang.reflect.Array;
import java.util.*;
import static net.mindview.util.Print.*;
//Exercise 5: (3) Modify ListFeatures.java so that it uses Integers (remember autoboxing!) instead of Pets,
// and explain any difference in results.


public class ListFeatures {
  public static void main(String[] args) {
    Random rand = new Random(47);
    List<Integer> integers = new ArrayList<Integer>(Arrays.asList(1,2,3,4,5,6,7));
    print("1: " + integers);
    Integer h = 99;
    integers.add(h); // Automatically resizes
    print("2: " + integers);
    print("3: " + integers.contains(h));
    integers.remove(h); // Remove by object
    Integer p = integers.get(2);
    print("4: " +  p + " " + integers.indexOf(p));
    Integer cymric = 88;
    print("5: " + integers.indexOf(cymric));
    print("6: " + integers.remove(cymric));
    // Must be the exact object:
    print("7: " + integers.remove(p));
    print("8: " + integers);
    integers.add(3, 77); // Insert at an index
    print("9: " + integers);
    List<Integer> sub = integers.subList(1, 4);
    print("subList: " + sub);
    print("10: " + integers.containsAll(sub));
    Collections.sort(sub); // In-place sort
    print("sorted subList: " + sub);
    // Order is not important in containsAll():
    print("11: " + integers.containsAll(sub));
    Collections.shuffle(sub, rand); // Mix it up
    print("shuffled subList: " + sub);
    print("12: " + integers.containsAll(sub));
    List<Integer> copy = new ArrayList<Integer>(integers);
    sub = Arrays.asList(integers.get(1), integers.get(4));
    print("sub: " + sub);
    copy.retainAll(sub);
    print("13: " + copy);
    copy = new ArrayList<Integer>(integers); // Get a fresh copy
    copy.remove(2); // Remove by index
    print("14: " + copy);
    copy.removeAll(sub); // Only removes exact objects
    print("15: " + copy);
    copy.set(1, 66); // Replace an element
    print("16: " + copy);
    copy.addAll(2, sub); // Insert a list in the middle
    print("17: " + copy);
    print("18: " + integers.isEmpty());
    integers.clear(); // Remove all elements
    print("19: " + integers);
    print("20: " + integers.isEmpty());
    integers.addAll(Arrays.asList(4,8,7,9));
    print("21: " + integers);
    Object[] o = integers.toArray();
    print("22: " + o[3]);
    Integer[] pa = integers.toArray(new Integer[0]);
    print("23: " + pa[3]);
  }
}
//
//public class ListFeatures {
//  public static void main(String[] args) {
//    Random rand = new Random(47);
//    List<Integer> integers = integers.arrayList(7);
//    print("1: " + integers);
//    Hamster h = new Hamster();
//    integers.add(h); // Automatically resizes
//    print("2: " + integers);
//    print("3: " + integers.contains(h));
//    integers.remove(h); // Remove by object
//    Integer p = integers.get(2);
//    print("4: " +  p + " " + integers.indexOf(p));
//    Integer cymric = new Cymric();
//    print("5: " + integers.indexOf(cymric));
//    print("6: " + integers.remove(cymric));
//    // Must be the exact object:
//    print("7: " + integers.remove(p));
//    print("8: " + integers);
//    integers.add(3, new Mouse()); // Insert at an index
//    print("9: " + integers);
//    List<Integer> sub = integers.subList(1, 4);
//    print("subList: " + sub);
//    print("10: " + integers.containsAll(sub));
//    Collections.sort(sub); // In-place sort
//    print("sorted subList: " + sub);
//    // Order is not important in containsAll():
//    print("11: " + integers.containsAll(sub));
//    Collections.shuffle(sub, rand); // Mix it up
//    print("shuffled subList: " + sub);
//    print("12: " + integers.containsAll(sub));
//    List<Integer> copy = new ArrayList<Integer>(integers);
//    sub = Arrays.asList(integers.get(1), integers.get(4));
//    print("sub: " + sub);
//    copy.retainAll(sub);
//    print("13: " + copy);
//    copy = new ArrayList<Integer>(integers); // Get a fresh copy
//    copy.remove(2); // Remove by index
//    print("14: " + copy);
//    copy.removeAll(sub); // Only removes exact objects
//    print("15: " + copy);
//    copy.set(1, new Mouse()); // Replace an element
//    print("16: " + copy);
//    copy.addAll(2, sub); // Insert a list in the middle
//    print("17: " + copy);
//    print("18: " + integers.isEmpty());
//    integers.clear(); // Remove all elements
//    print("19: " + integers);
//    print("20: " + integers.isEmpty());
//    integers.addAll(integers.arrayList(4));
//    print("21: " + integers);
//    Object[] o = integers.toArray();
//    print("22: " + o[3]);
//    Integer[] pa = integers.toArray(new Integer[0]);
//    print("23: " + pa[3].id());
//  }
//}/* Output:
//1: [Rat, Manx, Cymric, Mutt, Pug, Cymric, Pug]
//2: [Rat, Manx, Cymric, Mutt, Pug, Cymric, Pug, Hamster]
//3: true
//4: Cymric 2
//5: -1
//6: false
//7: true
//8: [Rat, Manx, Mutt, Pug, Cymric, Pug]
//9: [Rat, Manx, Mutt, Mouse, Pug, Cymric, Pug]
//subList: [Manx, Mutt, Mouse]
//10: true
//sorted subList: [Manx, Mouse, Mutt]
//11: true
//shuffled subList: [Mouse, Manx, Mutt]
//12: true
//sub: [Mouse, Pug]
//13: [Mouse, Pug]
//14: [Rat, Mouse, Mutt, Pug, Cymric, Pug]
//15: [Rat, Mutt, Cymric, Pug]
//16: [Rat, Mouse, Cymric, Pug]
//17: [Rat, Mouse, Mouse, Pug, Cymric, Pug]
//18: false
//19: []
//20: true
//21: [Manx, Cymric, Rat, EgyptianMau]
//22: EgyptianMau
//23: 14
///:~
