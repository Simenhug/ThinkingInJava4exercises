package concurrency;

public class Skinny {
    private volatile double d; // Prevent optimization
    private static int counter = 0;
    private final int id = counter++;
    public Skinny() {
        // Expensive, interruptible operation:
        for(int i = 1; i < 10000; i++) {
            d += (Math.PI + Math.E) / (double)i;
        }
    }
    public void operation() { System.out.println(this + " losing weight"); }
    public String toString() { return "Skinny id: " + id; }
}
