package reusing;
import static net.mindview.util.Print.*;

//(3) Add a proper hierarchy of dispose( ) methods to all the classes in Exercise 9.
class Component1{
    Component1(int i){
        print("building component1");
    }
    void dispose(){print("component1 disposed");}
}
class Component2{
    Component2(int i){
        print("building component2");
    }
    void dispose(){print("component2 disposed");}
}
class Component3{
    Component3(int i){
        print("building component3");
    }
    void dispose(){print("component3 disposed");}
}
class Root{
    Component1 c1 = new Component1(99);
    Component2 c2 = new Component2(99);
    Component3 c3 = new Component3(99);

    Root(int i) {
        print("I AM ROOOOOOOT!");
    }
    void dispose(){
        c3.dispose();
        c2.dispose();
        c1.dispose();
        print("root disposed");
    }
}
public class E12 extends Root{
    E12(int i) {
        super(99);
    }
    public static void main(String[] args) {
        E12 e12 = new E12(99);
        try {
            print("e12 object constructed");
        }finally {
            e12.dispose();
        }

    }
}
