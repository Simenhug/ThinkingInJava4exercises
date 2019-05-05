package holding;
import net.mindview.util.TextFile;

import java.util.*;
import static net.mindview.util.Print.*;

//Exercise 21: (3) Using a Map<String,Integer>, follow the form of UniqueWords.java to
// create a program that counts the occurrence of words in a file.
// Sort the results using Collections.sort( ) with a second argument of
// String.CASE_INSENSITIVE_ORDER (to produce an alphabetic sort), and display the result.

public class E21 {
    public static void main(String[] args) {
        List<String> file = new ArrayList<String>(new TextFile("SetOperations.java", "\\W+"));
        Collections.sort(file, String.CASE_INSENSITIVE_ORDER);
        Map<String, Integer> UniqueWords = new HashMap<>();
        Iterator<String> iterator = file.iterator();
        while (iterator.hasNext()) {
            String word = iterator.next();
            Integer integer = UniqueWords.get(word);
            UniqueWords.put(word, integer == null? 1: integer++);
        }
        print(UniqueWords);
    }
}
