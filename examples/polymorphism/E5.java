package polymorphism;
import static net.mindview.util.Print.*;

//(1) Starting from Exercise 1, add a wheels( ) method in Cycle, which returns the number of wheels.
// Modify ride( ) to call wheels( ) and verify that polymorphism works.

class Cycle{
    void riding(){
        print("riding a cycle");
    }
    int wheels(){return 0;}
}
class Unicycle extends Cycle{
    @Override
    void riding(){
        print("riding an unicycle");
    }
    @Override
    int wheels(){return 1;}
}
class Bicycle extends Cycle{
    @Override
    void riding(){
        print("riding a bicycle");
    }
    @Override
    int wheels(){return 2;}
}
class Tricycle extends Cycle{
    @Override
    void riding(){
        print("riding a tricycle");
    }
    @Override
    int wheels(){return 3;}
}
public class E5 {
    static void ride(Cycle i) {
        i.riding();
        print(i.wheels());
    }

    public static void main(String[] args) {
        Unicycle unicycle = new Unicycle();
        Bicycle bicycle = new Bicycle();
        Tricycle tricycle = new Tricycle();
        //upcasting
        ride(unicycle);
        ride(bicycle);
        ride(tricycle);
    }
}
