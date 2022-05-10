class Solution {
    public void moveZeroes(int[] nums) {
        int i = 0, j = 0;
        while (j < nums.length) {
            if (nums[j] == 0) {
                j++;
            } else {
                int tmp = nums[i];
                nums[i] = nums[j];
                nums[j] = tmp;
                j++;
                i++;
            }
        }
    }
}

/**
More clear - two pointer o(1) o(n)*/
class Solution {
    public void moveZeroes(int[] nums) {
        int l = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                swap(nums, i, l);
                l++;
            }
        }
    }
    
    private void swap(int[] nums, int l, int r) {
        int tmp = nums[l];
        nums[l] = nums[r];
        nums[r] = tmp;
    }
}