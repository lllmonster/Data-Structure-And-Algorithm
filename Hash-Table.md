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

## HashSet Operation
```
public static void main(String[] args) {
  Set<Integer> hashSet = new HashSet<>(); // initialize the hash set
  hashSet.add(3); // add a new key
  hashSet.remove(3); // remove the key
  if (!hashSet.contains(2)) {
    // check if the key is in the hash set
  }
  hashSet.size();
  // iterate the hash set
  for (Integer i : hashSet) {}
  hashSet.clear(); // clear the hash set
  hashSet.isEmpty();
}
```
Typically, a hash set is used to `check if a value has ever appeared or not`. Let's look at an example:  
**Given an array of Integers, find if the array contains any duplicates.**
```
public boolean findDuplicates(List<Type>& keys) {
  Set<Type> hashset = new HashSet<>();
  for (Type key : keys) {
    if (hashset.contains(key)) return true;
    hashset.add(key);
  }
  return false;
}
```
## HashMap Operation
```
public static void main(String[] args) {
  // 1. initialize the hash map
  Map<Integer, Integer> hashmap = new HashMap<>();
  // 2. insert a new (key, value) pair
  hashmap.putIfAbsent(0,0);
  // 3. insert a new (key, value) pair or update the value of existed key
  hashmap.put(1,1);
  // 4. get the value of specific key
  hashmap.get(1);
  // 5. delete a key
  hashmap.remove(2);
  // 6. check if a key/value is in the hash map
  hashmap.containsKey(2);
  hashmap.containsValue(2);
  // 7. get the size of the hash map
  hashmap.size();
  // 8. iterate the hash map
  for (Map.Entry<Integer, Integer> entry : hashmap.entrySet()) {
    entry.getKey();
    entry.getValue();
  }
  for (String key : map.keySet()) {}
  for (String value : map.values()) {}
  // 9. clear the hash map
  hashmap.clear();
  // 10. check if the hash map is empty
  hashmap.isEmpty();
}
```
Let's look at an example:  
**Given an array of integers, return indices of the two numbers such that they add up to a specific target.**  
In this example, if we only want to return true if there is a solution, we can use a hash set to store all the values when we iterate the array and check if `target - current_value` is in the hash set or not.  
However, we are asked to `return more information` which means we not only care about the value but also care about the index. We need to store not only the number as the key but also the index as the value. Therefore, we should use a hash map rather than a hash set.
```
ReturnType aggregateByKey_hashmap(List<Type>& keys) {
  Map<Type, InfoType> hashmap = new HashMap<>();
  for (Type key : keys) {
    if (hashmap.containsKey(key)) {
      if (hashmap.get(key) satisfies the requirement) {
        return needed_information;
      }
    }
    hashmap.put(key, value);
  }
  return needed_information;
}
```
Another frequent scenario is to `aggregate all the information by key`. Here's an example:  
**Given a string, find the first non-repeating character in it and return its index. If it doesn't exist, return -1.**  
A simple way is to `count the occurrence` of each character first. And then go through the results to find out the first unique character.  
Therefore, we can maintain a hashmap whose key is the character while the value is a counter for the corresponding character. Each time when we iterate a character, we just add the corresponding value by 1.  
The key to solving this kind of problem is to `decide your strategy when you encounter an existing key`.
```
ReturnType aggregateByKey_hashmap(List<Type>& keys) {
  Map<Type, InfoType> hashmap = new HashMap<>();
  for (Type key : keys) {
    if (hashmap.containsKey(key)) {
      hashmap.put(key, updated_information);
    }
    hashmap.put(key, value);
  }
  return needed_information;
}
```
## Design the key
Sometimes we have to think it over to `design a suitable key` when using a hash table. Let's look at an example:  
**Given an array of strings, group anagrams together.**  
When you design a key, you need to guarantee that:
1. All values belong to the same group will be mapped in the same group.  
2. Values which needed to be separated into different groups will not be mapped into the same group.  

In the example above, our mapping strategy can be 'sort the string and use the sorted string as the key'.  
The mapping strategy can be really `tricky` sometimes.  
#### Summary
- When the order of each element in the string/array doesn't matter, you can use the `sorted string/array` as the key.  
- If you only care about the offset of each value, usually the offset from the first value, you can use the `offset` as the key.  
- In a tree, you might want to know directly use the `TreeNode` as key sometimes. But in most cases, the `serialization of the subtree` might be a better idea.
<img src="./image/hashkey3.png" alt="drawing" height="200px">
- In a matrix, you might want to use the `row index` and `column index` as key.  
- In a Sudoku, you can combine the row index and the column index to identify which `block` this element belongs to.  
<img src="./image/hashkey5.png" alt="drawing" height="200px">  
- Sometimes, in a matrix, you might want to aggregate the values in the same `diagonal line`.  
<img src="./image/hashkey6.png" alt="drawing" height="200px">  
