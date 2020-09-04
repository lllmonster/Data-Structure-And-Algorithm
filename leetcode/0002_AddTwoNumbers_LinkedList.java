class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode ans = dummy;
        int sign = 0;
        while (l1 != null || l2 != null) {
            int n1 = 0, n2 = 0;
            if (l1 != null) {n1 = l1.val;l1=l1.next;}
            if (l2 != null) {n2 = l2.val;l2=l2.next;}
            ans.next = new ListNode((n1+n2+sign)%10);
            ans = ans.next;
            sign = (n1+n2+sign)/10;
        }
        if (sign != 0) {
            ans.next = new ListNode(sign);
        }
        return dummy.next;
        
    }
}