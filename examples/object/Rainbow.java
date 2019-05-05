public class Rainbow {
    int anIntegerRepresentingColors;
    void changeTheHueOfTheColor(int newHue){
        anIntegerRepresentingColors = newHue;
    }
    public static void main(String[] args){
        Rainbow rainbow = new Rainbow();
        rainbow.anIntegerRepresentingColors = 1;
        System.out.println(rainbow.anIntegerRepresentingColors);
        rainbow.changeTheHueOfTheColor(7);
        System.out.println(rainbow.anIntegerRepresentingColors);
    }
}
