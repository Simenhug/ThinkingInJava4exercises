package interfaces;
import static net.mindview.util.Print.*;

//Exercise 4: (3) Create an abstract class with no methods. Derive a class and add a method.
// Create a static method that takes a reference to the base class, downcasts it to the derived class,
// and calls the method. In main( ), demonstrate that it works.
// Now put the abstract declaration for the method in the base class,
// thus eliminating the need for the downcast.

abstract class Class1{
    abstract void printThings();
}

class Class2 extends Class1{
    void printThings(){
        print("print things");
    }
    static void printTest(Class1 class1){
        //((Class2)class1).printThings();
        class1.printThings();
    }
}

public class E4 {
    public static void main(String[] args) {
        Class2 class2 = new Class2();
        Class2.printTest(class2);
    }
}
