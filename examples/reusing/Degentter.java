
package reusing;
import static net.mindview.util.Print.*;

class Cleanser {
    private String s = "Cleanser";
    public void append(String a) { s += a; }
    public void dilute() { append(" dilute()"); }
    public void apply() { append(" apply()"); }
    public void scrub() { append(" scrub()"); }
    public String toString() { return s; }
    public static void main(String[] args) {
        Cleanser x = new Cleanser();
        x.dilute(); x.apply(); x.scrub();
        print(x);
    }
}

public class Degentter {
    // Change a method:
    Cleanser cleanser = new Cleanser();
    public void dilute(){cleanser.dilute();}
    public void apply(){cleanser.apply();}
    public void scrub() {
        cleanser.append(" Detergent.scrub()");
        cleanser.scrub(); // Call base-class version
    }
    // Add methods to the interface:
    public void foam() { cleanser.append(" foam()"); }
    // Test the new class:
    public static void main(String[] args) {
        Degentter x = new Degentter();
        x.dilute();
        x.apply();
        x.scrub();
        x.foam();
        print(x);
        print("Testing base class:");
        Cleanser.main(args);
    }
}