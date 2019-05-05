//: innerclasses/GreenhouseController.java
// Configure and execute the greenhouse system.
// {Args: 5000}
package innerclasses;
import innerclasses.controller.*;
import innerclasses.GreenhouseControls.*;

import java.util.LinkedList;

//holding Exercise 13: (3) In the innerclasses/GreenhouseController.java example,
// the class Controller uses an ArrayList.
// Change the code to use a LinkedList instead, and use an Iterator to cycle through the set of events.
public class GreenhouseController {
  public static void main(String[] args) {
    GreenhouseControls gc = new GreenhouseControls();
    // Instead of hard-wiring, you could parse
    // configuration information from a text file here:
    gc.addEvent(gc.new Bell(900));
    LinkedList<Event> eventLinkedList = new LinkedList<Event>();
    Event[] eventList = {
      gc.new ThermostatNight(0),
      gc.new LightOn(200),
      gc.new LightOff(400),
      gc.new WaterOn(600),
      gc.new WaterOff(800),
      gc.new LightOn(1000),
      gc.new LightOff(1200),
      gc.new ThermostatDay(1400)
    };
    for (Event event : eventList) {
      eventLinkedList.add(event);
    }
    gc.addEvent(gc.new Restart(2000, eventList));
    if(args.length == 1)
      gc.addEvent(
        new GreenhouseControls.Terminate(
          new Integer(args[0])));
    gc.run();
  }
} /* Output:
Bing!
Thermostat on night setting
Light is on
Light is off
Greenhouse water is on
Greenhouse water is off
Thermostat on day setting
Restarting system
Terminating
*///:~
