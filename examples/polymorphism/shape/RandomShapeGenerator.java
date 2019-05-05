//: polymorphism/shape/RandomShapeGenerator.java
// A "factory" that randomly creates shapes.
package polymorphism.shape;
import java.util.*;

//holding Exercise 31: (3) Modify polymorphism/shape/RandomShapeGenerator.java to make it Iterable.
// You’ll need to add a constructor that takes the number of elements that you want the iterator to produce before stopping.
// Verify that it works.
public class RandomShapeGenerator implements Iterable<Shape>{
  private Random rand = new Random(48);
  public Shape next() {
    switch(rand.nextInt(4)) {
      default:
      case 0: return new Circle();
      case 1: return new Square();
      case 2: return new Triangle();
      case 3: return new Hexagon();
    }
  }
  private Shape[] shapes;
  RandomShapeGenerator(int n) {
    shapes = new Shape[n];
    for(int i = 0; i < n; i++)
      shapes[i] = next();
  }
  public Iterator<Shape> iterator(){
    return new Iterator<Shape>() {
      private int index = 0;
      @Override
      public boolean hasNext() {
        return index < shapes.length;
      }

      @Override
      public Shape next() {
        return shapes[index++];
      }

      @Override
      public void remove(){
        throw new UnsupportedOperationException();
      }
    };
  }

  public static void main(String[] args) {
    RandomShapeGenerator randomShapeGenerator = new RandomShapeGenerator(20);
    Iterator<Shape> iterator = randomShapeGenerator.iterator();
    while (iterator.hasNext()) {
      Shape shape = iterator.next();
      System.out.println(shape);
    }
  }
} ///:~
