package interfaces;
import java.io.IOException;
import java.nio.CharBuffer;
import java.util.Random;
import java.util.Scanner;

import static net.mindview.util.Print.*;

//Exercise 16: (3) Create a class that produces a sequence of chars.
// Adapt this class so that it can be an input to a Scanner object.
class RandomCharMaker{
    Random random = new Random(99);
    public char[] MakeCharNoWar(int len){
        char[] chars = new char[len];
        char[] lowers = ("qwertyuiopasdfghjklzxcvbnm1234567890").toCharArray();
        for (int i=0;i<chars.length;i++) {
            chars[i] = lowers[random.nextInt(lowers.length)];
        }
        return chars;
    }
}

class ReadableCharMaker extends RandomCharMaker implements Readable{
    private int count;
    @Override
    public int read(CharBuffer cb) {
        if (count-- == 0) {
            return -1;
        }
        char[] output = MakeCharNoWar(8);
        for (char i : output) {
            cb.append(i);
        }
        cb.append(" ");
        return 8;
    }
    public ReadableCharMaker(int count){this.count = count;}
}

public class E16 {
    public static void main(String[] args) {
        Scanner s = new Scanner(new ReadableCharMaker(10));
        while (s.hasNext()) {
            print(s.next());
        }
    }
}
