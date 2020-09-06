class Solution {
    public int kthSmallest(TreeNode root, int k) {
        //return recursive(root, k);
        return iterative(root, k);
    }
    
    private int iterative(TreeNode root, int k) {
        Stack<TreeNode> stk = new Stack();
        int cnt = 0;
        while (root != null || !stk.isEmpty()) {
           if (root != null) {
               stk.push(root);
               root = root.left;
           } else {
               root = stk.pop();
               cnt++;
               if (cnt == k) return root.val;
               root = root.right;
           }
        }
        return Integer.MIN_VALUE;
    }
    
    private int cnt = 0;
    private int ans = 0;
    private int recursive(TreeNode root, int k) {
        recursive_sub(root, k);
        return ans;
    }
    
    private void recursive_sub(TreeNode root, int k) {
        if (root == null) return;
        recursive_sub(root.left, k);
        cnt++;
        if (cnt == k) {ans = root.val; return;}
        recursive_sub(root.right, k);
        
    }
}