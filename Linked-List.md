# Linked List
## Introduction
Similar to the array, the linked list is also a `linear` data structure.  
There are two types of linked list: single linked list and double linked list.  
Singly linked list:  
![alt text](./image/singleLinkedList.png)  
Doubly linked list:
![alt text](./image/doubleLinkedList.png)  

## Singly Linked List
### Definition
```
// Definition for singly linked list.
public class SinglyListNode {
  int val;
  SinglyListNode next;
  SinglyListNode(int x) {val = x; }
}
```
In most cases, we will use `head` node to represent the whole list.
### Operations
In linked list, it takes O(n) time on average to visit an element by index. But in the array, we are able to access a random element in constant time.
#### Add Operation - Singly Linked List - O(1)
#### Delete Operation - Singly Linked List - O(n)
see Linked-List-implementation.java

## Two-Pointer Technique in Linked List
Let's start a classic problem,  
*Give a linked list, determine if it has a cycle in it.*  
We might have come up with the solution using the `hash table`. But there is a more efficient solution using the `two-pointer technique`.
1. `If there is no cycle, the fast pointer will stop at the end of linked list.`  
2. `If there is a cycle, the fast pointer will eventually meet with the slow pointer.`  

How to determine the proper speed for the two pointers?  
It is a safe choice to move the slow pointer `one step` at a time while moving the fast pointer `two steps` at a time. So smart :)
```
// Initialize slow & fast pointers
ListNode slow = head;
ListNode fast = head;
/**
 * Change this condition to fit specific problem.
 * Attention: remember to avoid null-pointer error
 **/
while (slow != null && fast != null && fast.next != null) {
    slow = slow.next;         // move slow pointer one step each time
    fast = fast.next.next;    // move fast pointer two steps each time
    if (slow == fast) {       // change this condition to fit specific problem
        return true;
    }
}
return false;   // change return value to fit specific problem
```
### Tips
1. Always examine if the node is null before you call the next field.
2. Carefully define the end conditions of your loop, to avoid endless loop.

## Reversed Linked List
Problem: Reverse a singly linked list.  
One Solution is to `iterate the nodes in original order and move them to the head of the list one by one.`  
In this algorithm, each node will be moved exactly once.  
Therefore, the time complexity is `O(n)`, the sapce complexity is `O(1)`.
There are two algorithms to reverse linked list: iterative and recursive.
```
// iterative
public ListNode reverseList(ListNode head) {
    if(head == null || head.next == null) return head;
    ListNode prev = null;
    ListNode cur = head;
    ListNode temp = cur;
    while (cur != null) {
        temp = cur.next;
        cur.next = prev;
        prev = cur;
        cur = temp;
    }
    return prev;
}
// recursive
public ListNode reverseList(ListNode head) {
    if (head == null || head.next == null) return head;
    ListNode ans = reverseList(head.next);
    head.next.next = head;
    head.next = null;
    return ans;
}
```
## Doubly Linked List
### Node structure
```
// Definition for doubly-linked list.
class DoublyListNode {
    int val;
    DoublyListNode next, prev;
    DoublyListNode(int x) {val = x;}
}
```
### Operations
#### Add Operation - Doubly Linked List - O(1)
#### Delete Operation - Doubly Linked List - O(1)  

## Time complexity Comparison'''''''''''''''''''''''''''''''''''''''''''''''''''''''''
![alt text](./image/timeComparison.png)  
