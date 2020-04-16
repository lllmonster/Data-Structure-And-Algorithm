/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k == 1) return head;
        ListNode dummy = new ListNode(0);
        ListNode begin = dummy;
        begin.next = head;
        int i = 0;
        while (head != null) {
            i++;
            if(i % k == 0) {
                begin = reverse(begin, head.next);
                head = begin.next;
            } else {
                head = head.next;
            }
        }
        return dummy.next;
    }
    
    private ListNode reverse(ListNode s, ListNode e) {
        ListNode pre = s;
        ListNode cur = s.next;
        ListNode first = cur;
        while (cur != e) {
            ListNode nex = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nex;
        }
        s.next = pre;
        first.next = e;
        return first;
    }
}