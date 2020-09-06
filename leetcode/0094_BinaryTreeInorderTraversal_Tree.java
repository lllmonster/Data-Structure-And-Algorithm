class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        //return recursive(root);
        return iterative(root);
    }
    
    private List<Integer> iterative(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        Stack<TreeNode> stk = new Stack();
        if (root == null) return ans;
        
        while (root != null || !stk.isEmpty()) {
            while (root != null) {
                stk.push(root);
                root = root.left;
            }
            root = stk.pop();
            ans.add(root.val);
            root = root.right;
        }
        return ans;
    }
    
    private List<Integer> recursive(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        recursive_sub(root, ans);
        return ans;
    }
    
    private void recursive_sub(TreeNode root, List<Integer> ans) {
        if (root == null) return;
        recursive_sub(root.left, ans);
        ans.add(root.val);
        recursive_sub(root.right, ans);
    }
}