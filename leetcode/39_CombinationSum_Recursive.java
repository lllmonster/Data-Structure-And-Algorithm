class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        backtrack(ans, candidates, target, new ArrayList(), 0);
        return ans;
    }
    
    private void backtrack(List<List<Integer>> ans, int[] candidates, int target, List<Integer> sublist, int start) {
        if (target < 0) return;
        if (target == 0) {
            ans.add(new ArrayList(sublist));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            sublist.add(candidates[i]);
            backtrack(ans, candidates, target - candidates[i], sublist, i);
            sublist.remove(sublist.size()-1);
        }
    }
}