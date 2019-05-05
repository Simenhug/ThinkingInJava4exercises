package interfaces;
import static net.mindview.util.Print.*;
//Exercise 14: (2) Create three interfaces, each with two methods.
// Inherit a new interface that combines the three, adding a new method.
// Create a class by implementing the new interface and also inheriting from a concrete class.
// Now write four methods, each of which
//takes one of the four interfaces as an argument.
// In main( ), create an object of your class and pass it to each of the methods.

//Exercise 15: (2) Modify the previous exercise by creating an abstract class and inheriting that into the derived class.

interface Simen{
    void love();
    void protect();
}

interface Della{
    void worry();
    void quite();
}

interface Abby{
    void listen();
    void solace();
}

interface Romancer extends Simen, Della, Abby{
    void romance();
}

class Person{
    String name;
}

abstract class YoungBoy{
    String name;
}

class Lover extends YoungBoy implements Romancer{
    public void love(){};
    public void protect(){};
    public void worry(){};
    public void quite(){};
    public void listen(){};
    public void solace(){};
    public void romance(){}

    void setName(String name) {
        this.name = name;
        print("his name is " + this.name);
    }
}

public class E14 {
    void likeSimen(Simen guy){
        print("brave, passionate and proactive");
    }

    void likeDella(Della beauty) {
        print("soft, dreamy and insecure");
    }

    void likeAbby(Abby lady) {
        print("wise, gentle and mature");
    }

    void relationship(Romancer lover) {
        print("starting a new relationship");
    }
    public static void main(String[] args){
        Lover simen = new Lover();
        simen.setName("Simen");
        E14 romance = new E14();
        romance.likeAbby(simen);
        romance.likeDella(simen);
        romance.likeSimen(simen);
        romance.relationship(simen);
    }
}
