package innerclasses;

//Exercise 20: (1) Create an interface containing a nested class. Implement this interface and create an instance of the nested class.

import static net.mindview.util.Print.print;

interface face20{
    class Static20{
        Static20(){
            print("making static20");
        }
    }
}
public class E20 {
    public static void main(String[] args) {
        face20.Static20 facestat = new face20.Static20();
    }
}
