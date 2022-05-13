/**
Recursion */
class Solution {
    public int sumOfLeftLeaves(TreeNode root) {
        return findSum(root, false);
    }
    
    private int findSum(TreeNode root, boolean ifLeft) {
        if (root == null) return 0;
        if (root.left == null && root.right == null && ifLeft) {
            return root.val;
        }
        return findSum(root.left, true) + findSum(root.right, false);
    }
}

/**
Iteration - using deque instead of stack
as stack is deprecrated. o_o */
class Solution {
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) return 0;
        
        Deque<TreeNode> stack = new LinkedList<>();
        int sum = 0;
        stack.push(root);
        
        while (!stack.isEmpty()) {
            TreeNode curr = stack.pop();
            if (isLeaf(curr.left)) {
                sum += curr.left.val;
            }
            if (curr.left != null) stack.push(curr.left);
            if (curr.right != null) stack.push(curr.right);
        }
        
        return sum;
    }
    
    private boolean isLeaf(TreeNode node) {
        return node != null && node.left == null && node.right == null;
    }
}