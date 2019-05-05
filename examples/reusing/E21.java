package reusing;
import static net.mindview.util.Print.*;

//(1) Create a class with a final method. Inherit from that class and attempt
//to overwrite that method.
class Origin{
    String test;

    final void setTest(String input) {
        test = input;
    }
}

public class E21 extends Origin{
//    @Override
//    void setTest(String input) {
//        test = input + "impossible";
//    }
}

//E21.java:16: error: setTest(String) in E21 cannot override setTest(String) in Origin
//    void setTest(String input) {
//         ^
//  overridden method is final
//1 error