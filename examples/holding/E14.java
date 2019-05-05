package holding;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

import static net.mindview.util.Print.print;

//Exercise 14: (3) Create an empty LinkedList<Integer>. Using a Listlterator,
// add Integers to the List by always inserting them in the middle of the List.
public class E14 {
    static void addMiddle(LinkedList linkedList, int[] ints) {
        for (int i : ints) {
            Iterator<Integer> iterator = linkedList.listIterator(linkedList.size()/2);
            ((ListIterator<Integer>) iterator).add(i);
        }
    }
    public static void main(String[] ars) {
        LinkedList<Integer> linkedList = new LinkedList<Integer>();
        int[] ints = {1,2,3,4,5};
        addMiddle(linkedList, ints);
        print(linkedList);
    }
}
