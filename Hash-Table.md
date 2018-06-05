## Introduction
`Hash Table` is a data structure which organizes data using  `hash function` in order to support quick insertion and search.  
There are two kinds of hash tables:
- `Hash Set`, a set data structure to store `no repeated values`.  
- `Hash Map`, a map data structure to store `(key, value)` pairs.

## Keys to Design a Hash Table
### Hash function
The hash function is the most important component of a hash table which is used to map the key to a specific bucket.  
The hash function will depend on `the range of values` and `the number of buckets`.  
How to design a hash function? The idea is to try to assign the key to the bucket as `uniform as you can`. Ideally, a perfect hash function will be a one-by-one mapping between the key and the bucket. However, in most cases, a hash function is not perfect and it is a tradeoff between `the amount of buckets` and `the capacity of a bucket`.
### Collision Resolution
In most cases, collisions are almost inevitable.  
Assume that the bucket, which holds the maximum number of keys, has N keys.  
Typically, if N is constant and small, we can simply use an `array` to store the keys in the same bucket. If N is variable and large, we might need to use `height balanced binary search tree` instead.

## HashSet Implementation
The most important in HashSet is no repeated value. Here implement it using Object and HashMap.
```
class MyHashSet {
    HashMap map;
    private static final Object PRESENT = new Object();
    /** Initialize your data structure here. */
    public MyHashSet() {
        map = new HashMap<>();
    }

    public void add(int key) {
        map.put(key,PRESENT);
    }

    public void remove(int key) {
        map.remove(key);
    }

    /** Returns true if this set did not already contain the specified element */
    public boolean contains(int key) {
        return map.containsKey(key);
    }
}
```
## HashMap Implementation
[Reference](https://www.journaldev.com/146/hashmap-implementation-with-list-in-java) , Input use object instead of Interger
```
class MyHashMap {

    class Container {
        int key;
        int value;
        public void insert(int k, int v) {
            this.key = k;
            this.value = v;
        }
    }

    private Container c;
    private List<Container> recordList;

    /** Initialize your data structure here. */
    public MyHashMap() {
        this.recordList = new ArrayList<Container>();
    }

    /** value will always be positive. */
    public void put(int key, int value) {
        this.c = new Container();
        c.insert(key, value);
        // check for the same key before adding
        for (int i = 0; i < recordList.size(); i++) {
            Container c1 = recordList.get(i);
            if(c1.key == key) {
                remove(key);
                break;
            }
        }
        recordList.add(c);
    }

    /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
    public int get(int key) {
        for (int i = 0; i < recordList.size(); i++) {
            Container c1 = recordList.get(i);
            if(key == c1.key) {
                return c1.value;
            }
        }
        return -1;
    }

    /** Removes the mapping of the specified value key if this map contains a mapping for the key */
    public void remove(int key) {
        for (int i = 0; i < recordList.size(); i++) {
            Container c1 = recordList.get(i);
            if(key == c1.key) {
                recordList.remove(i);
                break;
            }
        }
    }
}
```
## Complexity Analysis
If there are M keys in total, we can achieve the space complexity O(M) when using hash table.  
If we use `an array` in each bucket to store values in the same bucket. Ideally, the bucket size is small enough to be regarded as a constant. The time complexity of both insertion and search will be O(1).  
But in the worst case, the maximum bucket size will be N. And the time complexity will be O(1) for insertion but O(N) for search.  

**The principle of Built-in Hash Table**  
The typical design of built-in hash table is:  
1. The key value can be any `hashable` type. A value will have a `hashcode`. This code will be used in the mapping function to get the bucket box.  
2. Each bucket contains `an array` to store all the values in the same bucket initially.
3. If there are too many values in the same bucket, these values will be maintained in a `height-balanced binary search tree` instead.   

The average time complexity of insertion and search is still O(1).  
And the time complexity in the worst case is O(logN) for insertion and search by using height-balanced BST. It is a trade-off between insertion and search.
