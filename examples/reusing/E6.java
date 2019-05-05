package reusing;

//: reusing/E6c.java
// Inheritance, constructors and arguments.
import static net.mindview.util.Print.*;
class Game {
    Game(int i) {
        print("Game constructor");
    }
}
class BoardGame extends Game {
    BoardGame(int i) {
        super(i); //error: constructor Game in class Game cannot be applied to given types;
        print("BoardGame constructor");
        //super(i); //error: call to super must be first statement in constructor
    }
}
public class E6 extends BoardGame {
    E6() {
        super(11);
        print("E6c constructor");
    }
    public static void main(String[] args) {
        E6 x = new E6();
    }
}