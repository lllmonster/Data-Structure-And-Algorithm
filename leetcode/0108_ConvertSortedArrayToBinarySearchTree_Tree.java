class Solution {
    public TreeNode sortedArrayToBST(int[] nums) {
        return sort(nums, 0, nums.length-1);
    }
    
    private TreeNode sort(int[] nums, int lo, int hi) {
        if (hi < lo) return null;
        int mid = (lo + hi) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = sort(nums, lo, mid-1);
        root.right = sort(nums, mid+1, hi);
        return root;
    }
}