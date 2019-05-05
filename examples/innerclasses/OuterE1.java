package innerclasses;
import static net.mindview.util.Print.*;

//Exercise 1: (1) Write a class named Outer that contains an inner class named Inner.
// Add a method to Outer that returns an object of type Inner.
// In main( ), create and initialize a reference to an Inner.

//Exercise 3: (1) Modify Exercise 1 so that Outer has a private String field (initialized by the constructor),
// and Inner has a toString( ) that displays this field. Create an object of type Inner and display it.

public class OuterE1 {
    private String lover;
    class Inner{
        String say = "Della";
        public String toString(){return lover;}
    }
    public Inner getInner(){return new Inner();}
    OuterE1(){
        lover = "Della loves Simen";
    }

    public static void main(String[] args) {
        OuterE1 outerE1 = new OuterE1();
        Inner inner = outerE1.getInner();
        print(inner.say);
        print(inner);
    }
}
