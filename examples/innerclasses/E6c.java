package innerclasses;

import innerclasses.E6a.Interface6;
import innerclasses.E6b.Outer6;

//Exercise 6: (2) Create an interface with at least one method,
// in its own package. Create a class in a separate package.
// Add a protected inner class that implements the interface.
// In a third package, inherit from your class and, inside a method,
// return an object of the protected inner class,
// upcasting to the interface during the return.
public class E6c extends Outer6 {
    public Interface6 getInner(){
        return this.new Inner6();
    }

    public static void main(String[] args) {
        E6c tester = new E6c();
        Interface6 insider = tester.getInner();
        insider.E6a();
    }
}
