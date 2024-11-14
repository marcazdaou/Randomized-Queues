import dsa.LinkedStack;

import stdlib.StdIn;
import stdlib.StdOut;

public class Sort {
    // Entry point.
    public static void main(String[] args) {
        LinkedDeque<String> d = new LinkedDeque<>();  // Create a queue d.
        while (!StdIn.isEmpty()) {  // For each word w read from standard input
            String w = StdIn.readString();
            if (d.isEmpty() || less(w, d.peekFirst())) {
                d.addFirst(w);  // Add w to the front of d if it is less than the first word in d
            } else if (less(d.peekLast(), w)) {
                d.addLast(w);  // Add w to the back of d if it is greater than the last word in d
            } else {
                LinkedStack<String> s = new LinkedStack<>(); // create queue s
                while (!d.isEmpty() && less(d.peekFirst(), w)) {  // remove words less than w
                    // from the front of d and store them in stack s;
                    s.push(d.removeFirst());
                }
                d.addFirst(w);  // add w to the front of d.
                while (!s.isEmpty()) {
                    d.addFirst(s.pop());  //  add words from s also to the front of d.
                }
            }
        }
        while (!d.isEmpty()) {
            StdOut.println(d.removeFirst());  // Write the words from d to standard output.

        }
    }

    // Returns true if v is less than w according to their lexicographic order, and false otherwise.
    private static boolean less(String v, String w) {

        return v.compareTo(w) < 0;
    }
}
