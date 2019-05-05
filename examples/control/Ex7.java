package control;
import static net.mindview.util.Print.*;

public class Ex7 {
    static void count1(int n){
        for(int i=1;i<n;i++){
            print(i);
        }
    }

    static void count2(int n){
        for(int i=1;i<n;i++){
            print(i);
            if (i==99)
                break;
        }
    }

    static void count3(int n){
        for(int i=1;i<n;i++){
            print(i);
            if (i==99)
                return;
        }
    }
    public static void main(String[] args){
        count1(101);
        count2(101);
        count3(101);
    }
}
