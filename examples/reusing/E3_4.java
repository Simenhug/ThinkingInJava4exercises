package reusing;
//(2) Prove the previous sentence.
//(2) Prove that the base-class constructors are (a) always called and (b) called
//before derived-class constructors.
import static net.mindview.util.Print.print;

class Art {
    Art() { print("Art constructor"); }
}
class Drawing extends Art {
    Drawing() { print("Drawing constructor"); }
}
public class E3_4 extends Drawing {
    public E3_4(){print("E34 constructor");};
    public static void main(String[] args) {
        E3_4 x = new E3_4();
    }
}