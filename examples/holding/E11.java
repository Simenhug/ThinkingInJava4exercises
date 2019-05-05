package holding;

//Exercise 11: (2) Write a method that uses an Iterator to step through a Collection and print
// the toString( ) of each object in the container.
// Fill all the different types of Collections with objects and apply your method to each container.

//import net.mindview.simple.List;

import java.util.*;

import static net.mindview.util.Print.print;

public class E11 {
    void iteratorMaker(Collection collection) {
        Iterator iterator = collection.iterator();
        while (iterator.hasNext()) {
            print(iterator.next());
        }
    }

    public static void main(String[] args) {
        E11 e11 = new E11();
        List arr = Arrays.asList("a", "b", "c", "d");
        ArrayList<String> arrayList = new ArrayList<String>(arr);
        HashSet<String> hashSet = new HashSet<String>(arr);
        e11.iteratorMaker(arrayList);
        e11.iteratorMaker(hashSet);
    }

}
