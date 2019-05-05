package reusing;
import static net.mindview.util.Print.*;

//(2) Create a class with a method that is overloaded three times.
// Inherit a new class, add a new overloading of the method,
// and show that all four methods are available in the derived class.

class Della{
    int simen(int i) {
        print("Simen kissed Della 99 times");
        return 99;
    };

    String simen(String string) {
        print("Della has strings pulled by Simen");
        return ("possessed");
    }

    void simen(){
        print("Della loves Simen since the birth of the void");
    }
}
public class E13 extends Della{
    char simen(char i){
        print("Della is attracted by Simen's character");
        return 'S';
    }

    public static void main(String[] args) {
        E13 simen = new E13();
        simen.simen(99);
        simen.simen("love Della");
        simen.simen();
        simen.simen('S');
    }
}
