package reusing;
import static net.mindview.util.Print.*;

//Create a base class with only a non-default constructor, and a derived class with both a default (no-arg) and non-default constructor.
// In the derived-class constructors, call the base-class constructor.
class alita{
    alita(int i){
        print("battle angel");
    }
}
public class E8 extends alita{
    E8(){
        super(99);
        print("Default E8");
    }
    E8(int i){
        super(99);
        print("Adjusted E8");
    }

    public static void main(String[] args) {
        E8 alita = new E8();
        E8 hugo = new E8(99);
    }
}
