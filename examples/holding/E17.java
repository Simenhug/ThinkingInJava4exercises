package holding;
import java.util.*;
import static net.mindview.util.Print.*;
import holding.Gerbil1;

//Exercise 17: (2) Take the Gerbil class in Exercise 1 and put it into a Map instead,
// associating each Gerbil’s name (e.g. "Fuzzy" or "Spot") as a String (the key) for
// each Gerbil (the value) you put in the table. Get an Iterator for the keySet( )
// and use it to move through the Map, looking up the Gerbil for each key and printing
// out the key and telling the Gerbil to hop( ).
public class E17 {
    public static void main(String[] args) {
        HashMap<String, Gerbil1> GerbilMap = new HashMap<>();
        String[] names = "Della Abby Tavy Jing Joy Jasmine Ellie Hugo Willem Faye".split(" ");
        for(int i=0;i<10;i++){
            GerbilMap.put(names[i], new Gerbil1(i));
        }
        Iterator<String> iterator = GerbilMap.keySet().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            print(key);
            GerbilMap.get(key).hop();
        }
    }
}
