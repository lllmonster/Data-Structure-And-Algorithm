class Solution {
    /**
    Method 1: backtrack
    Method 2: backtrack + DP (TODO)
     */
    public List<List<String>> partition(String s) {
        List<List<String>> ans = new ArrayList<>();
        backtrack(s, ans, new ArrayList(), 0);
        return ans;
    }
    
    private void backtrack(String s, List<List<String>> ans, List<String> sub, int start) {
        if (start == s.length()) {
            ans.add(new ArrayList(sub));
            return;
        }
        for (int i = start; i < s.length(); i++) {
            String str = s.substring(start, i+1);
            if (isPal(str)) {
                sub.add(s.substring(start, i+1));
                backtrack(s, ans, sub, i+1);
                sub.remove(sub.size()-1);
            }
        }
    }
    
    private boolean isPal(String s) {
        int l = 0, r = s.length()-1;
        while (l < r) {
            if (s.charAt(l) != s.charAt(r))
                return false;
            l++;r--;
        }
        return true;
    }
}