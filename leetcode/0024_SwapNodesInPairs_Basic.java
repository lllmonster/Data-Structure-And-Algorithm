/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0);
        ListNode list = dummy;
        list.next = head;
        while(list.next != null && list.next.next != null) {
            ListNode first = list.next;
            ListNode second = list.next.next;
            first.next = second.next;
            list.next = second;
            second.next = first;
            list = list.next.next;
        }
        return dummy.next;
    }
}