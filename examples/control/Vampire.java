package control;
import java.util.ArrayList;
import static net.mindview.util.Print.*;
import static net.mindview.util.Range.*;

public class Vampire {
    /**
     * permutation function
     * @param str string to calculate permutation for
     * @param l starting index
     * @param r end index
     */
    void permute(ArrayList<String> permutations, String str, int l, int r) {
        if (l == r)
            permutations.add(str);
        else
        {
            for (int i = l; i <= r; i++)
            {
                str = swap(str,l,i);
                permute(permutations, str,l+1, r);
                str = swap(str,l,i);
            }
        }
    }

    /**
     * Swap Characters at position
     * @param a string value
     * @param i position 1
     * @param j position 2
     * @return swapped string
     */
    String swap(String a, int i, int j)
    {
        char temp;
        char[] charArray = a.toCharArray();
        temp = charArray[i] ;
        charArray[i] = charArray[j];
        charArray[j] = temp;
        return String.valueOf(charArray);
    }

    ArrayList<String> twoIntMultiplied(int i, int j){
        String combined = Integer.toString(i) + Integer.toString(j);
        ArrayList<String> permutations = new ArrayList<String>();
        permute(permutations,combined,0,combined.length()-1);
        //printnb(i+" "+j+" "+combined+"\n");
        return permutations;
    }

    public static void main(String[] args){
        Vampire vampire = new Vampire();
        for (int i: range(10,100)
             ) {
            for (int j: range(10,100)
                 ) {
                ArrayList<String> permutatuons = vampire.twoIntMultiplied(i,j);
                //print("i*j == " + i*j + " result = " + result);
                for (String s: permutatuons) {
                    if((i*j) == Integer.parseInt(s)){
                        print("vampire found: " + s);
                    }
                }
            }
        }
    }
}
