package holding;

//Exercise 1: (2) Create a new class called Gerbil with an int gerbilNumber that’s initialized in the constructor.
// Give it a method called hop( ) that displays which gerbil number this is, and that it’s hopping.
// Create an ArrayList and add Gerbil objects to the List.
// Now use the get( ) method to move through the List and call hop( ) for each Gerbil.

//Exercise 8: (1) Modify Exercise l so it uses an Iterator to move through the List while calling hop( ).

import java.util.ArrayList;
import java.util.Iterator;

import static net.mindview.util.Print.print;

public class Gerbil1 {
    int gerbilNumber;
    public Gerbil1(int num){
        gerbilNumber = num;
    }
    void hop(){
        print(gerbilNumber);
        print("hopping");
    }

    public static void main(String[] args) {
        ArrayList<Gerbil1> gerbil1s = new ArrayList<Gerbil1>();
        for (int i = 0; i < 10; i++) {
            gerbil1s.add(new Gerbil1(i));
        }
//        for (Gerbil1 gerbil1 : gerbil1s) {
//            gerbil1.hop();
//        }
        Iterator<Gerbil1> iterator = gerbil1s.iterator();
        while (iterator.hasNext()) {
            Gerbil1 g = iterator.next();
            g.hop();
        }
    }
}
