package innerclasses;

//Exercise 21: (2) Create an interface that contains a nested class that has a static method that calls the methods of your interface and displays the results.
// Implement your interface and pass an instance of your implementation to the method.

import static net.mindview.util.Print.print;

interface Face21{
    void sayStuff();
    class static21{
        void speak(Face21 stupid){
            stupid.sayStuff();
        }
    }
}

class RealFace implements Face21{
    public void sayStuff(){
        print("say stuff");}
}

public class E21 {
    public static void main(String[] args) {
        Face21 realFace = new RealFace();
        (new Face21.static21()).speak(realFace);
    }
}
