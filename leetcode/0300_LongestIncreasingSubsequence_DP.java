class Solution {
    /**
    Method 1: DP, o(n^2), o(n)
    Method 2: BinarySearch, o(nlogn), o(n). (Nice one)
     */
    public int lengthOfLIS(int[] nums) {
        //return dp(nums);
        return binarySearch(nums);
    }
    
    private int dp(int[] nums) {
        if (nums.length == 0) return 0;
        int max = 1;
        int[] dp = new int[nums.length];
        dp[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            int tmp = 0;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    tmp = Math.max(tmp, dp[j]);
                }
            }
            dp[i] = tmp + 1;
            max = Math.max(max, dp[i]);
        }
        return max;
    }
    
    private int binarySearch(int[] nums) {
        if (nums.length == 0) return 0;
        int[] dp = new int[nums.length];
        int max = 0;
        for (int num : nums) {
            int i = 0, j = max;
            // binary search
            while (i < j) {
                int m = (i+j)/2;
                if (dp[m] < num) {
                    i = m + 1;
                } else {
                    j = m;
                }
            }
            dp[i] = num;
            if (i == max) max++;
        }
        return max;
    }
}