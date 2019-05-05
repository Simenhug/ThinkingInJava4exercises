package initialization;
import static net.mindview.util.Print.*;

//Create a class with a constructor that takes a String argument.
// During construction, print the argument.
// Create an array of object references to this class, but don’t actually create objects to assign into the array.
// When you run the program, notice whether the initialization messages from the constructor calls are printed.

class Abby{

    Abby(String Simen) {
        print(Simen);
    }
}

public class E17 {

    public static void main(String[] args) {
        Abby[] abbys = new Abby[7];
        print("Abbys created");
    }
}
