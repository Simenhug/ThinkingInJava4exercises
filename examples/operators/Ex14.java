package operators;

//Exercise 14: (3) Write a method that takes two String arguments and uses all the boolean
// comparisons to compare the two Strings and print the results. For the == and !=, also perform the equals( ) test.
// In main( ), call your method with some different String objects.
import static net.mindview.util.Print.*;

public class Ex14 {

    static void fuckYourStringsUp(String fucker, String sucker){
        print("New test: ");
        print("String 1 = " + fucker);
        print("String 2 = " + sucker);
        //print("1 && 2 = " + fucker&&sucker);
        //print("1 || 2 = " + fucker||sucker);
        print("1 == 2 = " + fucker==sucker);
        print("1 != 2 = " + fucker!=sucker);
        print("1 .equals() 2 = " + fucker.equals(sucker));
        //print("!1 = " + !fucker);
        //print("!2 = " + !sucker);
        print("\n" + "\n");
    }

    public static void main(String[] args){
        fuckYourStringsUp("Simen", "Della");
        fuckYourStringsUp("Simen", "Simen");
        fuckYourStringsUp("xinmian", "Abby");
        fuckYourStringsUp("Simen", "simen");
    }
}
