package control;

import java.util.Random;
import static net.mindview.util.Print.*;

public class Ex3 {
    public static void main(String[] args){
        Random random = new Random(42);
        while(true){
            int simen = random.nextInt(100);
            int della = random.nextInt(100);
            if(simen > della)
                print("simen > della");
            else if(simen == della)
                print("simen == della");
            else
                print("simen < della");
        }
    }
}