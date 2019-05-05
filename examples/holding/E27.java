package holding;
import java.util.*;
import static net.mindview.util.Print.*;

//Exercise 27: (2) Write a class called Command that contains a String and has a method operation( ) that displays the String.
// Write a second class with a method that fills a Queue with Command objects and returns it.
// Pass the filled Queue to a method in a third class that consumes the objects in the Queue and
// calls their operation( ) methods.
class Command{
    String commander;
    public void operation(){
        print(commander);
    }
    public Command(String order){
        commander = order;
    }
}
class ControlCenter{
    public static Queue<Command> SetCommands(){
        Queue<Command> Commands = new LinkedList<Command>();
        String[] names = "Della Abby Tavy Jing Joy Jasmine Ellie Hugo Willem Faye".split(" ");
        for (String name : names) {
            ((LinkedList<Command>) Commands).offer(new Command(name));
        }
        return Commands;
    }
}
class Battle{
    public static void Engage(Queue<Command> Commands){
        while (Commands.peek() != null) {
            Command command = Commands.remove();
            command.operation();
        }
    }
}
public class E27 {
    public static void main(String[] args) {
        Queue<Command> Commander = ControlCenter.SetCommands();
        Battle.Engage(Commander);
    }
}
