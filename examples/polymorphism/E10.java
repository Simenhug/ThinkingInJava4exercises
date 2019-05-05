package polymorphism;
import static net.mindview.util.Print.*;

//(3) Create a base class with two methods. In the first method, call the second method.
// Inherit a class and override the second method. Create an object of the derived class,
// upcast it to the base type, and call the first method. Explain what happens.
class Base{
    void call2(){
        print("first method");
        this.second();
        print("first method done");
    }
    void second(){
        print("second method being called");
    }
}

class Derive extends Base{
    @Override
    void second(){
        print("derived second method");
    }
}

public class E10 {
    static void upcast(Base i){
        i.call2();
    }

    public static void main(String[] args) {
        Derive derive = new Derive();
        upcast(derive);
    }
}
