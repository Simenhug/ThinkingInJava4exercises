package interfaces;
import java.util.*;
import polymorphism.music.Note;
import static net.mindview.util.Print.*;
//(3) Refactor Musics.java by moving the common methods in Wind, Percussion and Stringed into an abstract class.

//Exercise 10: (3) Modify Musics.java by adding a Playable interface.
// Move the play( ) declaration from Instrument to Playable.
// Add Playable to the derived classes by including it in the implement s list.
// Change tune( ) so that it takes a Playable instead of an Instrument.

interface Playable{
    void play(Note n);
}
abstract class Instrument {
    abstract public String toString();
    abstract void adjust();
}

class Wind extends Instrument implements Playable {
    public void play(Note n) { print("Wind.play() " + n); }
    public String toString() { return "Wind"; }
    void adjust() { print("Adjusting Wind"); }
}

class Percussion extends Instrument implements Playable{
    public void play(Note n) { print("Percussion.play() " + n); }
    public String toString() { return "Percussion"; }
    void adjust() { print("Adjusting Percussion"); }
}

class Stringed extends Instrument implements Playable{
    public void play(Note n) { print("Stringed.play() " + n); }
    public String toString() { return "Stringed"; }
    void adjust() { print("Adjusting Stringed"); }
}

class Boombox extends Instrument implements Playable{
    public void play(Note n) { print("Boombox.play() " + n); }
    public String toString() { return "Boombox"; }
    void adjust() { print("Adjusting Boombox"); }
}

class Brass extends Wind {
    public void play(Note n) { print("Brass.play() " + n); }
    void adjust() { print("Adjusting Brass"); }
}

class Woodwind extends Wind {
    public void play(Note n) { print("Woodwind.play() " + n); }
    public String toString() { return "Woodwind"; }
}

class RandomGenerator{
    Random rand = new Random(99);
    public Playable next(){
        int i = rand.nextInt(6);
        switch (i) {
            default:
            case 0: return new Wind();
            case 1: return new Percussion();
            case 2: return new Stringed();
            case 3: return new Boombox();
            case 4: return new Brass();
            case 5: return new Woodwind();
        }
    }
}

public class Music9 {
    // Doesn't care about type, so new types
    // added to the system still work right:
    public static void tune(Playable i) {
        // ...
        i.play(Note.MIDDLE_C);
    }
    public static void tuneAll(Playable[] e) {
        for(Playable i : e)
            tune(i);
    }
    public static void main(String[] args) {
        Woodwind wind = new Woodwind();
        Percussion percussion = new Percussion();
        Stringed stringed = new Stringed();
        print(wind + " " + percussion + " " + stringed);
        RandomGenerator randomGenerator = new RandomGenerator();
        // Upcasting during addition to the array:
        Playable[] orchestra = new Playable[9];
        for(int i=0;i<9;i++){
            orchestra[i] = randomGenerator.next();
        }
        tuneAll(orchestra);
    }
}
