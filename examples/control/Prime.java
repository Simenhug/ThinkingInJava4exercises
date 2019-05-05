package control;

import static net.mindview.util.Print.*;

public class Prime {

    public static void main(String[] args){
        for(int i=2;i<1000;i++){
            boolean isPrime = true;
            for(int j=1;j<i;j++){
                if ((i%j == 0) && (j!=1)) {
//                    print("i = " + i + " and it's not a prime number");
//                    print("j = "+j);
                    isPrime = false;
                    break;
                }
            }
            if (isPrime)
                print(i);
        }
    }
}
