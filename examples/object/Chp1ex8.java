public class Chp1ex8 {
    public static void main(String[] args){
        StaticTest st1 = new StaticTest();
        StaticTest st2 = new StaticTest();
        st1.i++;
        System.out.println(st1.i);
        System.out.println(st2.i);
    }
}

class StaticTest {
    static int i = 89;
}