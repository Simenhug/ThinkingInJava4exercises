package control;
import java.lang.reflect.Array;
import static net.mindview.util.Print.*;
import static net.mindview.util.Range.*;

public class Fibonacci {
    public static void main(String[] args){
        int n = Integer.parseInt(args[0]);
        if (n == 0) {
            print("nothing here");
            return;
        }
        if (n == 1) {
            print("fibonacci: 1");
            return;
        }
        if (n == 2){
            print("fibonacci: 1 2");
            return;
        }
        printnb("fibonacci: 1 1 ");
        Integer[] fibonacci = new Integer[n];
        fibonacci[0] = 1;
        fibonacci[1] = 1;
        for (int i: range(2,n)) {
            fibonacci[i] = fibonacci[i-2] + fibonacci[i-1];
            printnb(fibonacci[i] + " ");
        }
        print();
    }
}
