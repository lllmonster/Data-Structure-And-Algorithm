/**
smart recursive. didn't think of it
Brute DFS 
- Time O(N * min(L,H))
- Space O(H)
- where N = tree size, H = tree height, L = list length. */
class Solution {
    public boolean isSubPath(ListNode head, TreeNode root) {
        if (head == null) return true;
        if (root == null) return false;
        return dfs(root, head)
            || isSubPath(head, root.left)
            || isSubPath(head, root.right);
    }
    
    private boolean dfs(TreeNode root, ListNode head) {
        if (head == null) return true;
        if (root == null) return false;
        
        return root.val == head.val
            && (dfs(root.left, head.next) || dfs(root.right, head.next));
    }
}

/**
DP
- Time O(N + L)
- Space O(L + H)
- where N = tree size, H = tree height, L = list length. TODO*/

/**
KMP search - TODO */