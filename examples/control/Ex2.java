package control;

import java.util.Random;
import static net.mindview.util.Print.*;

public class Ex2 {
    public static void main(String[] args){
        Random random = new Random(42);
        for(int i=0;i<25;i++){
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
