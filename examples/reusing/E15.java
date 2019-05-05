package reusing;
import static net.mindview.util.Print.*;

//(2) Create a class inside a package. Your class should contain a protected method.
// Outside of the package, try to call the protected method and explain the results.
// Now inherit from your class and call the protected method from inside a method of your derived class.

public class E15 {
    private String Della = "Della";
    protected void confess(){
        print("Simen loves Della");
    }
}
