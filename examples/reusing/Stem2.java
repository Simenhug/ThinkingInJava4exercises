package reusing;
        import static net.mindview.util.Print.*;

//(2) Create a class called Root that contains an instance of each of the classes (that you also create)
// named Component1, Component2, and Component3. Derive a class Stem2 from Root that also contains an instance of each “component.”
// All classes should have default constructors that print a message about that class.

//Modify the previous exercise so that each class only has non-default constructors.
class Component1{
    Component1(int i){
        print("building component1");
    }
}
class Component2{
    Component2(int i){
        print("building component2");
    }
}
class Component3{
    Component3(int i){
        print("building component3");
    }
}
class Root{
    Component1 c1 = new Component1(99);
    Component2 c2 = new Component2(99);
    Component3 c3 = new Component3(99);

    Root(int i) {
        print("I AM ROOOOOOOT!");
    }
}
public class Stem2 extends Root{
    Stem2(int i) {
        super(99);
    }
    public static void main(String[] args) {
        Stem2 Stem2 = new Stem2(99);
    }
}
