class Solution {
    /**
    Two Pointer: start for 0, end for 2
     */
    public void sortColors(int[] nums) {
        int start = 0;
        int end = nums.length - 1;
        for (int i = 0; i <= end; i++) {
            if (nums[i] == 0) {
                nums[i] = nums[start];
                nums[start] = 0;
                start++;
            } else if (nums[i] == 2) {
                nums[i] = nums[end];
                nums[end] = 2;
                end--;
                i--;
            }
        }
    }
}