# Binary Tree
## Introduction
A tree is a data structure to simulate a hierarchical tree structure.  
Each node of tree will have a root value and a list of references to other nodes which are called child nodes. From graph view, a tree can also be defined as a directed acyclic graph which has `N nodes and N - 1 edges`.  
A `Binary Tree` is a tree data structure in which each node has `at most two children`, which are referred to as the left child and the right child.  

## Traverse a Tree
1.  Pre-order Traversal :
root - left - right
2.  In-order Traversal :
left - root - right
3.  Post-order Traversal :
left - right - root

## Level - Order Traversal
Level - order traversal is to traverse the tree level by level.  
`Breadth-First Search` is an algorithm to traverse or search in data structure like tree or a graph.  
When we do breadth-first search in a tree, the order of the nodes we visited is in level order.  
Typically, we use a queue to help us to do BFS.  
