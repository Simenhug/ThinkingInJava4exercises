package interfaces;
import net.mindview.util.Print;

import static net.mindview.util.Print.*;

//Exercise 3: (2) Create a base class with an abstract print( ) method that is overridden in a derived class.
// The overridden version of the method prints the value of an int variable defined in the derived class.
// At the point of definition of this variable, give it a nonzero value.
// In the base-class constructor, call this method. In main( ), create an object of the derived type,
// and then call its print( ) method. Explain the results.

abstract class BaseClass{
    abstract void print();
    BaseClass(){
        print();
    }
}

class DerivedClass extends BaseClass{
    private int i = 99;
    @Override
    void print() {
        Print.print(i);
    }
}

public class E3 {
    public static void main(String[] args){
        DerivedClass derivedClass = new DerivedClass();
        derivedClass.print();
    }
}
