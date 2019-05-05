package innerclasses;
import java.util.Random;

import static net.mindview.util.Print.*;

//Exercise 19: (3) Create a framework using Factory Methods that
// performs both coin tossing and dice tossing.

//Exercise 17: (1) Modify the solution to Exercise 19 from the Interfaces chapter to use anonymous inner classes.

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

public class E17 {
    Tosser playTosser(TossFactory factory) {
        return factory.getTosser();
    }

    Coin flipCoin(){
        return new Coin(){
            {
                print("flipping a coin");}
        };
    }

    Dice tossDice(){
        return new Dice(){
            {
                print("tossing a dice");}
        };
    }

    public static void main(String[] args) {
        E17 t19 = new E17();
        Tosser
                coin = t19.flipCoin(),
                dice = t19.tossDice();
        coin.tossing();
        dice.tossing();
    }
}
