class Solution {
    /**
    Method 1: Dynamic Programming O(n), O(n)
    Method 2: Fibnoacci Number, O(n), O(1)
    */
    public int climbStairs(int n) {
        // return dp(n);
        return fibnoacci(n);
    }
    
    private int dp(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n];
    }
    
    private int fibnoacci(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        int first = 1, second = 1;
        int third = 0;
        for (int i = 2; i <= n; i++) {
            third = first + second;
            first = second;
            second = third;
        }
        return third;
    }
}