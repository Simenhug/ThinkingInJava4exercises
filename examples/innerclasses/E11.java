package innerclasses;

import static net.mindview.util.Print.print;

//Exercise 11: (2) Create a private inner class that implements a public interface.
// Write a method that returns a reference to an instance of the private inner class,
// upcast to the interface. Show that the inner class is completely hidden by trying
// to downcast to it.
interface Face11{
    void didado();
}
class Shell11 {
    private class Inner11 implements Face11{
        public void didado(){
            print("inner didado");
        }
    }

    Face11 getInner(){
        return new Inner11();
    }
}

public class E11{
    public static void main(String[] args) {
        Shell11 shell = new Shell11();
       // ((Inner11)shell.getInner()).didado();
    }
}
//E11.java:27: error: cannot find symbol
//        ((Inner11)shell.getInner()).didado();
//          ^
//  symbol:   class Inner11
//  location: class E11
//1 error