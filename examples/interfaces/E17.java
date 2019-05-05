package interfaces;
//Exercise 17: (2) Prove that the fields in an interface are implicitly static and final.

interface staticFinalFields{
    int marketCap = 6;
}
public class E17 implements staticFinalFields{
//    void setMarketCap(int cap){
//        marketCap = cap;
//    }
    void saysomething(){
        System.out.println("hello ya");
    }
}
//E17.java:9: error: cannot assign a value to final variable marketCap
//        marketCap = cap;
//        ^
//1 error