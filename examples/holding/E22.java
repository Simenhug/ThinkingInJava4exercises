package holding;
import net.mindview.util.TextFile;

import java.util.*;
import static net.mindview.util.Print.*;

//Exercise 21: (3) Using a Map<String,Integer>, follow the form of UniqueWords.java to
// create a program that counts the occurrence of words in a file.
// Sort the results using Collections.sort( ) with a second argument of
// String.CASE_INSENSITIVE_ORDER (to produce an alphabetic sort), and display the result.

//Exercise 22: (5) Modify the previous exercise so that it uses a class
// containing a String and a count field to store each different word,
// and a Set of these objects to maintain the list of words.

class WordCounter{
    static int total;
    String word;
    Integer frequency;
    public WordCounter(String string, Integer count) {
        word = string;
        frequency = count;
    }
}
public class E22 {
    public static void main(String[] args) {
        List<String> file = new ArrayList<String>(new TextFile("SetOperations.java", "\\W+"));
        Collections.sort(file, String.CASE_INSENSITIVE_ORDER);
        Set<WordCounter> UniqueWords = new HashSet<>();
        Iterator<String> iterator = file.iterator();
        while (iterator.hasNext()) {
            String word = iterator.next();
            WordCounter keeper = new WordCounter(word, 0);
            for (String words : file) {
                if (word == words) {
                    keeper.frequency++;
                    keeper.total++;
                }
                UniqueWords.add(keeper);
            }
        }
        Iterator<WordCounter> iterator1 = UniqueWords.iterator();
        while (iterator1.hasNext()) {
            WordCounter item = iterator1.next();
            print(item.word + " " + item.frequency);
        }
    }
}
