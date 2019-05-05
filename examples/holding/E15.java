package holding;
import net.mindview.util.Stack;

import java.util.*;

import static net.mindview.util.Print.print;
import static net.mindview.util.Print.printnb;

//Exercise 15: (4) Stacks are often used to evaluate expressions in programming languages.
// Using net.mindview.util.Stack, evaluate the following expression,
// where’+’ means "push the following letter onto the stack," and’-’ means
// "pop the top of the stack and print it": "+U+n+c—+e+r+t—+a-+i-+n+t+y—+ -+r+u—+l+e+s—"

public class E15 {
    public static void main(String[] args) {
        String instruction = "+U+n+c—+e+r+t—+a—+i—+n+t+y—+ —+r+u—+l+e+s—";
        char[] instructionArray = instruction.toCharArray();
        ArrayList<Character> instructionFlow = new ArrayList<Character>();
        for (char c : instructionArray) {
            instructionFlow.add(c);
        }
        Iterator<Character> iterator = ((ArrayList) instructionFlow).iterator();
        Stack<Character> characterStack = new Stack<Character>();
        while (iterator.hasNext()) {
            char order = iterator.next();
            //print("show: " + order);
            if (order == '+') {
                char next = iterator.next();
                //print("adding  "+next);
                characterStack.push(next);
            }
            else if (order == '—') {
                char result = characterStack.pop();
                print(result);
            }
            else {
                print("Exception: " + order);
            }
        }
    }
}
