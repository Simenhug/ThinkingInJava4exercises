package innerclasses;

//Exercise 9: (1) Create an interface with at least one method,
// and implement that interface by defining an inner class within a method,
// which returns a reference to your interface.

//Exercise 13:(1) Repeat Exercise 9 using an anonymous inner class.

import static net.mindview.util.Print.print;

interface Ninetynine{
    void say();
}

public class E13 {
    public Ninetynine buildInner(boolean alwasyTrue){
        if (alwasyTrue) {
            return new Ninetynine() {
                public void say() {
                    print("this inner class implements 99");
                }
            };
        }
        else{
            class TEMP implements Ninetynine{
                public void say(){}
            }
            return new TEMP();
        }
    }

    public static void main(String[] args) {
        E13 e13 = new E13();
        e13.buildInner(true).say();
    }
}
