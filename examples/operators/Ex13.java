package operators;

import static net.mindview.util.Print.print;

public class Ex13 {

    public static void main(String[] args){
        char c = 's';
        printBinaryInt(c);
        c = 'i';
        printBinaryInt(c);
        c = 'm';
        printBinaryInt(c);
        c = 'e';
        printBinaryInt(c);
        c = 'n';
        printBinaryInt(c);
        c++;
        printBinaryInt(c);
        c &= 15;
        printBinaryInt(c);
    }
    static void printBinaryInt(int i){
        print("int " + i + " in binary is: " + Integer.toBinaryString(i));
    }
}
