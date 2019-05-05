//: innerclasses/Sequence.java
// Holds a sequence of Objects.
package innerclasses;
//Exercise 22: (2) Implement reverseSelector( ) in Sequence.java.
//holding Exercise 3: (2) Modify innerclasses/Sequence.java so that you can add any number
//of elements to it.
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

import static net.mindview.util.Print.print;

interface Selector {
  boolean end();
  Object current();
  void next();
}	

public class Sequence {
  private ArrayList<Object> items;
  private int next = 0;
  public Sequence() { items = new ArrayList<Object>(); }
  public void add(Object x) {
    items.add(x);
  }
  private class SequenceSelector implements Selector {
    private int i = 0;
    public boolean end() { return i == items.size(); }
    public Object current() { return items.get(i); }
    public void next() { if(i < items.size()) i++; }
  }
  private class ReverseSelector implements Selector{
    private int pointer = items.size()-1;
    @Override
    public void next() {}
    @Override
    public boolean end() {
      return false;
    }
    public boolean first(){return pointer < 0;}
    public Object current() {return items.get(pointer);}
    public void previous() {if(pointer>=0) pointer--;}
  }

  public Selector selector() {
    return new SequenceSelector();
  }
  public ReverseSelector reverseSelector() {return new ReverseSelector();}
  public static void main(String[] args) {
    Sequence sequence = new Sequence();
    for(int i = 0; i < 10; i++)
      sequence.add(Integer.toString(i));
    Iterator iterator = sequence.items.listIterator();
    while (iterator.hasNext()) {
      print(iterator.next());
    }
    Iterator reverse = sequence.items.listIterator(sequence.items.size());
    while (((ListIterator) reverse).hasPrevious()) {
      print(((ListIterator) reverse).previous());
    }
//    Selector selector = sequence.selector();
//    ReverseSelector reverseSelector = sequence.reverseSelector();
////    for (Object i : sequence.items) {
////      System.out.println(i);
////    }
//    while(!selector.end()) {
//      System.out.print(selector.current() + " ");
//      selector.next();
//    }
//    System.out.println();
//    while (!reverseSelector.first()){
//      System.out.print(reverseSelector.current() + " ");
//      reverseSelector.previous();
//    }
//    System.out.println();
  }
} /* Output:
0 1 2 3 4 5 6 7 8 9
*///:~
