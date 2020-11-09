class Solution {
    /**
    Method 1: DP
                dp[n] = Math.min{ dp[n - i*i] + 1 },  n - i*i >=0 && i >= 1
    Method 2: Mathematical
                Only four result, 1 or 2 or 3 or 4
                If n is a perfect square, return 1.
                The result is 4 if and only if n can be written in the form of 4^k*(8*m + 7). 
                Please refer to Legendre's three-square theorem.
                The result is 2 if and only if n is the sum of two perfect squares.
                The other case is 3.
    */
    public int numSquares(int n) {
        return dp(n);
        //return mathematical(n);
    }
    
    private int dp(int n) {
        if (n == 0) return 0;
        int[] dp = new int[n+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j*j <= i; j++) {
                dp[i] = Math.min(dp[i], dp[i-j*j] + 1);
            }
        }
        return dp[n];
    }
    
    private int mathematical(int n) {
        int number = n;
        while(number % 4 == 0)
            number /= 4;
        if(number % 8 == 7) return 4;
        if(isSquat(n)) return 1;
        for(int i = 1; i * i < n; i++){
            if(isSquat(n - i * i)) return 2;
        }
        return 3;
    }
    
    public boolean isSquat(int i){
        int j = (int)Math.sqrt(i);
        return i == j * j;
    }
        
}