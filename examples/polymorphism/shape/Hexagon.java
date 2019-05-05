//: polymorphism/shape/Circle.java
package polymorphism.shape;
import static net.mindview.util.Print.*;

public class Hexagon extends Shape {
    @Override
    public void draw() { print("Hexagon.draw()"); }
    @Override
    public void erase() { print("Hexagon.erase()"); }
    @Override
    public void printing(){print("Hexagon printing message");}
} ///:~