class Solution {
    /**
    Method 1: Recursive
    Method 2: Iterative
    */
    public List<List<Integer>> levelOrder(TreeNode root) {
        //return recursive(root);
        return iterative(root);
    }
    
    private List<List<Integer>> recursive(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        recursive_sub(root, ans, 0);
        return ans;
    }
    
    private void recursive_sub(TreeNode root, List<List<Integer>> ans, int depth) {
        if (root == null) return;
        if (ans.size() <= depth) {
            ans.add(new ArrayList());
        }
        ans.get(depth).add(root.val);
        recursive_sub(root.left, ans, depth+1);
        recursive_sub(root.right, ans, depth+1);
    }
    
    private List<List<Integer>> iterative(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) return ans;
        q.offer(root);
        while (!q.isEmpty()) {
            int len = q.size();
            List<Integer> sublist = new ArrayList<>();
            for (int i = 0; i < len; i++) {
                if (q.peek().left != null) {q.offer(q.peek().left);}
                if (q.peek().right != null) {q.offer(q.peek().right);}
                sublist.add(q.poll().val);
            }
            ans.add(sublist);
        }
        return ans;
    }
}