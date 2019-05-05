package initialization;
import static net.mindview.util.Print.*;

public class E15 {
    String boy;
    String girl;
    int love;
    {
        boy = "Simen";
        girl = "Della";
        love = 1314;
    }

    public static void main(String[] args) {
        E15 e15 = new E15();
        print(e15.boy + " " + e15.girl + " " + e15.love);
    }
}
