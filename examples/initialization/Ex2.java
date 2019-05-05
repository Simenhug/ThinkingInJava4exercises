package initialization;

public class Ex2 {
    String test;
    String trial = "set to alita";

    Ex2(String i){
        this.test = i;
    }

    public static void main(String[] args) {
        Ex2 case1 = new Ex2("set to vector");
        System.out.println(case1.trial);
        System.out.println(case1.test);
    }
}
