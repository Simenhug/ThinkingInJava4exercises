package initialization;
//Create a class with two methods. Within the first method,
// call the second method twice: the first time without using this,
// and the second time using this—just to see it working; you should not use this form in practice.

import static net.mindview.util.Print.*;

public class E8 {

    void firstMethod(){
        print("performing first method");
    }

    void callFirstMethod(){
        firstMethod();
        this.firstMethod();
    }

    public static void main(String[] args) {
        E8 e8 = new E8();
        e8.callFirstMethod();
    }
}
