package interfaces;
import interfaces.E5a.*;
import static net.mindview.util.Print.*;
//Exercise 5: (2) Create an interface containing three methods, in its own package. Implement the interface in a different package.
public class E5 implements E5a{
    public void printthings(){print("print");};
    public int process(){return number*2;};
    public void getNumber(){
        print(number);
    }
    public static void main(String[] args){
        E5 e5 = new E5();
        e5.getNumber();
    }
}
