package holding;


//Exercise 7: (3) Create a class, then make an initialized array of objects of your class.
// Fill a List from your array. Create a subset of your List by using subList( ), then remove this subset from your List.

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static net.mindview.util.Print.print;

class SimenLove{
    String lover = "Della";

    SimenLove(String gf) {
        lover = gf;
    }

    SimenLove(){};

    @Override
    public String toString() {
        return lover;
    }
}

public class E7 {
    public static void main(String[] args) {
        SimenLove[] dellas = new SimenLove[]{
                new SimenLove(),
                new SimenLove("Abby"),
                new SimenLove("Jing"),
                new SimenLove("Jasmine")
        };
        List<SimenLove> lovers = new ArrayList<SimenLove>(Arrays.asList(dellas));
        List<SimenLove> newbie = lovers.subList(2,4);
        print("newbie: " + newbie);
        lovers.removeAll(newbie);
        print(lovers);
    }
}
