# Binary Search
## Identification and Template Introduction
*How do we identify Binary Search?*  
Binary Search should be considered every time you need to search for an index or element in a collection. If the collection is unsorted, we can always sort it first before applying Binary Search.

*3 Parts of a Successful Binary Search*  
1. Pre-processing - Sort if collection is unsorted  
2. Binary Search - Using a loop or recursion to divide search space in half after each comparison.  
3. Post-processing - Determine viable candidates in the remaining space.

## Binary Search Template I  
```
int binarySearch(int[] nums, int target){
  if(nums == null || nums.length == 0)
    return -1;

  int left = 0, right = nums.length - 1;
  while(left <= right){
    // Prevent (left + right) overflow
    int mid = left + (right - left) / 2;
    if(nums[mid] == target){ return mid; }
    else if(nums[mid] < target) { left = mid + 1; }
    else { right = mid - 1; }
  }

  // End Condition: left > right
  return -1;
}
```
Template I is used to search for an element or condition which can be determined by accessing `a single index` in the array.

## Binary Search Template II
```
int binarySearch(int[] nums, int target){
  if(nums == null || nums.length == 0)
    return -1;

  int left = 0, right = nums.length - 1;
  while(left < right){
    // Prevent (left + right) overflow
    int mid = left + (right - left) / 2;
    if(nums[mid] == target){ return mid; }
    else if(nums[mid] < target) { left = mid + 1; }
    else { right = mid; }
  }

  // Post-processing
  // End Condition: left == right
  if(left != nums.length && nums[left] == target) return left;
  return -1;
```
Template II is an advanced form of Binary Search. It is used to search for an element or condition which requires accessing `the current index and its immediate right neighbor's index` in the array.  

## Binary Search Template III
```
int binarySearch(int[] nums, int target){
  if(nums == null || nums.length == 0)
    return -1;

  int left = 0, right = nums.length - 1;
  while(left + 1 < right){
    // Prevent (left + right) overflow
    int mid = left + (right - left) / 2;
    if(nums[mid] == target){ return mid; }
    else if(nums[mid] < target) { left = mid; }
    else { right = mid; }
  }

  // Post-processing
  // End Condition: left + 1 == right
  if(nums[left] == target) return left;
  if(nums[right] == target) return right;
  return -1;
```
Template III is another unique form of Binary Search. It is used to search for an element or condition which requires `accessing the current index and its immediate left and right neighbor's index` in the array.
