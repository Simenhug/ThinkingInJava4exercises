//: polymorphism/PolyConstructors.java
// Constructors and polymorphism
// don't produce what you might expect.
//Exercise 15: (2) Add a RectangularGlyph to PolyConstructors.java
// and demonstrate the problem described in this section.
package polymorphism;
import static net.mindview.util.Print.*;

class Glyph {
  void draw() { print("Glyph.draw()"); }
  Glyph() {
    print("Glyph() before draw()");
    draw();
    print("Glyph() after draw()");
  }
}	

class RoundGlyph extends Glyph {
  private int radius = 1;
  RoundGlyph(int r) {
    radius = r;
    print("RoundGlyph.RoundGlyph(), radius = " + radius);
  }
  void draw() {
    print("RoundGlyph.draw(), radius = " + radius);
  }
}

class RectangularGlyph extends Glyph{
  private int diagonal = 2;
  RectangularGlyph(int d){
    this.diagonal = d;
    print("RectangularGlyph.draw(), diagonal = " + diagonal);
  }
  void draw(){print("RectangularGlyph.draw(), diagonal = " + diagonal);}
}

public class PolyConstructors {
  public static void main(String[] args) {
    new RoundGlyph(5);
    new RectangularGlyph(99);
  }
} /* Output:
Glyph() before draw()
RoundGlyph.draw(), radius = 0
Glyph() after draw()
RoundGlyph.RoundGlyph(), radius = 5
*///:~
