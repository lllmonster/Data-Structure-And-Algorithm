class Solution {
    /**
    DP: Two array, buy[] and sell[]
        How to define strategy:
            buy[i] = Math.max(buy[i - 1], sell[i - 2] - prices[i]);   
            sell[i] = Math.max(sell[i - 1], buy[i - 1] + prices[i]);
        How to initialize:
            buy[0] = -prices[0];
            buy[1] = Math.max(-prices[0], -prices[1]);
            sell[0] = 0;
            sell[1] = Math.max(0, buy[0] + prices[1]);
        How to optimize:
            Change array to constant. dp() -> dpconst()
     */
    public int maxProfit(int[] prices) {
        //return dp(prices);
        return dpconst(prices);
    }
    
    private int dp(int[] prices) {
        if (prices.length == 0 || prices.length == 1) return 0;
        int n = prices.length;
        int[] sell = new int[n];
        int[] buy = new int[n];
        buy[0] = -prices[0];
        buy[1] = Math.max(-prices[0], -prices[1]);
        sell[0] = 0;
        sell[1] = Math.max(0, buy[0] + prices[1]);
        for (int i = 2; i < prices.length; i++) {
            buy[i] = Math.max(buy[i-1], sell[i-2] - prices[i]);
            sell[i] = Math.max(sell[i-1], buy[i-1] + prices[i]);
        }
        return Math.max(buy[n-1], sell[n-1]);
    }
    
    private int dpconst(int[] prices) {
        if (prices.length == 0 || prices.length == 1) return 0;
        int b0 = -prices[0];
        int b1 = Math.max(b0, -prices[1]);
        int s0 = 0;
        int s1 = Math.max(0, b1 + prices[1]);
        for (int i = 2; i < prices.length; i++) {
            int b2 = Math.max(b1, s0 - prices[i]);
            int s2 = Math.max(s1, b1 + prices[i]);
            b1 = b2;
            s0 = s1; s1 = s2;
        }
        return s1;        
    }
}