/**
Recursive - o(n),o(n)*/
class Solution {
    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        getHeight(root, ans);
        return ans;
    }
    
    private int getHeight(TreeNode n, List<List<Integer>> ans) {
        if (n == null) return -1;
        int leftHeight = getHeight(n.left, ans);
        int rightHeight = getHeight(n.right, ans);
        int currHeight = Math.max(leftHeight, rightHeight) + 1;
        if (ans.size() == currHeight) {
            ans.add(new ArrayList<>());
        }
        ans.get(currHeight).add(n.val);
        return currHeight;
    }
}