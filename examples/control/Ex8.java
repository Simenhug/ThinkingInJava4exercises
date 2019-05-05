package control;
import java.util.*;
import static net.mindview.util.Print.*;
import static net.mindview.util.Range.*;


public class Ex8 {
    public static void main(String[] args){
        for (int i: range(10)) {
            switch (i){
                case 1:
                case 2: print("smaller ones, " + i); //break;
                case 3:
                case 5:
                case 7: print("odd numbers, " + i); //break;
                case 9: print("biggest odd, " + i); //break;
                default: print("not a special case, number is " + i);
            }
        }
    }
}
