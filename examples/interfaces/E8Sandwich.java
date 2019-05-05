package interfaces;
import static net.mindview.util.Print.*;

class Meal {
    Meal() { print("Meal()"); }
}

class Bread {
    Bread() { print("Bread()"); }
}

class Pickle{
    Pickle(){
        print("Picle");}
}

class Cheese {
    Cheese() { print("Cheese()"); }
}

class Lettuce {
    Lettuce() { print("Lettuce()"); }
}

class Lunch extends Meal {
    Lunch() { print("Lunch()"); }
}

class PortableLunch extends Lunch {
    PortableLunch() { print("PortableLunch()");}
}
interface FastFood{
    Bread b = new Bread();
    Cheese c = new Cheese();
    Lettuce l = new Lettuce();
}

public class E8Sandwich extends PortableLunch implements FastFood{
    public E8Sandwich() { print("Sandwich()"); }
    public static void main(String[] args) {
        E8Sandwich shakeshack = new E8Sandwich();
        print(shakeshack.b);
        print(shakeshack.c);
        print(shakeshack.l);
    }
}
