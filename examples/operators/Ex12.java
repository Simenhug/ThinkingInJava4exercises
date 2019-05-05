package operators;
import static net.mindview.util.Print.*;

public class Ex12 {
    public static void main(String[] args){
        int i = 0xFFF;
        printBinaryInt(i);
        i <<= 1;
        int binaryLength = Integer.toBinaryString(i).length();
        print("binary length = " + binaryLength);
        for (int j=0;j<binaryLength;j++){
            i >>>= 1;
            printBinaryInt(i);
        }
    }
    static void printBinaryInt(int i){
        print("int " + i + " in binary is: " + Integer.toBinaryString(i));
    }
}

