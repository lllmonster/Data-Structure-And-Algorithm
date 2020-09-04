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
    * Divide and Conquer, O(Nlogk), O(1)
    How to divide is tricky
    */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        int len = lists.length;
        while(len > 1) {
            int j = 0;
            for (int i = 0; i < len; i+=2) {
                lists[j] = (i == len -1) ? lists[i] : merge(lists[i], lists[i+1]);
                j++;
            }
            len = j;
        }
        return lists[0];
    }
    
    private ListNode merge(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode head = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                head.next = l1;
                l1 = l1.next;
            } else {
                head.next = l2;
                l2 = l2.next;
            }
            head = head.next;
        }
        if (l1 != null) head.next = l1;
        if (l2 != null) head.next = l2;
        return dummy.next;
    }
    
}