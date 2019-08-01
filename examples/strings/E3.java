package strings;

//: (1) Modify Turtle.java so that it sends all output to System.err.
import java.io.*;
import java.util.*;

public class E3 {
    private String name;
    private Formatter f;
    public E3(String name, Formatter f) {
        this.name = name;
        this.f = f;
    }
    public void move(int x, int y) {
        f.format("%s The E3 is at (%d,%d)\n", name, x, y);
    }
    public static void main(String[] args) {
        PrintStream outAlias = System.err;
        E3 tommy = new E3("Tommy",
                new Formatter(System.err));
        E3 terry = new E3("Terry",
                new Formatter(outAlias));
        tommy.move(0,0);
        terry.move(4,8);
        tommy.move(3,4);
        terry.move(2,5);
        tommy.move(3,3);
        terry.move(3,3);
    }
}