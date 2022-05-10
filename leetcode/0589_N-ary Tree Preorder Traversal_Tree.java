/**
PreOrder Traversal - Recursive
 */
class Solution {
    public List<Integer> preorder(Node root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        traversePreOrder(root, res);
        return res;
    }
    
    private void traversePreOrder(Node root, List<Integer> res) {
        if (root == null) return;
        res.add(root.val);
        for (Node child : root.children) {
            traversePreOrder(child, res);
        }
    }
}

/**
PreOrder Traversal - Interative 
*/
class Solution {
    public List<Integer> preorder(Node root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node curr = stack.pop();
            res.add(curr.val);
            for (int i = curr.children.size() - 1; i >= 0; i--) {
                stack.push(curr.children.get(i));
            }
        }
        
        return res;
    }
}


/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/