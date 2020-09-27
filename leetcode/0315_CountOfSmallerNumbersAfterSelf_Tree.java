class Solution {
    /**
    TODO: Need to do again.
    Method 1: Binary Search Tree
    Method 2: Merge sort (Too tired to do..)
     */
    public List<Integer> countSmaller(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        if (nums == null || nums.length == 0) return ans;
        TreeNode root = new TreeNode(nums[nums.length-1]);
        ans.add(0);
        for (int i = nums.length-2; i >= 0; i--) {
            ans.add(insertNode(root, nums[i]));
        }
        Collections.reverse(ans);
        return ans;
    }
    
    private int insertNode(TreeNode root, int val) {
        int smaller = 0;
        TreeNode node = new TreeNode(val);
        boolean isConnected = false;
        while(!isConnected) {
            if (node.data <= root.data) {
                root.count++;
                if (root.left == null) {
                    root.left = node;
                    isConnected = true;
                } else {
                    root = root.left;
                }
            } else {
                smaller += root.count;
                if (root.right == null) {
                    root.right = node;
                    isConnected = true;
                } else {
                    root = root.right;
                }
            }
        }
        return smaller;
    }
}

class TreeNode {
    TreeNode left;
    TreeNode right;
    int data;
    int count = 1;
    TreeNode(int data) {
        this.data = data;
    }
}