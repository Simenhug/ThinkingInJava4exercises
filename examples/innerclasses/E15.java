package innerclasses;

//Exercise 15: (2) Create a class with a non-default constructor (one with arguments) and no default constructor (no "no-arg" constructor).
// Create a second class that has a method that returns a reference to an object of the first class.
// Create the object that you return by making an anonymous inner class that inherits from the first class.

import static net.mindview.util.Print.print;

class NoNothing{
    NoNothing(int i){}
    void sayNothing(){};
}

class Something{
    NoNothing outOfNothing(int i){
        return new NoNothing(i){
            void sayNothing(){print("not saying anything");}
        };
    }
    void dosomething(){
        NoNothing n = outOfNothing(99);
        n.sayNothing();
    }
}
public class E15 {
    public static void main(String[] args) {
        Something something = new Something();
        something.dosomething();
    }
}
