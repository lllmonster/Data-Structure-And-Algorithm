class Solution {
    /**
    DP: The basic idea is to find which balloon is the last one to burst.
        c[i][j] = maxCoins(nums[i] -> nums[j])
        ans = c[1][n]
        c[i][j] = max(c[i][k-1] + nums[i-1]*nums[k]*nums[j+1] + c[k+1][j])

        Interesting...
     */
    public int maxCoins(int[] nums) {
        // Build a new array to add the first and last number 1.        
        int n = nums.length;
        int[] copys = new int[n+2];
        copys[0] = 1;
        copys[n+1] = 1;
        for (int i = 1; i <= n; i++) {
            copys[i] = nums[i-1];
        }
        
        // Start DP
        int[][] dp = new int[n+2][n+2];
        for (int l = 1; l <= n; l++) {
            for (int i = 1; i <= n - l + 1; i++) {
                int j = i + l - 1;
                for (int k = i; k <= j; k++) {
                    dp[i][j] = Math.max(dp[i][j], 
                                       dp[i][k-1] + dp[k+1][j] + copys[i-1]*copys[k]*copys[j+1]);
                }
            }
        }
        return dp[1][n];
    }
}