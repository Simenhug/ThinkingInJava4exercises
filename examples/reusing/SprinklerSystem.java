//: reusing/SprinklerSystem.java
// Composition for code reuse.
package reusing;
//Exercise 1: (2) Analyze SprinklerSystem.toString( ) in reusing/SprinklerSystem.java
// to discover whether writing the toString( ) with an explicit StringBuilder will
// save any StringBuilder creations.

class WaterSource {
  private String s;
  WaterSource() {
    System.out.println("WaterSource()");
    s = "Constructed";
  }
  public String toString() { return s; }
}	

public class SprinklerSystem {
  private String valve1, valve2, valve3, valve4;
  private WaterSource source = new WaterSource();
  private int i;
  private float f;
//  public String toString() {
//    return
//      "valve1 = " + valve1 + " " +
//      "valve2 = " + valve2 + " " +
//      "valve3 = " + valve3 + " " +
//      "valve4 = " + valve4 + "\n" +
//      "i = " + i + " " + "f = " + f + " " +
//      "source = " + source;
//  }
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("value1 = ");
    builder.append(valve1);
    builder.append(" ");
    builder.append("value2 = ");
    builder.append(valve2);
    builder.append(" ");
    builder.append("value3 = ");
    builder.append(valve3);
    builder.append(" ");
    builder.append("value1 = ");
    builder.append(valve1);
    builder.append("\n");
    builder.append("i = ");
    builder.append(i);
    builder.append(" ");
    builder.append("f = ");
    builder.append(f);
    builder.append(" ");
    builder.append("source = ");
    builder.append(source);
    return builder.toString();
  }
  public static void main(String[] args) {
    SprinklerSystem sprinklers = new SprinklerSystem();
    System.out.println(sprinklers);
  }
} /* Output:
WaterSource()
valve1 = null valve2 = null valve3 = null valve4 = null
i = 0 f = 0.0 source = Constructed
*///:~
