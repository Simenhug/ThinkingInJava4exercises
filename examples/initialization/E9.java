package initialization;
import static net.mindview.util.Print.*;

public class E9 {

    String s = "initial value";

    E9(String s) {
        this.s = s;
    }

    E9(int end){
        this("ALITA");
        print("new value is " + this.s + " with number "+end);
    }

    public static void main(String[] args) {
        E9 alita = new E9(99);
    }
}
