class Solution {
    public Node connect(Node root) {
        //return iterative(root);
        return recursive(root);
    }
    
    private Node iterative(Node root) {
        Node start = root;
        while (root != null) {
            Node cur = root;
            while (cur != null) {
                if (cur.left != null && cur.right != null) {
                    cur.left.next = cur.right;
                }
                if (cur.right != null && cur.next != null) {
                    cur.right.next = cur.next.left;
                }
                cur = cur.next;
            }
            root = root.left;
        }
        return start;
    }
    
    private Node recursive(Node root) {
        if (root == null) return null;
        if (root.left != null && root.right != null) {
            root.left.next = root.right;
        }
        if (root.right != null && root.next != null) {
            root.right.next = root.next.left;
        }
        recursive(root.left);
        recursive(root.right);
        return root;
    }
}