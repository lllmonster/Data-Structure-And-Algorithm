class Solution {
    /**
    DP: The point is at least one character, at most two characters.
     */
    public int numDecodings(String s) {
        if (s.length() == 0) return 0;
        int[] dp = new int[s.length() + 1];
        dp[0] = 1;
        dp[1] = s.charAt(0) == '0' ? 0 : 1;
        for (int i = 2; i <= s.length(); i++) {
            int single = Integer.valueOf(s.substring(i-1, i));
            int doub = Integer.valueOf(s.substring(i-2, i));
            if (single > 0) {
                dp[i] += dp[i-1];
            }
            if (doub >= 10 && doub <= 26) {
                dp[i] += dp[i-2];
            }
        }
        return dp[s.length()];
    }
}