class Solution {
    /**
    Tricky method
     */
    public List<String> removeInvalidParentheses(String s) {
        int l = 0, r = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                l++;
            } else if (c == ')') {
                if (l != 0)
                    l--;
                else 
                    r++;
            }
        }
        Set<String> ans = new HashSet<>();
        dfs(s, l, r, ans, new StringBuilder(), 0, 0);
        return new ArrayList<String>(ans);
    }
    
    private void dfs(String s, int l, int r, Set<String> ans, StringBuilder sb, int start, int open) {
        if (l < 0 || r < 0 || open < 0)
            return;
        if (start == s.length()) {
            if (l == 0 && r == 0 && open == 0) {
                ans.add(sb.toString());
            }
            return;
        }
        char c = s.charAt(start);
        int len = sb.length();
        if (c == '(') {
            dfs(s, l-1, r, ans, sb, start+1, open); // not use
            dfs(s, l, r, ans, sb.append(c), start+1, open+1); // use
        } else if (c == ')') {
            dfs(s, l, r-1, ans, sb, start+1, open); // not use
            dfs(s, l, r, ans, sb.append(c), start+1, open-1); // use
        } else {
            dfs(s, l, r, ans, sb.append(c), start+1, open);
        }
        
        sb.setLength(len);
    }
}