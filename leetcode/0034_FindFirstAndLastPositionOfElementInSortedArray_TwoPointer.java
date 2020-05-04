class Solution {
    public int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0) return new int[]{-1,-1};
        int l = 0, r = nums.length - 1;
        int start = -1, end = -1;
        
        // left
        while (l < r) {
            int mid = (l + r) / 2;
            if (target <= nums[mid]) r = mid;
            else l = mid + 1;
        }
        if (nums[r] == target) start = r;
        
        // right
        l = 0; r = nums.length - 1;
        while (l < r) {
            int mid = (l + r + 1) / 2;
            if (target >= nums[mid]) l = mid;
            else r = mid-1;
        }
        if (nums[l] == target) end = l;
        
        return new int[]{start, end};
    }

}xx