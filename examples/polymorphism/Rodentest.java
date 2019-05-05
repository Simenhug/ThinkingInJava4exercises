package polymorphism;
import static net.mindview.util.Print.*;

//Create an inheritance hierarchy of Rodent: Mouse, Gerbil, Hamster, etc.
// In the base class, provide methods that are common to all Rodents,
// and override these in the derived classes to perform different behaviors
// depending on the specific type of Rodent. Create an array of Rodent,
// fill it with different specific types of Rodents, and call your base-class
// methods to see what happens.

//Exercise 14: (4) Modify Exercise 12 so that one of the member objects is a shared object
// with reference counting, and demonstrate that it works properly.
class Germ{
    public int refCount = 0;
    private static int count = 0;
    private final int id = count++;
    Germ(){
        print("creating " + this);
    }
    public String toString(){
        return ("Germ " + id);
    }
    public void addRef(){refCount++;}
    public void dispose(){
        if (--refCount == 0) {
            print("disposing " + this);
        }
    }
}
class Size{
    int big;

    Size(int i) {
        big = i;
        print("size " + big);
    }
}
class Rodent{
    Size s = new Size(1);
    Germ germ;
    void tweet(){
        print("Rodey");
    }
    Rodent(Germ germs){
        germs.refCount++;
        print("creating Rodent, refCount is now " + germs.refCount);
        this.germ = germs;
    }
    public void dispose(){
        print("disposing Rodent, ref count left: " + (germ.refCount-1));
        germ.dispose();
    }
}
class Mouse extends Rodent{
    Size s = new Size(2);
    @Override
    void tweet(){
        print("twee twee");
    }
    Mouse(Germ germs){
        super(germs);
        print("creating Mouse");
        //this.germ = germs;
    }
}
class Gerbil extends Rodent{
    Size s = new Size(3);
    @Override
    void tweet(){
        print("ger ger");
    }
    Gerbil(Germ germs){
        super(germs);
        print("creating Gerbil");
        //this.germ = germs;
    }
}
class Hamster extends Rodent{
    Size s = new Size(4);
    @Override
    void tweet(){
        print("ham ham");
    }
    Hamster(Germ germs){
        super(germs);
        print("creating Hamster");
        //this.germ = germs;
    }
}
public class Rodentest {
    static void sound(Rodent i) {
        i.tweet();
    }

    public static void main(String[] args) {
        Germ flu = new Germ();
        Rodent[] rodents = new Rodent[]{
                new Mouse(flu),
                new Gerbil(flu),
                new Hamster(flu)
        };
        for(Rodent i:rodents){
            print(i.germ);
            sound(i);
            i.dispose();
        }
    }
}
