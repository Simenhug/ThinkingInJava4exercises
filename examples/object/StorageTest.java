import java.lang.*;
import java.util.*;

public class StorageTest {
    static int storage(String s){
        return s.length()*2;
    }
    public static void main(String[] args){
        String word1 = "I ";
        String word2 = "want ";
        String word3 = "money!";
        int totalBytes = storage(word1) + storage(word2) + storage(word3);
        System.out.println(word1 + word2 + word3);
        System.out.println("total bytes: " + totalBytes);
    }
}
