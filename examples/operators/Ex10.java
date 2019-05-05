package operators;
import static net.mindview.util.Print.*;
import java.lang.*;


public class Ex10 {
    public static void main(String[] args){
        int i = 0xAAAAA;
        int j = 0x55555;
        print("i = " + Integer.toBinaryString(i));
        print("j = " + Integer.toBinaryString(j));
        print("i&j = " + Integer.toBinaryString(i&j));
        print("i|j = " + Integer.toBinaryString(i|j));
        print("i^j = " + Integer.toBinaryString(i^j));
        print("~i = " + Integer.toBinaryString(~i));
        print("~j = " + Integer.toBinaryString(~j));
    }
}
