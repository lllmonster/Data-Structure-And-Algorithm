# Two Pointer Technique
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
