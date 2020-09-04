class Solution {
    /**
    Method 1: Recursive, easy
    Method 2: Iterative
    */
    public boolean isSymmetric(TreeNode root) {
        //return recursive(root, root);
        return iterative(root);
    }
    
    private boolean recursive(TreeNode left, TreeNode right) {
        if (left == null && right == null) return true;
        if (left == null || right == null) return false;
        return (left.val == right.val) &&
                recursive(left.left, right.right) &&
                recursive(left.right, right.left);
    }
    
    private boolean iterative(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        q.offer(root);
        while (!q.isEmpty()) {
            TreeNode r1 = q.poll();
            TreeNode r2 = q.poll();
            if (r1 == null && r2 == null) continue;
            if (r1 == null || r2 == null) return false;
            if (r1.val != r2.val) return false;
            q.offer(r1.left);
            q.offer(r2.right);
            q.offer(r1.right);
            q.offer(r2.left);
        }
        return true;
    }
}