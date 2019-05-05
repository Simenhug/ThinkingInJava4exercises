package holding;
import java.util.*;
import static net.mindview.util.Print.*;

//Exercise 24: (2) Fill a LinkedHashMap with String keys and objects of your choice.
// Now extract the pairs, sort them based on the keys, and reinsert them into the Map.

public class E24 {
    public static void main(String[] args) {
        LinkedHashMap<String, Gerbil1> GerbilMap = new LinkedHashMap<>();
        String[] names = "Della Abby Tavy Jing Joy Jasmine Ellie Hugo Willem Faye".split(" ");
        for (int i = 0; i < 10; i++) {
            GerbilMap.put(names[i], new Gerbil1(i));
            printnb(names[i] + " ");
        }
        print();
        print();
        print();
        List<String> keys = new ArrayList<String>(GerbilMap.keySet());
        Collections.sort(keys, String.CASE_INSENSITIVE_ORDER);
        LinkedHashMap<String, Gerbil1> sortedGerbil = new LinkedHashMap<>();
        Iterator<String> iterator = keys.iterator();
        while ((iterator.hasNext())) {
            String key = iterator.next();
            sortedGerbil.put(key, GerbilMap.get(key));
        }
        print(sortedGerbil);

    }
}
