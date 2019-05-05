package reusing;
import static net.mindview.util.Print.*;

//(2) Prove that class loading takes place only once.
// Prove that loading may be caused by either the creation of the
// first instance of that class or by the access of a static member.
class Inherited{
    static String s1 = "Inherited s1 initialized";
    static void showPrint(){
        print("Inherited method initialized");
    }
    Inherited(){
        print("Inherited object initialized");
        print(s1);
    }
}
public class E23 extends Inherited{

    public static void main(String[] args) {
        //loading by static member access:
        Inherited.showPrint();
        //loading by creation of first instance
        E23 test = new E23();
        //prove that class loading takes place only once
        E23 test1 = new E23();

    }
}
