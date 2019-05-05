package holding;

import java.lang.reflect.Array;
import java.util.*;

import static net.mindview.util.Print.print;
import static net.mindview.util.Print.printnb;

//Exercise 4: (3) Create a generator class that produces character names (as String objects) from your favorite movie
// (you can use Snow White or Star Wars as a fallback) each time you call next( ), and loops around to the beginning of the character list when it runs out of names.
// Use this generator to fill an array, an ArrayList, a LinkedList, a HashSet, a LinkedHashSet, and a TreeSet, then print each container.
class MovieCharacters{
    String[] characters = new String[]{
            "Alita",
            "Victor",
            "Hugo",
            "Nova",
            "Suri",
            "IDo"
    };
    int pointer = 0;
    public String next(){
        String result = characters[pointer];
        if (pointer < characters.length - 1) {
            pointer++;
        }
        else {
            pointer = 0;
        }
        return result;
    }

    public void printing() {
        for (String a : characters) {
            printnb(a + " ");
        }
    }
}
public class E4 {
    public static void initialize(Collection collection, MovieCharacters movie) {
        movie.pointer = 0;
        for (int i = 0; i < 10; i++) {
            collection.add(movie.next());
        }
    }
    public static void main(String[] args) {
        MovieCharacters Alita = new MovieCharacters();
        ArrayList arrayList = new ArrayList();
        LinkedList linkedList = new LinkedList();
        HashSet hashSet = new HashSet();
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        TreeSet treeSet = new TreeSet();
        E4.initialize(arrayList,Alita);
        E4.initialize(linkedList,Alita);
        E4.initialize(hashSet,Alita);
        E4.initialize(linkedHashSet,Alita);
        E4.initialize(treeSet,Alita);
        print(arrayList);
        print(linkedList);
        print(hashSet);
        print(linkedHashSet);
        print(treeSet);
    }
}
