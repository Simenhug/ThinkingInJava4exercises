package operators;
import static net.mindview.util.Print.*;

public class Ex11 {
    public static void main(String[] args){
        int i = 0xAAAAA;
        printBinaryInt(i);
        i >>= 1;
        printBinaryInt(i);
        i >>= 1;
        printBinaryInt(i);
        i >>= 1;
        printBinaryInt(i);
        i >>= 1;
        printBinaryInt(i);
        i >>= 1;
        printBinaryInt(i);

    }
    static void printBinaryInt(int i){
        print("int " + i + " in binary is: " + Integer.toBinaryString(i));
    }
}

