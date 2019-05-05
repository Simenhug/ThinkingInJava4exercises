package reusing;
import static net.mindview.util.Print.*;

//Create two classes, A and B, with default constructors (empty argument lists) that announce themselves.
// Inherit a new class called C from A, and create a member of class B inside C.
// Do not create a constructor for C. Create an object of class C and observe the results.
class A{
    public A(){print("A constructed");}
}
class B{
    public B(){print("B constructed");}
}
class C extends A{
    public C(){print("C constructed");}
    static B b = new B();
}
public class E5{
    public static void main(String[] args) {
        C c = new C();
    }
}
