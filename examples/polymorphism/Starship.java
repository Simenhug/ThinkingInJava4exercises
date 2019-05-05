package polymorphism;
import java.util.Random;
import static net.mindview.util.Print.*;

//Exercise 16: (3) Following the example in Transmogrify.java,
// create a Starship class containing an AlertStatus reference that can indicate three different states.
// Include methods to change the states.

class AlertStatus{
    void broadCast(){print("no pending alerts");}
}

class BlueAlert extends AlertStatus{
    @Override
    void broadCast(){
        print("entering strong gravitational field");
    }
}

class OrangeAlert extends AlertStatus{
    @Override
    void broadCast(){
        print("hull integrity compromised");
    }
}

class RedAlert extends AlertStatus{
    @Override
    void broadCast(){
        print("engaging in battle");
    }
}

public class Starship {
    private AlertStatus status = new AlertStatus();
    public void alertSystem(int i){
        switch (i) {
            default:
            case 0: status = new BlueAlert(); break;
            case 1: status = new OrangeAlert(); break;
            case 2: status = new RedAlert(); break;
        }
    }

    public static void main(String[] args) {
        Random random = new Random(99);
        Starship UROM = new Starship();
        UROM.status.broadCast();
        UROM.alertSystem(random.nextInt(3));
        UROM.status.broadCast();
        UROM.alertSystem(random.nextInt(3));
        UROM.status.broadCast();
        UROM.alertSystem(random.nextInt(3));
        UROM.status.broadCast();
        UROM.alertSystem(random.nextInt(3));
        UROM.status.broadCast();
    }
}
