package initialization;
import static net.mindview.util.Print.*;

public class E6 {



    void bark(int inte, double doub){
        print("int bark double sniff");
    }

    void bark(double doub, int inte){
        print("double sniff int bark");
    }

    void testConstant(){
        print("test 7");
        bark(7, 7.0);
    }

    public static void main(String[] args) {
        int integer = 7;
        char Char = 7;
        byte Byte = 7;
        long Long = 7;
        double Double = 7;
        E6 e6 = new E6();
//        dog.bark(integer);
//        dog.bark(Char);
//        dog.bark(Byte);
//        dog.bark(Long);
//        dog.bark(Double);
        print("fun begins");
        e6.bark(integer,Double);
        e6.bark(Double, integer);
    }


}
