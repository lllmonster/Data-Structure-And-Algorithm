## Linked List
Definition for singly-linked list.
```
struct SinglyListNode {
    int val;
    SinglyListNode *next;
    SinglyListNode(int x) : val(x), next(NULL) {}
};
```
Definition for doubly-linked list.
```
struct DoublyListNode {
    int val;
    DoublyListNode *next, *prev;
    DoublyListNode(int x) : val(x), next(NULL), prev(NULL) {}
};
```
Definition of node and pointer, instead of dummyhead in Java (LC86)
```
ListNode* partition(ListNode* head, int x) {
        ListNode node1(0), node2(0);
        ListNode *p1 = &node1, *p2 = &node2;
        while (head) {
            if (head->val < x) {
                p1 = p1->next = head;;
            } else {
                p2 = p2->next = head;
            }
            head = head->next;
        }
        p2->next = NULL;
        p1->next = node2.next;
        return node1.next;
    }
```
