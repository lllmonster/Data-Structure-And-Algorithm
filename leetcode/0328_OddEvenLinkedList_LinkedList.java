class Solution {
    /**
    Idea is easy, hard to write bug-free code directly. O(n), O(1)
    odd.next = even.next;
    even.next = even.next.next;
    Boudary condition is important as well.
     */
    public ListNode oddEvenList(ListNode head) {
        if (head == null) return null;
        ListNode odd = head;
        ListNode even = head.next;
        ListNode evenHead = even;
        while(even != null && even.next != null) {
            odd.next = even.next;
            even.next = even.next.next;
            even = even.next;
            odd = odd.next;
        }
        
        odd.next = evenHead;
        return head;
    }
}