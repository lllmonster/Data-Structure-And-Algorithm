class Solution {
    /**
    Method 1: Recursion (easy), O(n), O(n)
    Method 2: Iterative -> TODO
     */
    public boolean isValidBST(TreeNode root) {
        return isValid(root, null,null);
    }
    
    private boolean isValid(TreeNode root, TreeNode left, TreeNode right) {
        if (root == null) return true;
        if (left != null && left.val >= root.val) return false;
        if (right != null && right.val <= root.val) return false;
        return isValid(root.left, left, root) &&
                isValid(root.right, root, right);
    }
}