public class Solution {
    /**
    Two Pointer (slow and fast)
    */
    public boolean hasCycle(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode s = dummy;
        ListNode f = dummy;
        while (f != null && f.next != null) {
            s = s.next;
            f = f.next.next; 
            if (s == f) return true;
        }
        return false;
    }
}