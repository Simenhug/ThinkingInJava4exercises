package innerclasses;

import innerclasses.controller.*;

//Exercise 25: (3) Inherit from GreenhouseControls in GreenhouseControls.java
// to add Event inner classes that turn water mist generators on and off.
// Write a new version of GreenhouseController.java to use these new Event objects.
public class MistEvent extends GreenhouseController {
    public static void main(String[] args) {
        GreenhouseControls gc = new GreenhouseControls();
        WaterMist wm = new WaterMist();
        gc.addEvent(gc.new Bell(900));
        Event[] eventList = {
                gc.new ThermostatNight(0),
                wm.new MistOn(200),
                wm.new MistOff(200),
                gc.new ThermostatDay(400)
        };
        gc.addEvent(gc.new Restart(2000, eventList));
        if (args.length == 1)
            gc.addEvent(
                    new GreenhouseControls.Terminate(
                            new Integer(args[0])));
        gc.run();
    }
}
