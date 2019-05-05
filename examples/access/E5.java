package access;
//Create a class with public, private, protected, and package-access fields and method members.
// Create an object of this class and see what kind of compiler messages you get when you try to access all the class members.
// Be aware that classes in the same directory are part of the “default” package.
import static net.mindview.util.Print.*;

class Della{
    public String publicDel = "public della accessed";
    protected String protectedDel = "protected della accessed";
    void setDel(){
        publicDel = "publicDel has been updated";
        System.out.println(protectedDel);
    }
    private void printDel() {
        System.out.println("printing: " + publicDel);
    }
}

public class E5 {
    public static void main(String[] args){
        Della della = new Della();
        System.out.println(della.publicDel);
        System.out.println(della.protectedDel);
        della.setDel();
        // della.printDel(); //private access
    }
}