package operators;
import static net.mindview.util.Print.*;

public class Dog {

    String name;
    String says;

    public static void main(String[] args){
        Dog spot = new Dog();
        Dog scruffy = new Dog();
        spot.name = "spot";
        spot.says = "Ruff!";
        scruffy.name = "scruffy";
        scruffy.says = "Wurf!";
        print(spot.name + " says: " + spot.says);
        print(scruffy.name + " says: " + scruffy.says);
        Dog cindy = spot;
        print("spot == cindy " + (spot==cindy));
        print("spot .equals() cindy " + spot.equals(cindy));
        print("spot says .equals() cindy says " + spot.says.equals(cindy.says));
    }
}
