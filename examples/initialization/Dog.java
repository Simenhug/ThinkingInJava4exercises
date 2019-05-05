package initialization;
import static net.mindview.util.Print.*;

public class Dog {

    void bark(char i){
        print("char howl");
    }

    void bark(byte i){
        print("byte bite");
    }

    void bark(int i){
        print("int bark");
    }
    void bark(long i){
        print("long whorl");
    }

    void bark(double i){
        print("double sniff");
    }

    void testConstant(){
        print("test 7");
        bark(7);
    }

    public static void main(String[] args) {
        int integer = 7;
        char Char = 7;
        byte Byte = 7;
        long Long = 7;
        double Double = 7;
        Dog dog = new Dog();
        dog.bark(integer);
        dog.bark(Char);
        dog.bark(Byte);
        dog.bark(Long);
        dog.bark(Double);
        print("fun begins");
        dog.bark((double)integer);
        dog.bark((char) Double);
        dog.bark(7L);
    }


}
