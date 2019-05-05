package reusing;
//(2) In Beetle.java, inherit a specific type of beetle from class Beetle,
// following the same format as the existing classes. Trace and explain the output.
import static net.mindview.util.Print.*;

class Insect {
    private int i = 9;
    protected int j;
    Insect() {
        print("i = " + i + ", j = " + j);
        j = 39;
    }
    private static int x1 =
            printInit("static Insect.x1 initialized");
    static int printInit(String s) {
        print(s);
        return 47;
    }
}

class Beetle extends Insect {
    //non static
    private int k = printInit("Beetle.k initialized");
    public Beetle() {
        print("k = " + k);
        print("j = " + j);
    }
    //static
    private static int x2 =
            printInit("static Beetle.x2 initialized");
    public static void main(String[] args) {
        print("Beetle constructor");
        Beetle b = new Beetle();
    }
}

public class E24 extends Beetle{
    //non static
    private int e24 = printInit("static E24.e24 initialized");
    public E24(){
        print("e24 = " + e24);
        print("j = " + j);
    }
    //static
    private static int int24 = printInit("static E24.int24 initialized");

    public static void main(String[] args) {
        print("constructing E24");
        E24 e24 = new E24();
    }
}

