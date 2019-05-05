package operators;


import java.util.*;
import static net.mindview.util.Print.print;

public class Coinflip {
    public static void main(String[] args){
        Random random = new Random(42);
        int coin = random.nextInt();
        if (coin % 2 == 0){
            print("it's a head");
        }
        else {
            print("it's a tail");
        }
    }
}

