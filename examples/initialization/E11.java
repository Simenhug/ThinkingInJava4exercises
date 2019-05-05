package initialization;

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

public class E11 {

    public static void main(String[] args) {
        Process process1 = new Process(true);
        Process process2 = new Process(true);
        process1.finishProcess();
        new Process(true);
        Runtime.getRuntime().runFinalization();
        System.gc();
        System.runFinalizersOnExit(true);
    }
}
