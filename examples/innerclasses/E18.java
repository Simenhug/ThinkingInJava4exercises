package innerclasses;

import static net.mindview.util.Print.print;

//Exercise 18: (1) Create a class containing a nested class. In main( ), create an instance of the nested class.
class Shell18{
    private int eger;
    static class Core18{
        String coreQuote = "core built";
        void Quoting(){
            print(coreQuote);
        }
    }
    static Core18 core18(){
        return new Core18();
    }
}
public class E18 {
    public static void main(String[] args) {
        Shell18.Core18 meat = Shell18.core18();
        meat.Quoting();
    }
}
