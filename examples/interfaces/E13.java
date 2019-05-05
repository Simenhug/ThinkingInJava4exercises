package interfaces;
//Exercise 13: (2) Create an interface, and inherit two new interfaces from that interface.
// Multiply inherit a third interface from the second two. 2

interface Spaceships{
    void setProductionYear(int year);
}

interface Apollo extends Spaceships{
    void launch();
}

interface Challenger extends Spaceships{
    void mission();
}

interface Dragon extends Apollo, Challenger{
    void setPassengers(int passenger);
}

class ShipBuilder implements Dragon{
    int productionYear;
    int passengers;
    public void setProductionYear(int year) {
        productionYear = year;
    }
    public void setPassengers(int passenger) {
        passengers = passenger;
    }
    public void launch(){
        System.out.println("launching");
    }
    public void mission(){
        System.out.println("mission accomplished");
    }
}
public class E13 {
    public static void main(String[] args) {
        ShipBuilder Alita = new ShipBuilder();
        Alita.setPassengers(2);
        Alita.setProductionYear(2019);
        Alita.launch();
        Alita.mission();
    }
}
