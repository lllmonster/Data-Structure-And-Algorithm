class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        // return recursive(root);
        return iterative(root);
        
    }
    
    private List<List<Integer>> iterative(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();
        if (root == null) return ans;
        q.offer(root);
        boolean zigzag = false;
        while (!q.isEmpty()) {
            int size = q.size();
            List<Integer> sublist = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                if (q.peek().left != null) q.offer(q.peek().left);
                if (q.peek().right != null) q.offer(q.peek().right);
                if (zigzag) {
                    sublist.add(0, q.poll().val);
                } else {
                    sublist.add(q.poll().val);
                }
            }
            zigzag = !zigzag;
            ans.add(sublist);
        }
        return ans;
    }
    
    private List<List<Integer>> recursive(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        recursive_sub(root, 0, ans);
        return ans;
    }
    
    private void recursive_sub(TreeNode root, int depth, List<List<Integer>> ans) {
        if (root == null) return;
        if (ans.size() <= depth) {
            ans.add(new ArrayList());
        }
        
        if (depth % 2 == 0) ans.get(depth).add(root.val);
        else ans.get(depth).add(0, root.val);
        
        recursive_sub(root.left, depth+1, ans);
        recursive_sub(root.right, depth+1, ans);
    }
}