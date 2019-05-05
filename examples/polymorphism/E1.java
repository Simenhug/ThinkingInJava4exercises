package polymorphism;
import static net.mindview.util.Print.*;

//(2) Create a Cycle class, with subclasses Unicycle, Bicycle and Tricycle.
// Demonstrate that an instance of each type can be upcast to Cycle via a ride( ) method.

//Exercise 17: (2) Using the Cycle hierarchy from Exercise 1,
// add a balance( ) method to Unicycle and Bicycle, but not to Tricycle.
// Create instances of all three types and upcast them to an array of Cycle.
// Try to call balance( ) on each element of the array and observe the results.
// Downcast and call balance( ) and observe what happens.


class Cycle{
    void riding(){
        print("riding a cycle");
    }
}
class Unicycle extends Cycle{
    @Override
    void riding(){
        print("riding an unicycle");
    }
    void balance(){
        print("quite hard to balance");
    }
}
class Bicycle extends Cycle{
    @Override
    void riding(){
        print("riding a bicycle");
    }
    void balance(){
        print("need practices to balance");
    }
}
class Tricycle extends Cycle{
    @Override
    void riding(){
        print("riding a tricycle");
    }
}
public class E1 {
    static void ride(Cycle i) {
        i.riding();
    }

    public static void main(String[] args) {
        Cycle[] parkingLot = new Cycle[]{
                new Unicycle(),
                new Bicycle(),
                new Tricycle()
        };

        try{
            for (Cycle i : parkingLot) {
                i.balance();
            }
            ((Unicycle)parkingLot[0]).balance();
            ((Bicycle)parkingLot[1]).balance();
            ((Tricycle)parkingLot[2]).balance();
        }
        finally {
            print("done!");
        }
    }
}

//OuterE1.java:57: error: cannot find symbol
//                i.balance();
//                 ^
//  symbol:   method balance()
//  location: variable i of type Cycle
//OuterE1.java:61: error: cannot find symbol
//            ((Tricycle)parkingLot[2]).balance();
//                                     ^
//  symbol:   method balance()
//  location: class Tricycle
//2 errors