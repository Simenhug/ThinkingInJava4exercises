package innerclasses;

//Exercise 19: (2) Create a class containing an inner class that itself contains an inner class.
// Repeat this using nested classes. Note the names of the .class files produced by the compiler.

class InnerWay{
    class InnerDouble{
        class InnerTriple{};
    }
}

class NestWay{
    static class NestedTwice{
        static class TripleNest{}
    }
}

public class E19 {
    public static void main(String[] args) {
        InnerWay i = new InnerWay();
        NestWay n = new NestWay();
        NestWay.NestedTwice nestedTwice = new NestWay.NestedTwice();
        NestWay.NestedTwice.TripleNest tripleNest = new NestWay.NestedTwice.TripleNest();
        InnerWay.InnerDouble innerDouble = i.new InnerDouble();
        InnerWay.InnerDouble.InnerTriple innerTriple = innerDouble.new InnerTriple();
    }
}
//
//public class E19 {
//    E19() { System.out.println("E19()"); }
//    private class E19Inner {
//        E19Inner() { System.out.println("E19Inner()"); }
//        private class E19InnerInner {
//            E19InnerInner() {
//                System.out.println("E19InnerInner()");
//            }
//        }
//    }
//    private static class E19Nested {
//        E19Nested() { System.out.println("E19Nested()"); }
//        private static class E19NestedNested {
//            E19NestedNested() {
//                System.out.println("E19NestedNested()");
//            }
//        }
//    }
//    public static void main(String[] args) {
//        E19Nested en = new E19Nested();
//        E19Nested.E19NestedNested enn = new E19Nested.E19NestedNested();
//        E19 e19 = new E19();
//        E19.E19Inner ei = e19.new E19Inner();
//        E19.E19Inner.E19InnerInner eii = ei.new E19InnerInner();
//    }
//}
