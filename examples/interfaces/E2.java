package interfaces;

//Exercise 2: (1) Create a class as abstract without including any
// abstract methods and verify that you cannot create any instances of that class.

abstract class AbstractTest{}

public class E2 {
    public static void main(String[] args) {
        AbstractTest abstractTest = new AbstractTest();
    }
}

//E2.java:10: error: AbstractTest is abstract; cannot be instantiated
//        AbstractTest abstractTest = new AbstractTest();
//                                    ^
//1 error