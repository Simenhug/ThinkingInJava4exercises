package innerclasses.E6b;
import innerclasses.E6a.Interface6;
import static net.mindview.util.Print.print;

//Exercise 6: (2) Create an interface with at least one method,
// in its own package. Create a class in a separate package.
// Add a protected inner class that implements the interface.
// In a third package, inherit from your class and, inside a method,
// return an object of the protected inner class,
// upcasting to the interface during the return.
public class Outer6 {
    protected class Inner6 implements Interface6{
        public Inner6(){}
        @Override
        public void E6a() {
            print("implementing interface 6");
        }
    }
}
