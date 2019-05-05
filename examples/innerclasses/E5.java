package innerclasses;
///Exercise 5: (1) Create a class with an inner class.
// In a separate class, make an instance of the inner class.

class Shell{
    class Core{
        String soft = "softcore";
    }
    public Core peel(){return new Core();}
}
public class E5 {
    public static void main(String[] args) {
        Shell shell = new Shell();
        Shell.Core core = shell.peel();
        System.out.println(core.soft);
    }
}
