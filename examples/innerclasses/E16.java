package innerclasses;
//Exercise 18: (2) Create a Cycle interface, with implementations Unicycle, Bicycle and Tricycle.
// Create factories for each type of Cycle, and code that uses these factories.

//Exercise 16: (1) Modify the solution to Exercise 18 from the Interfaces chapter to use anonymous inner classes.

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
    public Unicycle unimake(){
        return new Unicycle(){
            {
                print("unimaking");}
        };
    }
    public Bicycle bimake(){
        return new Bicycle(){
            {print("bimaking");}
        };
    }
    public Tricycle trimake(){
        return new Tricycle(){
            {
                print("trimaking");}
        };
    }
}
public class E16 {
    public static void main(String[] args) {
        Cycles cycles = new Cycles();
        Cycle
                uni = cycles.unimake(),
                bi = cycles.bimake(),
                tri = cycles.trimake();
        uni.printWheels();
        bi.printWheels();
        tri.printWheels();
    }
}
