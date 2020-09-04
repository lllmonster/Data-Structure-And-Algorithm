class Solution {
    /**
    Method 1: bottom up
    Method 2: top down
    Method 3: Iterative -> TODO
    */
    public int maxDepth(TreeNode root) {
        //return bottomup(root);
        return topdown(root);
    }
    
    int topdown_ans = 0;
    private int topdown(TreeNode root) {
        topdown_sub(root, 1);
        return topdown_ans;
    }
    
    private void topdown_sub(TreeNode root, int depth) {
        if (root == null) return;
        if (root.left == null && root.right == null) {
            topdown_ans = Math.max(topdown_ans, depth);
        }
        topdown_sub(root.left, depth+1);
        topdown_sub(root.right, depth+1);
    }
    
    private int bottomup(TreeNode root) {
        int ans = bottomup_sub(root);
        return ans;
    }
    
    private int bottomup_sub(TreeNode root) {
        if (root == null) return 0;
        int left = bottomup_sub(root.left);
        int right = bottomup_sub(root.right);
        return Math.max(left, right) + 1;
    }
}