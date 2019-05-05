package holding;

//Exercise 16: (5) Create a Set of the vowels. Working from UniqueWords.Java,
// count and display the number of vowels in each input word,
// and also display the total number of vowels in the input file.

//Exercise 20: (3) Modify Exercise 16 so that you keep a count of the occurrence of each vowel.

import net.mindview.util.TextFile;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.*;

import static net.mindview.util.Print.*;

public class E20 {
    public static void main(String[] args) {
        Set<String> vowels = new HashSet<String>();
        vowels.addAll(Arrays.asList(("A E I O U a e i o u").split(" ")));
        List<String> allWords = new ArrayList<String>
                (new TextFile("UniqueWords.java", "\\W+"));
        HashMap<String, Integer> vowelCounter=new HashMap<String, Integer>();
        Iterator<String> iterator = allWords.iterator();
        int totalCount = 0;
        while (iterator.hasNext()) {
            String nextWord = iterator.next();
            char[] chars = nextWord.toCharArray();
            for (char c : chars) {
                switch (c) {
                    default: break;
                    case 'A':
                    case 'a':
                        Integer frequency = vowelCounter.get("Aa");
                        vowelCounter.put("Aa", frequency == null? 1: frequency+1);
                        totalCount++;
                        break;
                    case 'E':
                    case 'e':
                        frequency = vowelCounter.get("Ee");
                        vowelCounter.put("Ee", frequency == null? 1: frequency+1);
                        totalCount++;
                        break;
                    case 'I':
                    case 'i':
                        frequency = vowelCounter.get("Ii");
                        vowelCounter.put("Ii", frequency == null? 1: frequency+1);
                        totalCount++;
                        break;
                    case 'O':
                    case 'o':
                        frequency = vowelCounter.get("Oo");
                        vowelCounter.put("Oo", frequency == null? 1: frequency+1);
                        totalCount++;
                        break;
                    case 'U':
                    case 'u':
                        frequency = vowelCounter.get("Uu");
                        vowelCounter.put("Uu", frequency == null? 1: frequency+1);
                        totalCount++;
                        break;
                }

            }
        }
        print(vowelCounter);
        print(totalCount);
    }
}
