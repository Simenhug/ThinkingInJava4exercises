package interfaces;
//Exercise 18: (2) Create a Cycle interface, with implementations Unicycle, Bicycle and Tricycle.
// Create factories for each type of Cycle, and code that uses these factories.

import static net.mindview.util.Print.print;

interface Cycle{
    void printWheels();
}
interface CycleFactory{
    Cycle getCycle();
}

class Unicycle implements Cycle{
    @Override
    public void printWheels() {
        print("I have one wheel");
    }
}

class Bicycle implements Cycle{
    @Override
    public void printWheels() {
        print("I have two wheels");
    }
}

class Tricycle implements Cycle{
    @Override
    public void printWheels() {
        print("I have three wheels");
    }
}

class UnicycleFactory implements CycleFactory{
    public Cycle getCycle(){ return new Unicycle();}
}
class BicycleFactory implements CycleFactory{
    public Cycle getCycle(){ return new Bicycle();}
}
class TricycleFactory implements CycleFactory{
    public Cycle getCycle(){ return new Tricycle();}
}

class Cycles{
    public Cycle cyclePlayer(CycleFactory factory) {
        return factory.getCycle();
    }
}
public class E18 {
    public static void main(String[] args) {
        Cycles cycles = new Cycles();
        Cycle
                uni = cycles.cyclePlayer(new UnicycleFactory()),
                bi = cycles.cyclePlayer(new BicycleFactory()),
                tri = cycles.cyclePlayer(new TricycleFactory());
        uni.printWheels();
        bi.printWheels();
        tri.printWheels();
    }
}
