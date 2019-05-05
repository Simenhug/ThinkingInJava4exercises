package initialization;
import static net.mindview.util.Print.*;

//Write a method that takes a vararg String array.
//Verify that you can pass either a comma-separated list of Strings or a String[] into this method.

public class E19 {

    static void della(String... args) {
        print("acceptable");
    }

    public static void main(String[] args) {
        della("Della", "loves", "Simen");
        della(new String[]{"Della", "loves", "Simen"});
    }
}
