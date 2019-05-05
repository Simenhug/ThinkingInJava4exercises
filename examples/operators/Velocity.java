package operators;
import java.util.*;
import static net.mindview.util.Print.*;

class Problem{
    int distance;
    int time;
    public void setDistance(int x){
        this.distance = x;
    }
    public void setTime(int y){
        this.time = y;
    }
}
public class Velocity {
    public static void main(String[] args){
        Random random = new Random(42);
        Problem problem = new Problem();
        int distance = random.nextInt(100)+1;
        int time = random.nextInt(15)+1;
        print("distance & time are: " + distance + " , " + time);
        problem.setDistance(distance);
        problem.setTime(time);
        print("the velocity in this problem is " + distance/time + " m/s.");
    }
}
