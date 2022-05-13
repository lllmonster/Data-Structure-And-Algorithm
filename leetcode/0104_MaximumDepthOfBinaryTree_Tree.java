/**
Recursion - bottom up */
class Solution {
    public int maxDepth(TreeNode root) {
        return findDepth(root);
    }
    
    private int findDepth(TreeNode root) {
        if (root == null) return 0;
        int left = findDepth(root.left) + 1;
        int right = findDepth(root.right) + 1;
        return Math.max(left, right);
    }
}

/**
Recursion - top down - not so good */
class Solution {
    int depth_max = 0;
    public int maxDepth(TreeNode root) {
        findDepth(root, 1);
        return depth_max;
    }
    
    private void findDepth(TreeNode root, int depth) {
        if (root == null) return ;
        if (root.left == null && root.right == null) {
            depth_max = Math.max(depth_max, depth);
        }
        findDepth(root.left, depth+1);
        findDepth(root.right, depth+1);
    }
}
/**
Iteration - BFS - using queue - cool */
class Solution {
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int depth = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                TreeNode curr = queue.poll();
                if (curr.left != null) queue.offer(curr.left);
                if (curr.right != null) queue.offer(curr.right);
            }
            depth++;
        }
        return depth;
        
    }
}

/**
Iteration - DFS - using stack */
class Solution {
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        
        Stack<TreeNode> stack = new Stack<>();
        Stack<Integer> depths = new Stack<>();
        stack.push(root);
        depths.push(1);
        
        int depth = 0;
        while (!stack.isEmpty()) {
            root = stack.pop();
            int currentDepth = depths.pop();
            if (root.left != null) {
                stack.push(root.left);
                depths.push(currentDepth+1);
            }
            if (root.right != null) {
                stack.push(root.right);
                depths.push(currentDepth+1);
            }
            depth = Math.max(depth, currentDepth);
        }
        return depth;
    }
}