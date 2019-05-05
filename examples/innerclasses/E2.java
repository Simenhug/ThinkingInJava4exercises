package innerclasses;

//Exercise 2: (1) Create a class that holds a String, and has a toString( ) method that displays this String.
// Add several instances of your new class to a Sequence object, then display them.

interface Selector {
    boolean end();
    Object current();
    void next();
}

class DellaSings{
    String sing = "I love Simen~";
    public String toString(){return sing;}
}

class Sequence {
    private Object[] items;
    private int next = 0;
    public Sequence(int size) { items = new Object[size]; }
    public void add(Object x) {
        if(next < items.length)
            items[next++] = x;
    }
    private class SequenceSelector implements Selector {
        private int i = 0;
        public boolean end() { return i == items.length; }
        public Object current() { return items[i]; }
        public void next() { if(i < items.length) i++; }
    }
    public Selector selector() {
        return new SequenceSelector();
    }
    public static void main(String[] args) {
        Sequence sequence = new Sequence(10);
        for(int i = 0; i < 10; i++)
            sequence.add(new DellaSings());
        Selector selector = sequence.selector();
        while(!selector.end()) {
            System.out.print(selector.current() + " ");
            selector.next();
        }
    }
}

public class E2{
    public static void main(String[] args) {
        Sequence.main(args);
    }
}