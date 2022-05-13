<!-- TOC -->

- [Queue](#queue)
- [Priority Queue](#priority-queue)
- [Deque](#deque)

<!-- /TOC -->
## Queue
```
Queue<int> q = new LinkedList<>();
q.offer();
q.poll();
q.peek();
```

## Priority Queue
```
PriorityQueue<int> pq = new PriorityQueue<int>(new Comparator<int>() {
    public int compare(int a, int b) {
        return a - b;
    }
});

pq.add(i);
pq.poll();
```
**CompareMethod**
```
double[][] arr = new double[2][2];
Arrays.sort(arr, (a, b) -> Double.compare(a[0], b[0]));
```

## Deque
The Deque is a subtype of the queue interface, which is related to the `double-ended queue`.
It can either be used as a `queue(first in first out/FIFO)` or as a `stack(last in first out/LIFO)`.
```Java
Deque<Obj> deque = new ArrayDeque<Obj>();
Deque<String> deque = new LinkedList<String>(); 

// Add at the first 
deque.addFirst("Element 2 (Head)"); 

// Add at the last 
deque.addLast("Element 3 (Tail)"); 

// We can remove the first element 
// or the last element. 
deque.removeFirst(); 
deque.removeLast(); 
```
