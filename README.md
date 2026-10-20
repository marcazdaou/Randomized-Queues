# Deques and Randomized Queues

Two generic collections in Java, written for a data structures course, plus two client programs that use them.

## Data structures (`Randomized Queues/`)

### `LinkedDeque<Item>`: double-ended queue
A doubly linked list supporting insertion, removal and peeking at **both ends** in constant time.

| Operation | Time |
|---|---|
| `addFirst`, `addLast` | O(1) |
| `removeFirst`, `removeLast` | O(1) |
| `peekFirst`, `peekLast`, `size`, `isEmpty` | O(1) |

### `ResizingArrayRandomQueue<Item>`: randomized queue
A resizing array where `dequeue()` removes a **uniformly random** item and `sample()` returns one without
removing it. Each iterator returns the items in an independent random order (it copies and shuffles them).

| Operation | Time |
|---|---|
| `enqueue` | O(1) amortized (array doubles when full) |
| `dequeue` | O(1) amortized (random item swapped with the last; array halves when ¼ full) |
| `sample`, `size`, `isEmpty` | O(1) |

## Client programs

| File | What it does |
|---|---|
| `Sample.java` | `java Sample lo hi k mode`: puts the integers lo…hi in a randomized queue and prints `k` of them, with replacement (`+`, via `sample`) or without (`-`, via `dequeue`) |
| `Sort.java` | Reads words from standard input and prints them in sorted order using a deque (and a stack for insertions in the middle) |

## Run

Uses the course libraries (`stdlib`, `dsa`).

```bash
javac LinkedDeque.java ResizingArrayRandomQueue.java Sample.java Sort.java
java Sample 1 10 3 -            # 3 distinct random integers from 1..10
java Sort < words.txt           # words in sorted order
```

## Concepts

Linked lists · resizing arrays and amortized analysis · uniform random sampling · generics and iterators
