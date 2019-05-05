import java.util.*;
import java.lang.*;

public class ATypeName {

    public static void main(String[] args){
        class ATypeNameTest {
            int a = 7;
            String phrase = "fuck!";

            String startFucking() {
                System.out.println(a);
                System.out.println(phrase);
                return ("you're fucked!");
            }
        }
        ATypeNameTest fuckTest = new ATypeNameTest();
        fuckTest.startFucking();

        }
    }

