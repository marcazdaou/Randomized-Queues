import java.util.Iterator;
import java.util.NoSuchElementException;

import stdlib.StdOut;
import stdlib.StdRandom;

// A data type to represent a double-ended queue (aka deque), implemented using a doubly-linked
// list as the underlying data structure.
public class LinkedDeque<Item> implements Iterable<Item> {
    private Node first;  // front of Deque
    private Node last;  // end of Deque
    private int n;  // size of Deque

    // Constructs an empty deque.
    public LinkedDeque() {
        first = null;  // empty
        last = null;
        n = 0;  // empty
    }

    // Returns true if this deque is empty, and false otherwise.
    public boolean isEmpty() {
        return n == 0;  // Return whether the deque is empty or not.
    }

    // Returns the number of items in this deque.
    public int size() {
        return n;  // return size of Deque
    }

    // Adds item to the front of this deque.
    public void addFirst(Item item) {
        if (item == null) {
            throw new NullPointerException("item is null");  // item is null.
        }
        Node oldFirst = first;  // Add the given item to the front of the deque.
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        if (isEmpty()) {  // If this is the first item that is being added,
            last = first;
        } else {
            oldFirst.prev = first;  //  both first and last must point to the same node
        }
        n++;  // Increment n by one.
    }

    // Adds item to the back of this deque.
    public void addLast(Item item) {
        if (item == null) {
            throw new NullPointerException("item is null");  // item is null
        }
        Node oldLast = last;  // Add the given item to the back of the deque
        last = new Node();
        last.item = item;
        last.prev = oldLast;
        if (isEmpty()) {  // If this is the first item that is being added
            first = last;
        } else {
            oldLast.next = last;  //  both first and last must point to the same node
        }
        n++;  // increment n by 1
    }

    // Returns the item at the front of this deque.
    public Item peekFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("Deque is empty");  // Deque is empty
        }
        return first.item;  // return first.item
    }

    // Removes and returns the item at the front of this deque.
    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("Deque is empty");  // Deque is empty
        }
        Item item = first.item;  // Remove and return the item at the front of the deque.
        first = first.next;
        n--;  // decrement n by 1
        if (isEmpty()) {  // If this is the last item that is being removed,
            last = null;
        } else {
            first.prev = null;  // both first and last must point to null.
        }
        return item;
    }

    // Returns the item at the back of this deque.
    public Item peekLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("Deque is empty");  // Deque is empty
        }
        return last.item;  // return last.item
    }

    // Removes and returns the item at the back of this deque.
    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("Deque is empty");  // Deque is empty.
        }
        Item item = last.item;  // Remove and return the item at the back of the deque
        last = last.prev;
        n--;  // Decrement by 1
        if (isEmpty()) {  // If this is the last item that is being removed,
            first = null;
        } else {
            last.next = null;  // both first and last must point to null.
        }
        return item;
    }

    // Returns an iterator to iterate over the items in this deque from front to back.
    public Iterator<Item> iterator() {
        return new DequeIterator();  // Return an object of type DequeIterator.
    }

    // Returns a string representation of this deque.
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Item item : this) {
            sb.append(item);
            sb.append(", ");
        }
        return n > 0 ? "[" + sb.substring(0, sb.length() - 2) + "]" : "[]";
    }

    // A deque iterator.
    private class DequeIterator implements Iterator<Item> {
        private Node current;  // Node current.

        // Constructs an iterator.
        public DequeIterator() {
            current = first;  // initializing instance variable
        }

        // Returns true if there are more items to iterate, and false otherwise.
        public boolean hasNext() {
            return current != null;  // Return whether the iterator has more items to iterate or not
        }

        // Returns the next item.
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Iterator is empty");  // Iterator is empty
            }
            Item item = current.item;  // Return the item in current.
            current = current.next;  // current to the next node
            return item;
        }
    }


    // A data type to represent a doubly-linked list. Each node in the list stores a generic item
    // and references to the next and previous nodes in the list.
    private class Node {
        private Item item;  // the item
        private Node next;  // the next node
        private Node prev;  // the previous node
    }

    // Unit tests the data type. [DO NOT EDIT]
    public static void main(String[] args) {
        LinkedDeque<Character> deque = new LinkedDeque<Character>();
        String quote = "There is grandeur in this view of life, with its several powers, having " +
                "been originally breathed into a few forms or into one; and that, whilst this " +
                "planet has gone cycling on according to the fixed law of gravity, from so simple" +
                " a beginning endless forms most beautiful and most wonderful have been, and are " +
                "being, evolved. ~ Charles Darwin, The Origin of Species";
        int r = StdRandom.uniform(0, quote.length());
        StdOut.println("Filling the deque...");
        for (int i = quote.substring(0, r).length() - 1; i >= 0; i--) {
            deque.addFirst(quote.charAt(i));
        }
        for (int i = 0; i < quote.substring(r).length(); i++) {
            deque.addLast(quote.charAt(r + i));
        }
        StdOut.printf("The deque (%d characters): ", deque.size());
        for (char c : deque) {
            StdOut.print(c);
        }
        StdOut.println();
        StdOut.println("Emptying the deque...");
        double s = StdRandom.uniform();
        for (int i = 0; i < quote.length(); i++) {
            if (StdRandom.bernoulli(s)) {
                deque.removeFirst();
            } else {
                deque.removeLast();
            }
        }
        StdOut.println("deque.isEmpty()? " + deque.isEmpty());
    }
}
