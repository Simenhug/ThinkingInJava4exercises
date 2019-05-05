package reusing;

//(1) Create a final class and attempt to inherit from it.
final class noInheritance{
    String settled;
}

//public class E22 extends noInheritance{
public class E22{
    String unsettled;
}


//E22.java:8: error: cannot inherit from final noInheritance
//public class E22 extends noInheritance{
//                         ^
//1 error