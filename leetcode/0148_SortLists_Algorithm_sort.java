class Solution {
    /**
    Method 1, Recursion, O(nlogn), space is O(logn)
    Method 2, Bottom-to-up, O(nlogn), space is O(1), hard to write
    */
    public ListNode sortList(ListNode head) {
        //return recursionMergeSort(head);
        return bottomupMergeSort(head);
    }
    
    private ListNode bottomupMergeSort(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        int len = 0;
        while (head != null) {
            len++;
            head = head.next;
        }

        for (int round = 1; round < len; round <<= 1) {
            ListNode prev = dummy;
            ListNode cur = dummy.next;
            while (cur != null) {
                ListNode left = cur;
                ListNode right = split(left, round);
                cur = split(right, round);
                prev = merge(left, right, prev);
            }
        }
        return dummy.next;
    }

    private ListNode split(ListNode head, int round) {
        if (head == null) return null;
        for (int i = 1; head.next != null && i < round; i++) {
            head = head.next;
        }
        ListNode right = head.next;
        head.next = null;
        return right;
    }

    private ListNode merge(ListNode l1, ListNode l2, ListNode cur) {
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }

        if (l1 != null) cur.next = l1;
        if (l2 != null) cur.next = l2;
        while (cur.next != null) cur = cur.next;
        return cur;

    }
    
    private ListNode recursionMergeSort(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode s = head;
        ListNode f = head;
        ListNode end = null;
        while (f != null && f.next != null) {
            end = s;
            s = s.next;
            f = f.next.next;
        }
        end.next = null;
        ListNode l1 = recursionMergeSort(head);
        ListNode l2 = recursionMergeSort(s);
        
        return mergeTwoLists(l1, l2);
    }
    
    private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode head = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
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