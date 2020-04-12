class Solution {
    public boolean isMatch(String s, String p) {
        if (s == null || p == null) return false;
        boolean[][] dp = new boolean[s.length()+1][p.length()+1];
        dp[0][0] = true;
        // Initialize dp
        for (int j = 0; j < p.length(); j++) {
            if (p.charAt(j) == '*' && dp[0][j-1]) {
                dp[0][j+1] = true;
            }
        }
        
        // s[i] == p[j], dp[i][j] = dp[i-1][j-1]
        // p[j] == '.',  dp[i][j] = dp[i-1][j-1]
        // p[j] == '*', 
        //          remove p a*, dp[i][j] = dp[i][j-2]
        //          multiple or single, dp[i][j] = dp[i-1][j]
        
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < p.length(); j++) {
                if (s.charAt(i) == p.charAt(j) || p.charAt(j) =='.') {
                    dp[i+1][j+1]=dp[i][j];
                } else if (p.charAt(j) == '*') {
                    dp[i+1][j+1] = dp[i+1][j-1];
                    if (s.charAt(i) == p.charAt(j-1) || p.charAt(j-1) == '.')
                    dp[i+1][j+1] = dp[i+1][j+1] || dp[i][j+1];
                }
            }
        }
        return dp[s.length()][p.length()];
    }
}