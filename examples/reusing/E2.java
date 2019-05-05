package reusing;
import static net.mindview.util.Print.*;
//Inherit a new class from class Detergent. Override scrub( ) and add a new method called sterilize( ).

public class E2 extends Detergent {
    public void scrub(){
        append(" E2.scrub()");
        super.scrub();
    }
    void bubbleBath(){
        append(" bubbleBath()");
    }
    public static void main(String[] args) {
        E2 x = new E2();
        x.dilute();
        x.apply();
        x.scrub();
        x.foam();
        x.bubbleBath();
        print(x);
        print("Testing base class:");
        Detergent.main(args);
    }
}
