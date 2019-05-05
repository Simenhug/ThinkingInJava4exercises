package holding;

import java.util.*;

import static net.mindview.util.Print.print;

//Exercise 12: (3) Create and populate a List<Integer>.
// Create a second List<Integer> of the same size as the first,
// and use ListIterators to read elements from the first List and
// insert them into the second in reverse order.
// (You may want to explore a number of different ways to solve this problem.)
public class E12 {
    public static void main(String[] args) {
        List<Integer> l1 = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5));
        List<Integer> l2 = new ArrayList<Integer>();
        ListIterator<Integer> iterator = l1.listIterator(l1.size());
        while (iterator.hasPrevious()) {
            l2.add(iterator.previous());
        }
        print(l1);
        print(l2);
    }
}
