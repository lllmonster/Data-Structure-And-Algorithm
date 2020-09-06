class Solution {
    /**
    Preorder: root, left, right
    inorder: left, root, right
    Recursive Idea: find the root in the inorder array and split it.
    Iterative Idea: hard to think :(
    */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        //return recursive(0, 0, inorder.length-1, preorder, inorder);
        return iterative(preorder, inorder);
    }
    
    private TreeNode iterative(int[] preorder, int[] inorder) {
        Stack<TreeNode> stk = new Stack();
        if (preorder.length == 0) return null;
        TreeNode root = new TreeNode(preorder[0]);
        TreeNode cur = root;
        for (int i = 1, j = 0; i < preorder.length; i++) {
            if (cur.val != inorder[j]) {
                cur.left = new TreeNode(preorder[i]);
                stk.push(cur);
                cur = cur.left;
            } else {
                j++;
                while (!stk.isEmpty() && stk.peek().val == inorder[j]) {
                    cur = stk.pop();
                    j++;
                }
                cur.right = new TreeNode(preorder[i]);
                cur = cur.right;
            }
        }
        return root;
    }
    
    private TreeNode recursive(int preStart, int inStart, int inEnd, int[] preorder, int[] inorder) {
        if (preStart > preorder.length - 1 || inStart > inEnd) return null;
        TreeNode root = new TreeNode(preorder[preStart]);
        int index = 0;
        for (int i = inStart; i <= inEnd; i++) {
            if (inorder[i] == root.val) {
                index = i;
                break;
            }
        }
        root.left = recursive(preStart+1, inStart, index-1, preorder, inorder);
        root.right = recursive(preStart+index-inStart+1, index+1, inEnd, preorder, inorder);
        return root;
    }
}