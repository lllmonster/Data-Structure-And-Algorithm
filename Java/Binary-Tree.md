# Binary Tree
## Introduction
A tree is a data structure to simulate a hierarchical tree structure.  
Each node of tree will have a root value and a list of references to other nodes which are called child nodes. From graph view, a tree can also be defined as a directed acyclic graph which has `N nodes and N - 1 edges`.  
A `Binary Tree` is a tree data structure in which each node has `at most two children`, which are referred to as the left child and the right child.  

## Traverse a Tree
1.  Pre-order Traversal :
root - left - right  

```java
// Recursive 
class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        helper(ans, root);
        return ans;
    }

    private void helper(List<Integer> ans, TreeNode root) {
        if (root == null) return;
        ans.add(root.val);
        helper(ans, root.left);
        helper(ans, root.right);
    }
}
```


```java
// Interative
class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        Stack<TreeNode> stk = new Stack<>();
        if (root != null) stk.push(root);
        while(!stk.isEmpty()) {
            root = stk.pop();
            ans.add(root.val);
            if (root.right != null) {
                stk.push(root.right);
            }
            if (root.left != null) {
                stk.push(root.left);
            }
        }
        return ans;
    }
}
```

2.  In-order Traversal :  
left - root - right  
Recursive   
```java
// Recursive 
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        helper(ans, root);
        return ans;
    }

    private void helper(List<Integer> ans, TreeNode root) {
        if (root == null) return;
        helper(ans, root.left);
        ans.add(root.val);
        helper(ans, root.right);
    }
}
```

```java
// Interative
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        Stack<TreeNode> stk = new Stack<>();
        while(root != null || !stk.isEmpty()) {
            while(root != null) {
                stk.push(root);
                root = root.left;
            }
            root = stk.pop();
            ans.add(root.val);
            root = root.right;
        }
        return ans;
    }
}
```
3.  Post-order Traversal :  
left - right - root  
 
```java
// Recursive 
class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        helper(ans, root);
        return ans;
    }

    private void helper(List<Integer> ans, TreeNode root) {
        if (root == null) return;
        helper(ans, root.left);
        helper(ans, root.right);
        ans.add(root.val);
    }
}
```

```java
// Interative
class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        Stack<TreeNode> stk = new Stack<>();
        while(root != null) {
            ans.addFirst(root.val);
            if (root.left != null) stk.push(root.left);
            root = root.right;
            if (root == null && !stk.isEmpty()) {
                root = stk.pop();
            }
        }
        return ans;
    }
}
```

## Level - Order Traversal
Level - order traversal is to traverse the tree level by level.  
`Breadth-First Search` is an algorithm to traverse or search in data structure like tree or a graph.  
When we do breadth-first search in a tree, the order of the nodes we visited is in level order.  
Typically, we use a queue to help us to do BFS.  

## Solve Tree Problems Recursively
### Top - down Solution
"Top -down" means that in each recursion level, we will visit the node first to come up with some values, and pass these values to its children when calling the function recursively. So the "top-down" solution can be considered as kind of **Pre-Order** traversal.  
```
1. return specific value for null nodes
2. update the answer if needed  
3. left_ans = top_down(root.left, left_params)
4. right_ans = top_down(root.right, right_params)
5. return the answer if needed
```
For instance, consider this problem: given a binary tree, find its maximum depth.  
We know that the depth of the root node is 1. For each node, if we know the depth of the node, we will know the depth of its children. Therefore, if we pass the depth of the node as a parameter when calling the function recursively, all the nodes know the depth of themselves. And for leaf nodes, we can use the depth to update the final answer.  
```
// Pseudocode
return if root is null
if root is a leaf node:
    answer = max(answer, depth);
maximum_depth(root.left, depth + 1)
maximum_depth(root.right, depth + 1)
```
```
// java code
private int ans;
private void maximum_depth(TreeNode root, int depth) {
  if (root == null) return;
  if (root.left == null && root.right == null) {
    ans = Math.max(ans, depth);
  }
  maximum_depth(root.left, depth + 1);
  maximum_depth(root.right, depth + 1);
}
```

### Bottom - up Solution
In each recursion level, we will firstly call the functions recursively for all the children nodes and the come up with the answer according to the return values and the values of the root node itself. This process can be regarded as kind of **Post-Order** traversal.  
```
return specific value for null node
left_ans = bottom_up(root.left);
right_ans = bottom_up(root.right);
return answers;
```
Let's go on discussing the question about maximum depth but using a different way of thinking: for a single node of the tree, what will be the maximum depth `x` of the subtree rooted at itself?  
If we know the maximum depth `l` of the subtree rooted at its left child and the maximum depth `r` of the subtree rooted at its right child. Then `x = max(l, r) + 1`.  
It means that for each node, we can get the answer after solving the problem of its children. Therefore, we can solve this problem using a "bottom - up" solution.  
```
// Pseudocode
return 0 if root is null
left_depth = maximum_depth(root.left)
right_depth = maximum_depth(root.right)
return max(left_depth, right_depth) + 1
```
```
// java code
public int maximum_depth(TreeNode root) {
  if (root == null) return 0;
  int left_depth = maximum_depth(root.left);
  int right_depth = maximum_depth(root.right);
  return Math.max(left_depth, right_depth) + 1;
}
```
