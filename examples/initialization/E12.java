package initialization;

//
////Create a class with a finalize( ) method that prints a message.
//// In main( ), create an object of your class. Explain the behavior of your program.

class Tank{

    boolean filled = false;

    Tank(boolean emptyOrFilled) {
        filled = emptyOrFilled;
    }

    void empty(){
        filled = false;
    }

    protected void finalize(){
        if (filled) {
            System.out.println("Error: Tank not empty!" + "\n");
            //IT HAS TO BE SYSTEM.OUT.PRINTLN. Using mindview.print won't work!!!!!!!
        }
    }
}

public class E12 {

    public static void main(String[] args) {
        Tank tank1 = new Tank(true);
        Tank tank2 = new Tank(true);
        tank1.empty();
        System.out.println("first forced gc(): ");
        System.gc();
        // Force finalization on exit but using method
        // deprecated since JDK 1.1:
        System.out.println("try deprecated runFinalizersOnExit(true):");
        System.runFinalizersOnExit(true);
        System.out.println("last forced gc():");
        System.gc();
    }
}
