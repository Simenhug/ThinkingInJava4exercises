package initialization;
import static net.mindview.util.Print.*;

//Create an array of String objects and assign a String to each element. Print the array by using a for loop.

public class E16 {
    public static void main(String[] args) {
        String[] della = new String[]{
                "Della",
                "loves",
                "Simen"
        };
        for(int i=0;i<della.length; i++){
            printnb(della[i] + " ");
        }
    }
}
