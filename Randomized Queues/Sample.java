import stdlib.StdOut;

public class Sample {
    // Entry point.
    public static void main(String[] args) {
        int lo = Integer.parseInt(args[0]);  // lo(int)
        int hi = Integer.parseInt(args[1]);  // hi(int)
        int k = Integer.parseInt(args[2]);  // k(int)
        String mode = args[3];  // mode(string)
        // Create a random queue q containing integers from the interval [lo, hi].
        ResizingArrayRandomQueue<Integer> q = new ResizingArrayRandomQueue<>();
        for (int i = lo; i <= hi; i++) {
            q.enqueue(i);
        }
        if (mode.equals("+")) {  // If mode is “+”
            for (int i = 0; i < k; i++) {  //  sample and write k from q
                StdOut.println(q.sample());
            }
        } else if (mode.equals("-")) {  //  If mode is “-”
            for (int i = 0; i < k; i++) {  //  dequeue and write k from q
                StdOut.println(q.dequeue());
            }
        } else {
            throw new IllegalArgumentException("Illegal mode");  // illegal mode
        }
    }
}