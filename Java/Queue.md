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
