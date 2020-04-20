class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        backtrack(nums, ans, new ArrayList(), new boolean[nums.length]);
        return ans;
    }
    
    private void backtrack(int[] nums, List<List<Integer>> ans, List<Integer> sublist,
                          boolean[] visited) {
        if (sublist.size() == nums.length) {
            if (!ans.contains(sublist)) {
                ans.add(new ArrayList(sublist));
            }
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if(!visited[i]) {
                sublist.add(nums[i]);
                visited[i] = true;
                backtrack(nums, ans, sublist, visited);
                visited[i] = false;
                sublist.remove(sublist.size()-1);
            }
        }
    }
}