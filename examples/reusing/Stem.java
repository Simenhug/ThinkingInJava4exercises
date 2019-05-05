package reusing;
        import static net.mindview.util.Print.*;

//(2) Create a class called Root that contains an instance of each of the classes (that you also create)
// named Component1, Component2, and Component3. Derive a class Stem from Root that also contains an instance of each “component.”
// All classes should have default constructors that print a message about that class.
class Component1{
    Component1(){
        print("building component1");
    }
}
class Component2{
    Component2(){
        print("building component2");
    }
}
class Component3{
    Component3(){
        print("building component3");
    }
}
class Root{
    Component1 c1 = new Component1();
    Component2 c2 = new Component2();
    Component3 c3 = new Component3();
}
public class Stem extends Root{
    public static void main(String[] args) {
        Stem stem = new Stem();
    }
}
