class Solution {
    /**
    Method 1: Selection algorithm (partition add quick sort), O(n)
    Method 2: normal Sort. O(nlogn)
    */
    public int findKthLargest(int[] nums, int k) {
        return select(nums, k);
        //return normalSort(nums, k);
    }
    
    private int select(int[] nums, int k) {
        k = nums.length - k;
        int lo = 0, hi = nums.length -1;
        while (lo < hi) {
            int pivot = partition(nums, lo, hi);
            if (pivot < k) {
                lo = pivot + 1;
            } else if (pivot > k) {
                hi = pivot - 1;
            } else {
                break;
            }
        }
        return nums[k];
    }
    
    private int partition(int[] nums, int lo, int hi) {
        int i = lo, j = hi+1;
        while (true) {
            while(i < hi && nums[++i] < nums[lo]);
            while(j > lo && nums[lo] < nums[--j]);
            if (i >= j) break;
            swap(nums, i, j);
        }
        swap(nums, lo, j);
        return j;
    }
    
    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
    
    private int normalSort(int[] nums, int k) {
        final int N = nums.length;
        Arrays.sort(nums);
        return nums[N - k];
    }
}