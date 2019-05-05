package initialization;

// Create an enum of the least-valuable six types of paper currency.
//Loop through the values( ) and print each value and its ordinal( ).

public class E21 {

    enum paperCurrency{
        ONE, FIVE, TEN, TWENTY, FIFTY, HUNDRED
    }

    public static void main(String[] args) {
        for (paperCurrency currency : paperCurrency.values()) {
            System.out.print(currency + " , order " + currency.ordinal());
            System.out.println();
        }
    }
}
