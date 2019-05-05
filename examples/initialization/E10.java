package initialization;
import static net.mindview.util.Print.*;
//
////Create a class with a finalize( ) method that prints a message.
//// In main( ), create an object of your class. Explain the behavior of your program.

class Process{

    boolean unfinishedProcess = false;

    Process(boolean processStatus) {
        unfinishedProcess = processStatus;
    }

    void finishProcess(){
        unfinishedProcess = false;
    }

    protected void finalize(){
        if (unfinishedProcess) {
            System.out.println("Error: unfinished process" + "\n");
            //IT HAS TO BE SYSTEM.OUT.PRINTLN. Using mindview.print won't work!!!!!!!
        }
    }
}

public class E10 {

    public static void main(String[] args) {
        Process process1 = new Process(true);
        Process process2 = new Process(true);
        process1.finishProcess();
        new Process(true);
        System.gc();
    }
}

//class WebBank {
//    boolean loggedIn = false;
//    WebBank(boolean logStatus) {
//        loggedIn = logStatus;
//    }
//    void logIn() {
//        loggedIn = true;
//    }
//    void logOut() {
//        loggedIn = false;
//    }
//    protected void finalize() {
//        if(loggedIn)
//            System.out.println("Error: still logged in");
//        // Normally, you'll also do this:
//        // super.finalize(); // Call the base-class version
//    }
//}
//
//public class E10 {
//    public static void main(String[] args) {
//        WebBank bank1 = new WebBank(true);
//        WebBank bank2 = new WebBank(true);
//        // Proper cleanup: log out of bank1 before going home
//        bank1.logOut();
//        // Drop the reference, forget to cleanup:
//        new WebBank(true);
//        // Force garbage collection and finalization:
//        System.gc();
//    }
//}