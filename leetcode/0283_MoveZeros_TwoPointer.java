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