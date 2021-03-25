class Solution {
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> ans = new ArrayList<>();
        
        int start = lower;
        for (int i = 0; i < nums.length; i++) {
            
            if (nums[i] < start) {
                continue;
            } else if (nums[i] == start) {
                start++;
                continue;
            } else {
                ans.add(getRange(start, nums[i]-1));
                start = nums[i] + 1;
            }
        }
        
        if (start <= upper) {
            ans.add(getRange(start, upper));
        }
        return ans;
    }
    
    private String getRange(int l, int r) {
        return (l == r) ? String.valueOf(l) : l + "->" + r;
    }
}