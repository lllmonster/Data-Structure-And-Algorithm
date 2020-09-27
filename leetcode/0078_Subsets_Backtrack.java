class Solution {
    /**
    Method 1: Backtrack
    Method 2: Iterate (Cascading)
    O(n*2^n), O(n*2^n)
    */
    public List<List<Integer>> subsets(int[] nums) {
        // return usingBacktrack(nums);
        return usingCascading(nums);
    }
    
    private List<List<Integer>> usingCascading(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        ans.add(new ArrayList());
        for (Integer num : nums) {
            int size = ans.size(); // tricky
            for (int i = 0; i < size; i++) {
                List<Integer> tmp = new ArrayList(ans.get(i)); // tricky
                tmp.add(num);
                ans.add(tmp);
            }
        }
        return ans;
    }
    
    private List<List<Integer>> usingBacktrack(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        backtrack(0, new ArrayList(), nums, ans);
        return ans;
    }
    
    private void backtrack(int start, List<Integer> sub, int[] nums, List<List<Integer>> ans) {
        ans.add(new ArrayList(sub));
        for (int i = start; i < nums.length; i++) {
            sub.add(nums[i]);
            backtrack(i+1, sub, nums, ans);
            sub.remove(sub.size()-1);
        }
    }
}