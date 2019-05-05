import java.util.*;
import java.lang.*;



public class DataOnlyTest {

    public static void main(String[] args){
        class DataOnly{
            int i;
            double d;
            boolean b;
        }
        DataOnly data = new DataOnly();
        data.i = 7;
        data.d = 8.0;
        data.b = true;
        System.out.println(data.i);
        System.out.println(data.b);
        System.out.println(data.d);
    }
}
