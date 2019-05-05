package innerclasses;

//Exercise 26: (2) Create a class with an inner class that has a non-default constructor (one that takes arguments).
// Create a second class with an inner class that inherits from the first inner class.

class Out26{
    class Inner26{
        Inner26(String name){
            this.name = name;
        }
        String name;
    }
}

class Out26b{
    class Inner26b extends Out26.Inner26 {
        // to inherit an inner class with no default constructor,
        // the constructor of the derived class should have two parameters:
        // a reference to the enclosing class of the inherited inner class, and a parameter from the inherited inner class's constructor
        // in the constructor body, enclosingClassReference.super (second parameter)
        Inner26b(Out26 o26, String name) {
            o26.super(name);
        }
    }
}
public class E26 {
    public static void main(String[] args) {
        Out26 out26 = new Out26();
        Out26b out26b = new Out26b();
        Out26b.Inner26b inner26b = out26b.new Inner26b(out26, "Della");
        System.out.println(inner26b.name);
    }
}
