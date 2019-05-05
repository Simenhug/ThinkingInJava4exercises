package holding;
import net.mindview.util.RandomGenerator;

import java.util.*;
import static net.mindview.util.Print.*;

//Exercise 28: (2) Fill a PriorityQueue (using offer( )) with Double values created using java.util.Random,
// then remove the elements using poll( ) and display them.
public class E28 {
    public static void main(String[] args) {
        PriorityQueue<Double> doublePriority = new PriorityQueue<>();
        Random random = new Random(99);
        for(int i=0;i<1000;i++){
            doublePriority.offer(random.nextDouble());
        }
        while (doublePriority.peek() != null) {
            Double number = doublePriority.poll();
            print(number);
        }
    }
}
