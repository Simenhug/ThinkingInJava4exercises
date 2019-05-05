package holding;
import java.util.*;
import static net.mindview.util.Print.*;
import holding.Gerbil1;

//Exercise 18: (3) Fill a HashMap with key-value pairs.
// Print the results to show ordering by hash code. Extract the pairs, sort by key,
// and place the result into a LinkedHashMap. Show that the insertion order is maintained.

//Exercise 19: (2) Repeat the previous exercise with a HashSet and LinkedHashSet.
public class E19 {
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

        Set<String> KeySet = new HashSet<>(GerbilMap.keySet());
        print("Set: " + KeySet);
        Iterator<String> iterator1 = KeySet.iterator();
        LinkedHashMap<String, Gerbil1> LinkedMap = new LinkedHashMap<>();
        while (iterator1.hasNext()) {
            String key = iterator1.next();
            LinkedMap.put(key, GerbilMap.get(key));
        }
        Iterator<String> iterator2 = LinkedMap.keySet().iterator();
        while (iterator2.hasNext()) {
            String key = iterator2.next();
            print(key);
        }

        LinkedHashSet<String> LinkedKeySet = new LinkedHashSet<>(GerbilMap.keySet());
        print("LinkedKeySet: " + LinkedKeySet);
        Iterator<String> iterator3 = LinkedKeySet.iterator();
        LinkedHashMap<String, Gerbil1> sortedMap = new LinkedHashMap<>();
        while (iterator3.hasNext()) {
            String key = iterator3.next();
            sortedMap.put(key, GerbilMap.get(key));
        }
        Iterator<String> iterator4 = sortedMap.keySet().iterator();
        while (iterator4.hasNext()) {
            String key = iterator4.next();
            print(key);
        }

    }
}
