package operators;
import static net.mindview.util.Print.*;

class AliasTest{
    float i;
}


public class Aliasing {
    public static void main (String[] args){
        AliasTest test1 = new AliasTest();
        AliasTest test2 = new AliasTest();
        test1.i = 0.12f;
        test2.i = 0.34f;
        print("test 1 initial condition: " + test1.i);
        print("test 2 initial condition: " + test2.i);
        test1 = test2;
        print("test1 = test2");
        print("now test1 is " + test1.i);
        print("now test2 is " + test2.i);
        test1.i = 0.56f;
        print("change test1 = 0.56f");
        print("now both test1 test2 equals " + test1.i + test2.i);

    }

}
