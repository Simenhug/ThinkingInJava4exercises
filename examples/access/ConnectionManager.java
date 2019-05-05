package access;
import static net.mindview.util.Print.*;

//Following the form of the example Lunch.java, create a class called ConnectionManager that manages a fixed array of Connection objects.
// The client programmer must not be able to explicitly create Connection objects, but can only get them via a static method in ConnectionManager.
// When the ConnectionManager runs out of objects, it returns a null reference. Test the classes in main( ).
class Connection{
    private Connection(){}
    public static Connection buildConnection(){
        return new Connection();
    }
}
public class ConnectionManager {
    Connection[] connections = new Connection[10];

    public static void main(String[] args) {
        ConnectionManager manager = new ConnectionManager();
        for (int i = 0; i < 3; i++) {
            manager.connections[i] = Connection.buildConnection();
        }
        for(Connection i:manager.connections){
            print(i);
        }
    }
}
