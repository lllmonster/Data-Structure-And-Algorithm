# Binary Search Tree
Binary Search Tree (BST), a special form of a binary tree, satisfies the binary search property:  
1. The value in each node must be `greater than` (or equal to) any values stored in its left subtree.  
2. The value in each node must be `less than` (or equal to) any values stored in its right subtree.  
## Operation
### Search
```
class Solution {
    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null || root.val == val) return root;
        if (root.val > val) return searchBST(root.left, val);
        return searchBST(root.right, val);
    }
}
```
### Insert
```
class Solution {
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            root = new TreeNode(val);
            return root;
        }
        if (root.val < val) root.right = insertIntoBST(root.right, val);
        else root.left = insertIntoBST(root.left, val);
        return root;
    }
}
```
### Delete
According to the number of its children, we should consider three different cases:  
1. If the target node has no child, we can simply remove the node.
2. If the target node has one child, we can use its child to replace itself.
3. If the target node has two children, replace the node with its in-order successor or predecessor node and delete that node.
```
class Solution {
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null;
        if (root.val < key) {
            root.right = deleteNode(root.right, key);
        } else if (root.val > key) {
            root.left = deleteNode(root.left, key);
        } else {
            if (root.left == null && root.right == null) root = null;
            else if (root.left != null && root.right == null) root = root.left;
            else if (root.left == null && root.right != null) root = root.right;
            else {
                root.val = minValue(root.right);
                root.right = deleteNode(root.right, root.val);
            }
        }
        return root;
    }
    
    public int minValue(TreeNode root) {
        while (root.left != null) {
            root = root.left;
        }
        return root.val;
    }
}
```
