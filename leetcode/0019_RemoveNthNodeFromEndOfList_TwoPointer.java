/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    /**
    * Two Pointer. O(n). One pass
    * first = second + n + 1
    * Boundary requirement is important.
    */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // return whileMethod(head, n);
        return forMethod(head, n);
        
    }

    private ListNode forMethod(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode second = dummy; // have to be dummy instead of head
        ListNode first = dummy;
        for (int i = 0; i <= n; i++) {
            first = first.next;
        }
        
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        
        second.next = second.next.next;
        return dummy.next;
    }

    private ListNode whileMethod(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode l = dummy;
        ListNode r = head;
        while (n > 0 && r != null) {
            r = r.next;
            n--;
        }
        while(r != null) {
            l = l.next;
            r = r.next;
        }
        l.next = l.next.next;
        return dummy.next;
    }
}