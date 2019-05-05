package innerclasses;

//Exercise 9: (1) Create an interface with at least one method, 
// and implement that interface by defining an inner class within a method, 
// which returns a reference to your interface.

//Exercise 10: (1) Repeat the previous exercise but define the inner class
// within a scope within a method.

import static net.mindview.util.Print.print;

interface Ninetynine{
    void say();
}

public class E9 {
    public Ninetynine buildInner(boolean alwasyTrue){
        if (alwasyTrue) {
            class Alita implements Ninetynine {
                public void say() {
                    print("this inner class implements 99");
                }
            }
            return new Alita();
        }
        else{
            class TEMP implements Ninetynine{
                public void say(){}
            }
            return new TEMP();
        }
    }

    public static void main(String[] args) {
        E9 e9 = new E9();
        e9.buildInner(true).say();
    }
}
