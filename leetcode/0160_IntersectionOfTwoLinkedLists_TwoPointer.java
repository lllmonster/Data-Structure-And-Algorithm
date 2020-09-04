public class Solution {
    /**
    HashSet, O(m+n), O(m)
    Two Pointer, O(m+n), O(1)
        it's like slow and fast two pointer algorithm.
        iterate each list two times and find intersection.
        Two method is the same with different understanding.
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        //return easyUnderstand(headA, headB);
        return elegantSolution(headA, headB);
    }
    
    private ListNode elegantSolution(ListNode headA, ListNode headB) {
        ListNode na = headA;
        ListNode nb = headB;
        while (na != nb) {
            na = (na != null) ? na.next : headB;
            nb = (nb != null) ? nb.next : headA;
        }
        return na;
    }
    
    private ListNode easyUnderstand(ListNode headA, ListNode headB) {
        ListNode na = headA;
        ListNode nb = headB;
        boolean ia = true; //still not iterate headB
        boolean ib = true; // still not iterate headA
        while (na != null && nb != null) {
            if (na == nb) return na;
            na = na.next;
            nb = nb.next;
            if (na == null && ia) {
                na = headB;
                ia = false;
            }
            if (nb == null && ib) {
                nb = headA;
                ib = false;
            }
        }
        
        return null;
    }
}