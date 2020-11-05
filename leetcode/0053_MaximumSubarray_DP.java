class Solution {
    /** DP solution, slider the window
    * one is O(n) space, the other one o(1) space
    */
    public int maxSubArray(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int max = dp[0];
        for (int i = 1; i < nums.length; i++) {
            if (dp[i-1] < 0) {
                dp[i] = nums[i];
            } else {
                dp[i] = nums[i] + dp[i-1];
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    private int SecondMethod(int[] nums) {
        int res = Integer.MIN_VALUE;
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum = Math.max(sum, 0) + nums[i];
            res = Math.max(res, sum);
        }
        return res;
    }
}