package reusing;
import static net.mindview.util.Print.*;

//(2) Create a class called Amphibian. From this, inherit a class called Frog.
// Put appropriate methods in the base class.
// In main( ), create a Frog and upcast it to Amphibian and demonstrate that all the methods still work.
class Amphibian{
    String name;
    String environment;
    void climb(){ environment = "land";};
    void swim(){ environment = "water";};
    public String toString(){return name;}
    Amphibian(String nm){name = nm;};
    static void life(Amphibian i){
        print("in the early stage, " + i + " lives in water");
        i.climb();
        print("in the adult stage, " + i + " climbs to the land");
    }
}
public class Frog extends Amphibian{
    Frog(String nm){super(nm);}
    //E17;
    //(1) Modify Exercise 16 so that Frog overrides the method definitions from the base class
    // (provides new definitions using the same method signatures). Note what happens in main( ).
    //@Override
    static void life(Amphibian i){
        print("in the adult stage, " + i + " lives on land");
        i.swim();
        print("in the mating season, " + i + " swims back to the water");
    }
    public static void main(String[] args){
        Frog frog = new Frog("prince");
        Amphibian.life(frog);
        frog.life(frog);
    }
}
