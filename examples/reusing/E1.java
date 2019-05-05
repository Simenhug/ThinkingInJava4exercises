package reusing;
import static net.mindview.util.Print.*;

//Create a simple class. Inside a second class, define a reference to an object of the first class.
// Use lazy initialization to instantiate this object.
class Abby{
    String lover;
    void confession(){
        print("Abby loves " + lover);
    }
}

public class E1 {
    private Abby mistress;
    public String toString(){
        if (mistress == null){
            mistress = new Abby();
            mistress.lover = "Simen";
        }
        return "Abby's lover is Simen";
    }
    public static void main(String[] args) {
        E1 couple = new E1();
        print(couple);
    }
}
