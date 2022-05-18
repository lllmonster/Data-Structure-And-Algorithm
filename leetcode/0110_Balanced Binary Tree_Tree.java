/**
my own solu */
class Solution {
    boolean isBalanced = true;
    public boolean isBalanced(TreeNode root) {
        dfs(root);
        return isBalanced;
    }
    
    private int dfs(TreeNode root) {
        if (root == null) return 0;
        int left = dfs(root.left) + 1;
        int right = dfs(root.right) + 1;
        if (Math.abs(left - right) > 1) {
            isBalanced = false;
        }
        return Math.max(left, right);
    } 
}

/**
solu without global parameter */
class Solution {
    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;
        return Math.abs(height(root.left) - height(root.right)) < 2 
            && isBalanced(root.left)
            && isBalanced(root.right);
    }
    
    private int height(TreeNode root) {
        if (root == null) return 0;
        int left = height(root.left) + 1;
        int right = height(root.right) + 1;
        
        return Math.max(left, right);
    } 
}