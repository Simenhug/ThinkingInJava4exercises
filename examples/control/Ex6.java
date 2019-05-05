package control;

import static net.mindview.util.Print.*;

//Modify the two test( ) methods in the previous two programs so that they take two extra arguments,
// begin and end, and so that testval is tested to see if it is within the range between (and including) begin and end.


public class Ex6 {
    static boolean test(int testval, int begin, int end){
        if((testval < begin) || (testval > end))
            return false;
        else
            return true;
    }
    public static void main(String[] args) {
        print(test(10, 5, 12));
        print(test(5, 10, 12));
        print(test(5, 5, 10));
    }
}
