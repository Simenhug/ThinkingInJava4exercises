package initialization;

public class E4 {

    E4(){
        System.out.println("default");
    }

    E4(String arg){
        System.out.println("default "+arg);
    }

    public static void main(String[] args) {
        E4 e4 = new E4("Della");
    }
}
