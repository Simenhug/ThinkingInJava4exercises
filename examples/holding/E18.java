package holding;
import java.util.*;
import static net.mindview.util.Print.*;
import holding.Gerbil1;

//Exercise 18: (3) Fill a HashMap with key-value pairs.
// Print the results to show ordering by hash code. Extract the pairs, sort by key,
// and place the result into a LinkedHashMap. Show that the insertion order is maintained.
public class E18 {
    public static void main(String[] args) {
        HashMap<String, Gerbil1> GerbilMap = new HashMap<>();
        String[] names = "Della Abby Tavy Jing Joy Jasmine Ellie Hugo Willem Faye".split(" ");
        for (int i = 0; i < 10; i++) {
            GerbilMap.put(names[i], new Gerbil1(i));
        }
        Iterator<String> iterator = GerbilMap.keySet().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            print(key);
        }
        print("now, LinkedHashMap");
        TreeSet<String> sortedKeySet = new TreeSet<>(GerbilMap.keySet());
        LinkedHashMap<String, Gerbil1> sortedMap = new LinkedHashMap<>();
        Iterator<String> iterator1 = sortedKeySet.iterator();
        while (iterator1.hasNext()) {
            String key = iterator1.next();
            sortedMap.put(key, GerbilMap.get(key));
        }
        Iterator<String> iterator2 = sortedMap.keySet().iterator();
        while (iterator2.hasNext()) {
            String key = iterator2.next();
            print(key);
        }
    }
}
