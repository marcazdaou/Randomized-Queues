import java.util.Iterator;
import java.util.NoSuchElementException;

import stdlib.StdOut;
import stdlib.StdRandom;

// A data type to represent a random queue, implemented using a resizing array as the underlying
// data structure.
public class ResizingArrayRandomQueue<Item> implements Iterable<Item> {
    private Item[] q;  //  Array to store the items of queue
    private int n;  // Size of the queue

    // Constructs an empty random queue.
    public ResizingArrayRandomQueue() {
        q = (Item[]) new Object[2];  // create q with an initial capacity of 2.
        n = 0;  // size 0
    }

    // Returns true if this queue is empty, and false otherwise.
    public boolean isEmpty() {
        return n == 0;  // boolean
    }

    // Returns the number of items in this queue.
    public int size() {
        return n;  // return size
    }

    // Adds item to the end of this queue.
    public void enqueue(Item item) {
        if (item == null) {
            throw new NullPointerException("item is null");  // item is null
        }
        if (n == q.length) {  // If q is at full capacity
            resize(2 * q.length);  // resize it to twice its current capacity.
        }
        q[n] = item;  // Insert the given item in q at index n.
        n++;  // Increment n by one.
    }


    // Returns a random item from this queue.
    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException("Random queue is empty"); // Random queue is empty
        }
        int r = StdRandom.uniform(n); // Return q[r], random from the interval [0, n).

        return q[r];
    }

    // Removes and returns a random item from this queue.
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Random queue is empty");  // random queue is empty
        }
        int r = StdRandom.uniform(n);
        Item item = q[r];  // Save q[r] in item,
        q[r] = q[n - 1];  //  Set q[r] to q[n - 1] and q[n - 1] to null.
        q[n - 1] = null;
        n--;  // Decrement by 1
        if (n > 0 && n == q.length / 4) {  // If q is at quarter capacity, resize it to half
            resize(q.length / 2);
        }
        return item;  // return item
    }

    // Returns an independent iterator to iterate over the items in this queue in random order.
    public Iterator<Item> iterator() {
        return new RandomQueueIterator();  // Return an object of type RandomQueueIterator.
    }

    // Returns a string representation of this queue.
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Item item : this) {
            sb.append(item);
            sb.append(", ");
        }
        return n > 0 ? "[" + sb.substring(0, sb.length() - 2) + "]" : "[]";
    }

    // An iterator, doesn't implement remove() since it's optional.
    private class RandomQueueIterator implements Iterator<Item> {
        private Item[] items;  // Array to store the items of q
        private int current;  // Index of the current item in items,


        // Constructs an iterator.
        public RandomQueueIterator() {
            items = (Item[]) new Object[n];  // Create items with capacity n.
            for (int i = 0; i < n; i++) {
                items[i] = q[i];  // Copy the n items from q into items.
            }
            StdRandom.shuffle(items);  //  Shuffle items.
            current = 0;  // initialize current
        }

        // Returns true if there are more items to iterate, and false otherwise.
        public boolean hasNext() {
            return current < n;  // Return whether the iterator has more items to iterate or not
        }

        // Returns the next item.
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Iterator is empty");  // Iterator is empty
            }
            return items[current++];
            // Return the item in items at index current and advance current by one.
        }
    }
    // Resize Underlying Array
    private void resize(int max) {
        Item[] temp = (Item[]) new Object[max];
        for (int i = 0; i < n; i++) {
            if (q[i] != null) {
                temp[i] = q[i];
            }
        }
        q = temp;
    }
    // Unit test the data type
    public static void main(String[] args) {
        ResizingArrayRandomQueue<Integer> q = new ResizingArrayRandomQueue<Integer>();
        int sum = 0;
        for (int i = 0; i < 1000; i++) {
            int r = StdRandom.uniform(10000);
            q.enqueue(r);
            sum += r;
        }
        int iterSumQ = 0;
        for (int x : q) {
            iterSumQ += x;
        }
        int dequeSumQ = 0;
        while (q.size() > 0) {
            dequeSumQ += q.dequeue();
        }
        StdOut.println("sum       = " + sum);
        StdOut.println("iterSumQ  = " + iterSumQ);
        StdOut.println("dequeSumQ = " + dequeSumQ);
        StdOut.println("iterSumQ + dequeSumQ == 2 * sum? " + (iterSumQ + dequeSumQ == 2 * sum));
    }
}
