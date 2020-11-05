class Solution {
    public int rob(int[] nums) {
        int[] rob = new int[nums.length+1];
        int[] norob = new int[nums.length+1];
        rob[0] = 0;
        norob[0] = 0;
        for (int i = 1; i <= nums.length; i++) {
            rob[i] = norob[i-1] + nums[i-1];
            norob[i] = Math.max(rob[i-1], norob[i-1]);
        }
        return Math.max(rob[nums.length], norob[nums.length]);
    }
}