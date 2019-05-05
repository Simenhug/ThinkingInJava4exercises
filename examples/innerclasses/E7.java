package innerclasses;
//Exercise 7: (2) Create a class with a private field and a private method.
// Create an inner class with a method that modifies the outer-class field
// and calls the outer-class method. In a second outer-class method,
// create an object of the inner class and call its method,
// then show the effect on the outer-class object.

import static net.mindview.util.Print.print;

class Outer7{
    private String shyDella = "this is shy Della";
    private void shymethod(){
        print(shyDella);
    }
    class Inner7{
        void setShyDella(String Simen){
            shyDella = Simen;
            shymethod();
        }
    }
    public void engageInner(String Simen){
        Inner7 temp = new Inner7();
        temp.setShyDella(Simen);
    }
}

public class E7 {
    public static void main(String[] args) {
        Outer7 outer7 = new Outer7();
        outer7.engageInner("Della loves Simen");
    }
}
