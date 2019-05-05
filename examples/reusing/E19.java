package reusing;
import static net.mindview.util.Print.*;

//(2) Create a class with a blank final reference to an object.
// Perform the initialization of the blank final inside all constructors.
// Demonstrate the guarantee that the final must be initialized before use,
// and that it cannot be changed once initialized.

class finalTest{
    public final String initialized;
    final int leftBehind;
    finalTest(){initialized = "Della"; leftBehind = 520;}
}

public class E19 {
    public static void main(String[] args) {
        finalTest test = new finalTest();
        //test.initialized = "Abby";
    }
}//;~

//E19.java:12: error: variable leftBehind might not have been initialized
//    finalTest(){initialized = "Della";}
//                                      ^
//E19.java:18: error: cannot assign a value to final variable initialized
//        test.initialized = "Abby";
//            ^
//2 errors

