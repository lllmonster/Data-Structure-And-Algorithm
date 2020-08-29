class Solution {
    /**
    Two Pointer(slow and fast), O(n), O(1)
    Find the middle, reverse the rest of it, compare.
    */
    public boolean isPalindrome(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        
        ListNode s = dummy;
        ListNode f = dummy;
        
        while (f != null && f.next != null) {
            s = s.next;
            f = f.next.next;
        }
        
        ListNode middle = reverse(s);
        ListNode start = dummy.next;
        
        while (start != null && middle != null) {
            if (start.val != middle.val) return false;
            start = start.next;
            middle = middle.next;
        }
        return true;
    }
    
    private ListNode reverse(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode tmp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = tmp;
        }
        return prev;
    }
}