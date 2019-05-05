package initialization;
import static net.mindview.util.Print.*;

//Write a switch statement for the enum in the previous example.
// For each case, output a description of that particular currency.

public class E22 {

    enum paperCurrency{
        ONE, FIVE, TEN, TWENTY, FIFTY, HUNDRED
    }

    paperCurrency money;

    E22(paperCurrency money) {
        this.money = money;
    }

    void describe(){
        System.out.println("Lemme see your bill");
        switch (money) {
            case ONE:
            case FIVE:
                print("good luck getting a snack");
                break;
            case TEN:
            case TWENTY:
                print("you could get a decent meal with this ... just one");
                break;
            case FIFTY:
                print("sounds like a great Saturday afternoon ... movie & dinner maybe?");
                break;
            case HUNDRED:
                print("now that's what I call some f**king money!!!");
                break;
            default:
                    print("you poor bastard ... I feel ya man");
        }
    }

    public static void main(String[] args) {
        //E22 money = new E22(paperCurrency.HUNDRED);
        E22
                money1 = new E22(paperCurrency.FIVE),
                money2 = new E22(paperCurrency.FIFTY),
                money3 = new E22(paperCurrency.HUNDRED);

        money1.describe();
        money2.describe();
        money3.describe();
    }
}
