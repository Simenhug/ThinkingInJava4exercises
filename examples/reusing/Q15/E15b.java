package reusing.Q15;

import static net.mindview.util.Print.*;
import reusing.E15;

class E15a{
    public static void main(String[] args) {
        E15 couple = new E15();
        //couple.confess(); //protected access
    }
}
public class E15b extends E15{
    void loveStory(){
        print("Simen met Della");
        super.confess();
    }
    public static void main(String[] args) {
        E15b story = new E15b();
        story.loveStory();
    }
}
