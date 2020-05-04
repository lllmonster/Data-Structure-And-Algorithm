class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        backtrack(nums, ans, new ArrayList());
        return ans;
    }
    
    private void backtrack(int[] nums, List<List<Integer>> ans, List<Integer> sublist) {
        if (sublist.size() == nums.length) {
            ans.add(new ArrayList(sublist));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (sublist.contains(nums[i])) continue;
            sublist.add(nums[i]);
            backtrack(nums, ans, sublist);
            sublist.remove(sublist.size()-1);
        }
    }
}