package innerclasses;

//Exercise 23: (4) Create an interface U with three methods. Create a class A with a method that produces a reference to a U by building an anonymous inner class.
// Create a second class B that contains an array of U. B should have one method that accepts and stores a reference to a U in the array,
// a second method that sets a reference in the array (specified by the method argument) to null,
// and a third method that moves through the array and calls the methods in U. In main( ),
// create a group of A objects and a single B. Fill the B with U references produced by the A objects.
// Use the B to call back into all the A objects. Remove some of the U references from the B.

import static net.mindview.util.Print.print;

interface U{
    void u1();
    void u2();
    void u3();
    void callback();
}

class A{
    String name;
    U forU(){
        return new U() {
            {print("building anonymous U");}
            @Override
            public void u1(){
                print("u1();");
            }

            @Override
            public void u2() {
                print("u2()");
            }

            @Override
            public void u3() {
                print("u3()");
            }

            @Override
            public void callback(){
                print(name);
            }
        };
    }

    public A(String name) {
        this.name = name;
    }
}

class B{
    U[] uarrary;
    int pointer = 0;
    boolean end(){
        return (pointer == uarrary.length);
    }
    void next(){
        if(pointer < uarrary.length-1)
            pointer++;
        else
            print("uarrary is full");
    }
    U current(){
        return uarrary[pointer];
    }
    void add(U u){
        if (!end()) {
            uarrary[pointer] = u;
            next();
        }
    }

    void toNull(int location) {
        uarrary[location] = null;
    }

    void callingU(){
        for (U u : uarrary) {
            if (u != null) {
                u.u1();
                u.u2();
                u.u3();
            }
        }
    }

    public B(int size){
        uarrary = new U[size];
    }
}

public class E23 {
    public static void main(String[] args) {
        A
                a1 = new A("a1"),
                a2 = new A("a2"),
                a3 = new A("a3");
        B bee = new B(9);
        for (U u: bee.uarrary) {
            int which = bee.pointer%3;
            switch (which){
                default:
                    print("can't add more U");
                case 0: bee.add(a1.forU()); break;
                case 1: bee.add(a2.forU()); break;
                case 2: bee.add(a3.forU()); break;
            }
        }
        for (U u : bee.uarrary) {
            u.callback();
        }
        bee.toNull(1);
        bee.toNull(4);
        for (U u : bee.uarrary) {
            print(u);
        }
    }
}
