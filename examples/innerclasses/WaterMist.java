package innerclasses;

import innerclasses.controller.*;
//Exercise 25: (3) Inherit from GreenhouseControls in GreenhouseControls.java to add Event inner classes that turn water mist generators on and off.
// Write a new version of GreenhouseController.java to use these new Event objects.
public class WaterMist extends GreenhouseControls{
    private boolean mist = false;
    public class MistOn extends Event{
        public MistOn(long delayTime) {
            super(delayTime);
        }
        @Override
        public void action() {
            mist = true;
        }
        public String toString(){
            return "mist generator is on";}
    }
    public class MistOff extends Event{
        public MistOff(long delayTime) {
            super(delayTime);
        }
        @Override
        public void action() {
            mist = false;
        }
        public String toString(){
            return "mist generator is off";}
    }
}
