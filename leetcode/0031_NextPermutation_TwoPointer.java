class Solution {
    /**
    Algorithm is the point
    1. Find the first decending number i from the end
    2. Find the number j just larger than i
    3. swap(i.j)
    4. reverse the other elements.(i+1, nums.length-1)
    
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