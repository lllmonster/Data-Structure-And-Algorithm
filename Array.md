# Array
## 1D Array
An array has a `a fixed capacity`, and we need to specify the size of the array when we initialize it. Sometimes this will be inconvenient and wasteful.

In Java,
```
public class Main {
  public static void main(String[] args) {
    // 1. initialize
    int[] a0 = new int[5];
    int[] a1 = {1,2,3};
    // 2. Get Length
    System.out.println("The size of a1 is: " + a1.length);
    // 3. Access the elements
    System.out.println("The first element is: " + a1[0]);
    // 4. Iterate all elements
    System.out.print("Version1, the contents of a1 are: ");
    for (int i = 0; i < a1.length; i++) {
      System.out.print(" " + a1[i]);
    }
    System.out.println();
    System.out.print("Version2, the contents of a1 are: ");
    for(int item: a1) {
      System.out.print(" " + item);
    }
    System.out.println();
    // 5. Modify element
    a1[0] = 4;
    // 6. Sort
    Arrays.sort(a1);
  }
}
```
## Dynamic Array
An dynamic array is a random access list data structure with `variable size`. In java, we have `ArrayList`. In python, we have `list`.

```
import java.util.*;
// "static void main must be defined in a public class"
public class test {
	public static void main(String[] args) {
      // 1. initialize
      List<Integer> v0 = new ArrayList<>();
      List<Integer> v1; // v1 == null
      // 2. cast an array to a vector
      Integer[] a = {0,1,2,3,4};
      v1 = new ArrayList<>(Arrays.asList(a));
      // 3. make a copy
      List<Integer> v2 = v1; // another reference to v1
      List<integer> v3 = new ArrayList<>(v1); // make an actual copy of v1
      // 4. get length
      System.out.println("The size of v1 is: " + v1.size());
      // 5. access element
      System.out.println("The first element in v1 is: " + v1.get(0));
      // 6. iterate the vector
      System.out.print("Version1, the contents of v1 are: ");
      for (int i = 0; i < v1.size(); i++) {
        System.out.println(" " + v1.get(i));
      }
      System.out.println();
      System.out.print("Version2, the contents of v1 are: ");
      for (int item : v1) {
        System.out.println(" " + item);
      }
      System.out.println();
      // 7. modify element
      v2.set(0, 5); // modify v2 will actually modify v1
      System.out.println("The first element in v1 is: " + v1.get(0));
      v3.set(0, -1); // modify v3 will not modify v1
      System.out.println("The first element in v1 is: " + v1.get(0));
      // 8. sort
      Collections.sort(v1);
      // 9. add new element at the end of the vector
      v1.add(-1);
      v1.add(1,6);
      // 10. delete the last element
      v1.remove(v1.size() - 1);
      // 11. covert to 1d Array
      List<String> ans = new ArrayList<>();
      String[] res = new String[ans.size()];
      return ans.toArray(res);
	}
}
```
## 2D Array
In java, the 2D array is actually a 1D array which contains M elements, each of which is an array of N integers.

Also, we can define a 2D dynamic array. It can be just a nested dynamic array.
```
public class test {
	public static void main(String[] args) {
		System.out.println("Example 1");
		int[][] a = new int[2][5];
		printArray(a);
		System.out.println("Example 2");
		int[][] b = new int[2][];
		printArray(b);
		System.out.println("Example 3");
		b[0] = new int[3];
		b[1] = new int[5];
		printArray(b);
	}
}
```

### Note:
The difference between `i++` and `++i` in Java:
`i++ = ++i => i = i + 1`, but not exactly same
```
int i = 3;
int a = i++; // a = 3, i = 4
int b = ++a; // b = 4, a = 4
```

In Python,
```
lst = [] # declare an empty list
lst = [1,2,3]
lst.append('a') # add items

for item in lst: # iterate over elements of the list
  # do something with item

for idx, item in enumerate(lst): # keep track of the current index
  # idx is the current index, while item is lst[idx]

lst.remove(x) # remove the first occurence of x in the lst

# Note, we cannot iterate over the list and modify it at the same time;
# to do that, you should instead iterate over a slice of the list
# (which is basically a copy of the list). As in:

for item in lst[:]: # Notice the [:] which makes a slice
       # Now we can modify lst, since we are iterating over a copy of it
```
## Two Pointer Technique -- Array
Start with a classic problem.  

**Reverse the elements in an array**  
The idea is to swap the first element with the end, advance to the next element and swapping repeatedly until it reaches the middle position.
```
public static void reverse(int[] v, int N) {
  int i = 0;
  int j = N - 1;
  while(i < j) {
    swap(v, i, j); // self-defined function
    i++;
    j--;
  }
}
```
**Summary**  
To summarize, one of typical scenarios to use two-pointer technique is that you want to `Iterate the array from two ends to the middle`.  
And it is worth noting that this technique is often used in an `sorted` array.

Sometimes, we can use `two pointers with different steps` to solve problems. Let's start with another classic problem.

**Given an array and a value, remove all instances of that value in-place and return the new length**  
If we don't have the limitation of space complexity, it will be easier. We can initialize a new array to store the answer. Actually, it is equivalent to using two pointers, one is used for the iteration of original array and another one always points at the last position of the new array.  
If we consider the space limitation, we can still use two pointers: `one is still used for the iteration while the seconde one always points at the position for next addition`.
```
public int removeElement(int[] nums, int val) {
  int k = 0;
  for (int i = 0; i < nums.length; i++) {
    if (nums[i] != val) {
      nums[k] = nums[i];
      k++;
    }
  }
  return k;
}
```
**Summary**  
To summarize, this is very common scenario of using the two-pointer technique when you need `one slow-runner and one fast-runner at the same time`, but you need to determine the movement strategy for both pointers at first.  
And it is worth noting that you might sometimes need to `sort` the array and a `greedy` thought to determine your strategy.


## Conclusion

There are more array-related data structures we need to learn.

1. Other data structures  
  - String
  - Hash table
  - Linked List
  - Queue
  - Stack

2. Understand the principle of some widely-used sorting algorithms and their complexity.

3. Binary search is used to search a specific element in a sorted array.

4. Two pointer also can be used in,
  - Slow-pointer and faster-pointer problem in Linked List
  - Sliding Window Problem

5. Two pointers technique sometimes will relate to Greedy Algorithm which helps us to design our pointers' movement strategy.
