package innerclasses;

import static net.mindview.util.Print.print;

//Exercise 8: (2) Determine whether an outer class has access to the
// private elements of its inner class.

public class E8 {
    class Inner8{
        private String inner = "try to get me";
    }
    public void tryToGetInner(){
        Inner8 inner8 = new Inner8();
        print(inner8.inner);
    }

    public static void main(String[] args) {
        E8 e8 = new E8();
        e8.tryToGetInner();
    }
}

//apparently they do
