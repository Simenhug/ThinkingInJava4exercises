package holding;

//Exercise 16: (5) Create a Set of the vowels. Working from UniqueWords.Java,
// count and display the number of vowels in each input word,
// and also display the total number of vowels in the input file.

import net.mindview.util.TextFile;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.*;

import static net.mindview.util.Print.*;

public class E16 {
    public static void main(String[] args) {
        Set<Character> vowels = new HashSet<Character>();
        char[] cs = ("A E I O U a e i o u").toCharArray();
        for (char c : cs) {
            vowels.add(c);
        }
        List<String> allWords = new ArrayList<String>
                (new TextFile("UniqueWords.java", "\\W+"));
        Iterator<String> iterator = allWords.iterator();
        int totalCount = 0;
        while (iterator.hasNext()) {
            int vowelCount = 0;
            String nextWord = iterator.next();
            char[] chars = nextWord.toCharArray();
            for (char c : chars) {
                if (vowels.contains(c)) {
                    vowelCount++;
                    totalCount++;
                }
            }
            print(nextWord + " has " + vowelCount + " vowels");
        }
        print(totalCount);
    }
}
