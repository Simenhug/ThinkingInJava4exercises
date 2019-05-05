// access/foreign/Foreign.java
package access.Foreign;
import access.local.*;
public class Foreign {
    public static void main(String[] args) {
        PackagedClass pc = new PackagedClass();
    }
}


/* Compiler error because: PackagedClass in not public, so no access outside of
 * package. Moving Foreign to local would allow package access to PackagedClass.
 */