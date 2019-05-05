package control;
import static net.mindview.util.Print.*;
import java.lang.*;

//Repeat Exercise 10 from the previous chapter, using the ternary operator and a bitwise test
// to display the ones and zeroes, instead of Integer.toBinaryString( ).


public class Ex5 {
    static void binaryToString(int i){
        if(i==0) printnb(i);
        else {
            int leadingZero = Integer.numberOfLeadingZeros(i);
            i <<= leadingZero;
            for(int j=0; j < 32-leadingZero; j++){
                int n = (Integer.numberOfLeadingZeros(i)==0)? 1:0;
                printnb(n);
                i <<= 1;
            }
        }
        print();
    }
    public static void main(String[] args){
        int i = 0xAAAAA;
        int j = 0x55555;
        print("using toBinaryString");
        print("i = " + Integer.toBinaryString(i));
        print("j = " + Integer.toBinaryString(j));
        print("i&j = " + Integer.toBinaryString(i&j));
        print("i|j = " + Integer.toBinaryString(i|j));
        print("i^j = " + Integer.toBinaryString(i^j));
        print("~i = " + Integer.toBinaryString(~i));
        print("~j = " + Integer.toBinaryString(~j));
        print("\n"+"\n"+"\n");
        print("using new method: binaryToString");
        print("i = ");
        binaryToString(i);
        print("j = ");
        binaryToString(j);
        print("i&j = ");
        binaryToString(i&j);
        print("i|j = ");
        binaryToString(i|j);
        print("i^j = ");
        binaryToString(i^j);
        print("~i = ");
        binaryToString(~i);
        print("~j = ");
        binaryToString(~j);

    }
}
