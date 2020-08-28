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
```

## Deque
The Deque is a subtype of the queue interface, which is related to the `double-ended queue`.
It can either be used as a `queue(first in first out/FIFO)` or as a `stack(last in first out/LIFO)`.
```Java
Deque<Obj> deque = new ArrayDeque<Obj>();
eque<String> deque = new LinkedList<String>(); 

// Add at the last 
deque.add("Element 1 (Tail)"); 

// Add at the first 
deque.addFirst("Element 2 (Head)"); 

// Add at the last 
deque.addLast("Element 3 (Tail)"); 

// Add at the first 
deque.push("Element 4 (Head)"); 

// Add at the last 
deque.offer("Element 5 (Tail)"); 

// Add at the first 
deque.offerFirst("Element 6 (Head)"); 

// We can remove the first element 
// or the last element. 
deque.removeFirst(); 
deque.removeLast(); 
```
