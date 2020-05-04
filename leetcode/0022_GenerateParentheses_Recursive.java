class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        generate("", 0, 0, n, ans);
        return ans;
    }
    
    private void generate(String prefix, int l, int r, int n, List<String> ans) {
        if (r > l) return;
        if (l == n && r == n) {
            ans.add(prefix);
            return;
        }
        if (l < n) {
            generate(prefix+"(", l+1, r, n, ans);
        }
        if (r < n) {
            generate(prefix+")", l, r+1, n, ans);
        }
    }
}