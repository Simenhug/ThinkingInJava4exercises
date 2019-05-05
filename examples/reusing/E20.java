package reusing;


//: reusing/FinalOverridingIllusion.java
// It only looks like you can override
// a private or private final method.
import static net.mindview.util.Print.*;
class WithFinals {
    // Identical to "private" alone:
    private final void f() { print("WithFinals.f()"); }
    // Also automatically "final":
    private void g() { print("WithFinals.g()"); }
}
class OverridingPrivate extends WithFinals {
    @Override
    private final void f() {
        print("OverridingPrivate.f()");
    }
    @Override
    private void g() {
        print("OverridingPrivate.g()");
    }
}
class OverridingPrivate2 extends OverridingPrivate {
    @Override
    public final void f() {
        print("OverridingPrivate2.f()");
    }
    @Override
    public void g() {
        print("OverridingPrivate2.g()");
    } }
public class E20 {
    public static void main(String[] args) {
        OverridingPrivate2 op2 = new OverridingPrivate2();
        op2.f();
        op2.g();
        // You can upcast:
        OverridingPrivate op = op2;
        // But you can’t call the methods:
        //! op.f();
        //! op.g();
        // Same here:
        WithFinals wf = op2;
        //! wf.f();
        //! wf.g();
    }
} /* Output:
OverridingPrivate2.f()
OverridingPrivate2.g()
*///:~

