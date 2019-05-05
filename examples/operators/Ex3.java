package operators;
import static net.mindview.util.Print.*;

class Alita{
    float lita;
}

public class Ex3 {

    static void Panzer(Alita angel){
        angel.lita = 99f;
    }

    public static void main(String[] args){
        Alita alita = new Alita();
        alita.lita = 66f;
        print("initial lita condition: " + alita.lita);
        Panzer(alita);
        print("after Panzer lita condition: " + alita.lita);
    }

}
