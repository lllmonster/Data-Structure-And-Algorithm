class Solution {
    /**
    1. Find the first decending number from the end '4'
    2. Find the number just larger than '4'
    3. swap
    4. reverse the other elements.
    
    O(n), O(1)
    */
    public void nextPermutation(int[] nums) {
        if (nums.length == 1) return;
        
        int i = nums.length - 2;
        while (i >= 0 && nums[i] >= nums[i+1]) i--;
        if (i >= 0) {
            int j = nums.length -1;
            while (nums[i] >= nums[j]) j--;
            swap(nums, i, j);
        }
        reverse(nums, i+1, nums.length-1);
        
    }
    
    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
    
    private void reverse(int[] nums, int l, int r) {
        while (l < r) {
            swap(nums, l, r);
            l++;r--;
        }
    }
}