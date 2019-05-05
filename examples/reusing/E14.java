package reusing;

//(1) In Car.java add a service( ) method to Engine and call this method in main( ).

class Engine {
    public void start() {}
    public void rev() {}
    public void stop() {}
    public void service(){
        System.out.println(("Engine Service"));}
}
class Wheel {
    public void inflate(int psi) {}
}
class Window {
    public void rollup() {}
    public void rolldown() {}
}
class Door {
    public Window window = new Window();
    public void open() {}
    public void close() {}
}
public class E14 {
    public Engine engine = new Engine();
    public Wheel[] wheel = new Wheel[4];
    public Door
            left = new Door(),
            right = new Door(); // 2-door
    public E14() {
        for(int i = 0; i < 4; i++)
            wheel[i] = new Wheel();
    }
    public static void main(String[] args) {
        E14 E14 = new E14();
        E14.left.window.rollup();
        E14.wheel[0].inflate(72);
        E14.engine.service();
    }
} ///:~