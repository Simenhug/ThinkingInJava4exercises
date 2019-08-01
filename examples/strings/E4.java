package strings;
//Exercise 4: (3) Modify Receipt.java so that the widths are all controlled by a single set of constant values.
// The goal is to allow you to easily change a width by changing a single value in one place.

import java.util.*;

public class E4 {
    private double total = 0;
    private Formatter f = new Formatter(System.out);
    private int[] width = {5, 10, 15};
    public void printTitle() {
        f.format("%-" + width[2] + "s %" + width[0] + "s %" + width[1] + "s\n", "Item", "Qty", "Price");
        f.format("%-" + width[2] + "s %" + width[0] + "s %" + width[1] + "s\n", "----", "---", "-----");
    }
    public void print(String name, int qty, double price) {
        f.format("%-" + width[2] + ".15s %" + width[0] + "s %" + width[1] + ".2f\n", name, qty, price);
        total += price;
    }
    public void printTotal() {
        f.format("%-" + width[2] + "s %" + width[0] + "s %" + width[1] + ".2f\n", "Tax", "", total*0.06);
        f.format("%-" + width[2] + "s %" + width[0] + "s %" + width[1] + "s\n", "", "", "-----");
        f.format("%-" + width[2] + "s %" + width[0] + "s %" + width[1] + ".2f\n", "Total", "",
                total * 1.06);
    }
    public static void main(String[] args) {
        E4 E4 = new E4();
        E4.printTitle();
        E4.print("Jack's Magic Beans", 4, 4.25);
        E4.print("Princess Peas", 3, 5.1);
        E4.print("Three Bears Porridge", 1, 14.29);
        E4.printTotal();
    }
} /* Output:
Item              Qty      Price
----              ---      -----
Jack's Magic Be     4       4.25
Princess Peas       3       5.10
Three Bears Por     1      14.29
Tax                         1.42
                           -----
Total                      25.06
*///:~
