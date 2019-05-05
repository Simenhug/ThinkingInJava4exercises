package operators;

import static net.mindview.util.Print.*;
import java.util.*;
import java.lang.*;


public class HexaOctal {
    public static void main(String[] args) {
        int i1 = 0x347f;
        int i2 = 034;
        print("hex to string: " + Long.toBinaryString(i1));
        print("octal to string: " + Long.toBinaryString(i2));
    }
}
