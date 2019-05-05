package initialization;
import static net.mindview.util.Print.*;

class Della{
    static int i = 1;
    static String lover;
    static {
        lover = "Simen";
    }
    static void printStatic(){
        print(i);
        print(lover);
    }
}

public class E14 {
    public static void main(String[] args) {
        Della.printStatic();
    }
}
