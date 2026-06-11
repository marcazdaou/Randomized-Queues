# Randomized Queues & Deques

Java implementations of two fundamental data structures: a **Deque** (double-ended queue) and a **Randomized Queue** — both with constant amortized time for all operations.

## Data Structures

### Deque
A generalization of a stack and queue that supports insertion and removal at **both ends**.

```
addFirst()  →  [ A | B | C | D ]  →  removeLast()
addLast()   ←  [ A | B | C | D ]  ←  removeFirst()
```

| Operation | Time Complexity |
|---|---|
| `addFirst` | O(1) amortized |
| `addLast` | O(1) amortized |
| `removeFirst` | O(1) amortized |
| `removeLast` | O(1) amortized |
| `size` | O(1) |

### Randomized Queue
Like a queue, but `dequeue()` returns a **uniformly random** item — equivalent to sampling without replacement.

| Operation | Time Complexity |
|---|---|
| `enqueue` | O(1) amortized |
| `dequeue` | O(1) amortized |
| `sample` | O(1) |
| `size` | O(1) |

## Implementation Details

- **Deque** — implemented with a doubly linked list for O(1) operations at both ends
- **Randomized Queue** — implemented with a resizing array; random access enables O(1) uniform sampling via Fisher-Yates shuffle

## Application: Permutation

```bash
# Print k uniformly random items from stdin (no repeats)
java Permutation k < input.txt
```

```bash
echo "A B C D E F G H I" | java Permutation 3
→ C
→ G
→ A
```

## Technologies

- **Java**
- Doubly linked list (Deque)
- Resizing array with Fisher-Yates sampling (RandomizedQueue)
- Generic types with `Iterator` support

## Build & Run

```bash
javac Deque.java RandomizedQueue.java Permutation.java
java Permutation 3 < words.txt
```