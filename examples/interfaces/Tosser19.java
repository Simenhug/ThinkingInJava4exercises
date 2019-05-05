package interfaces;
import java.util.Random;

import static net.mindview.util.Print.*;

//Exercise 19: (3) Create a framework using Factory Methods that
// performs both coin tossing and dice tossing.

interface Tosser{
    Random random = new Random(99);
    void tossing();
}
interface TossFactory{
    Tosser getTosser();
}

class Coin implements Tosser{
    @Override
    public void tossing(){
        int side = random.nextInt(2);
        switch (side) {
            default:
            case 0:
                print("it's a head"); break;
            case 1:
                print("it's a tail"); break;
        }
    }
}
class CoinFactory implements TossFactory{
    @Override
    public Tosser getTosser(){return new Coin();}
}

class Dice implements Tosser{
    @Override
    public void tossing(){
        int side = random.nextInt(6);
        switch (side) {
            default:
            case 0: print("you rolled a one"); break;
            case 1: print("you rolled a two"); break;
            case 2: print("you rolled a three"); break;
            case 3: print("you rolled a four"); break;
            case 4: print("you rolled a five"); break;
            case 5: print("you rolled a six"); break;
        }
    }
}
class DiceFactory implements TossFactory{
    @Override
    public Tosser getTosser(){return new Dice();}
}

public class Tosser19 {
    Tosser playTosser(TossFactory factory) {
        return factory.getTosser();
    }

    public static void main(String[] args) {
        Tosser19 t19 = new Tosser19();
        Tosser
                coin = t19.playTosser(new CoinFactory()),
                dice = t19.playTosser(new DiceFactory());
        coin.tossing();
        dice.tossing();
    }
}
