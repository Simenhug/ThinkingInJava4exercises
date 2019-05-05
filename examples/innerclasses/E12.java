package innerclasses;
//Exercise 7: (2) Create a class with a private field and a private method.
// Create an inner class with a method that modifies the outer-class field
// and calls the outer-class method. In a second outer-class method,
// create an object of the inner class and call its method,
// then show the effect on the outer-class object.

//Exercise 12:(1) Repeat Exercise 7 using an anonymous inner class.

import static net.mindview.util.Print.print;

interface Inner7{
   void setShyDella();
}

class Outer7{
    private String shyDella = "this is shy Della";
    private void shymethod(){
        print(shyDella);
    }
    Inner7 buildInner(final String Simen){
        return new Inner7(){
             public void setShyDella(){
                shyDella = Simen;
                shymethod();
            }
        };
    }
    public void engageInner(String Simen){
        Inner7 temp = buildInner(Simen);
        temp.setShyDella();
    }
}

public class E12 {
    public static void main(String[] args) {
        Outer7 outer7 = new Outer7();
        outer7.engageInner("Della loves Simen");
    }
}
