class Solution {
    public boolean isMonotonic(int[] nums) {
        boolean increase = true;
        boolean decrease = true;
        for (int i = 0; i < nums.length-1; i++) {
            if (nums[i+1] > nums[i]) {
                decrease = false;
            }
            if (nums[i+1] < nums[i]) {
                increase = false;
            }
        }
        return increase || decrease;
    }
}