package reusing;

import static net.mindview.util.Print.*;

//Modify Exercise 5 so that A and B have constructors with arguments instead of default constructors.
// Write a constructor for C and perform all initialization within C’s constructor.
class A{
    public A(int i){print("A constructed");}
}
class B{
    public B(int i){print("B constructed");}
}
class C extends A{
    public C(){
        super(7);
        print("C constructed");
        b = new B(7);
    }
    static B b;
}
public class E7{
    public static void main(String[] args) {
        C c = new C();
    }
}

