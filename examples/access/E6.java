package access;
import static net.mindview.util.Print.*;

//Create a class with protected data.
//Create a second class in the same file with a method that manipulates the protected data in the first class.

class Dellove{
    protected void loveSimen(){
        print("love Simen");
    }
    static protected String confession = "I love Simen forever";
}
public class E6 extends Dellove {
    static void confess(){
        print(confession);
    }

    public static void main(String[] args) {
        confess();
    }
}
