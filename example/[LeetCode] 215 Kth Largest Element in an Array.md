Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.  

## Three Method  
* Sort, O(nlogn), O(1)
* Priority queue, O(klogk), o(k)
* Quick sort, O(n), O(1)

## Sort
```
public int findKthLargest(int[] nums, int k) {
        Arrays.sort(nums);
        for (int i = nums.length-1; i >= 0; i--) {
            k--;
            if (k == 0) return nums[i];
        }
}
```

## Priority Queue
```
public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> q = new PriorityQueue<>();
        for (int i : nums) {
            q.offer(i);
            if (q.size() > k)
                q.poll();
        }
        return q.peek();
}
```

## Selection Algorithm
```
class Solution {
    public int findKthLargest(int[] nums, int k) {
        int lo = 0, hi = nums.length-1;
        while (lo <= hi) {
            int pointer = select(nums, lo, hi);
            if (pointer == k-1) return nums[pointer];
            else if (pointer < k-1) lo = pointer+1;
            else hi = pointer-1;
        }
        return -1;
    }

    public int select(int[] nums, int start, int end) {
        int pivot = nums[start];
        int l = start + 1;
        int r = end;
        while (l <= r) {
            if (nums[l] <= pivot && nums[r] >= pivot) {
                int tmp = nums[l];nums[l]=nums[r];nums[r]=tmp;
                l++;r--;
            } else if (nums[l] > pivot) {
                l++;
            } else if (nums[r] < pivot) {
                r--;
            }
        }
        nums[start] = nums[r];
        nums[r] = pivot;
        return r;
    }
}
```
