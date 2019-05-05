package holding;
import net.mindview.util.TextFile;

import java.util.*;
import static net.mindview.util.Print.*;

//Exercise 25: (3) Create a Map<String,ArrayList<Integer>>.
// Use net.mindview.TextFile to open a text file and read it
// in a word at a time (use "\\W+" as the second argument to the TextFile constructor).
// Count the words as you read them in, and for each word in the file,
// record in the ArrayList<Integer> the word count associated with that word—this is,
// in effect, the location in the file where that word was found.

//Exercise 26: (4) Take the resulting Map from the previous exercise and re-create
// the order of the words as they appeared in the original file.

public class E25 {
    public static void main(String[] args) {
        List<String> file = new ArrayList<String>(new TextFile("SetOperations.java", "\\W+"));
        Collections.sort(file, String.CASE_INSENSITIVE_ORDER);
        Map<String, ArrayList<Integer>> UniqueWords = new HashMap<>();
        Iterator<String> iterator = file.iterator();
        int wordCount = 0;
        while (iterator.hasNext()) {
            wordCount++;
            String word = iterator.next();
            if (!UniqueWords.keySet().contains(word)) {
                UniqueWords.put(word, new ArrayList<Integer>(Arrays.asList(wordCount)));
            }else {
                UniqueWords.get(word).add(wordCount);
            }
        }
        print(UniqueWords);

//        TreeSet<String> sorted = new TreeSet<>(UniqueWords.keySet());
//        Iterator<String> sortedIterator = sorted.iterator();
//        Map<String, ArrayList<Integer>> SortedUniqueWords = new LinkedHashMap<>();
//        while (sortedIterator.hasNext()) {
//            String newKey = sortedIterator.next();
//            SortedUniqueWords.put(newKey, UniqueWords.get(newKey));
//        }
        Map<String, ArrayList<Integer>> SortedUniqueWords = new TreeMap<>(UniqueWords);
        print(SortedUniqueWords);
    }
}
