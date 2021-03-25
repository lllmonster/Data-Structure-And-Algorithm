class Solution {
    /**
    Two Pointer. Easy
     */
    public int maxDistToClosest(int[] seats) {
        int len = 0;
        int start = -1;
        
        for (int i = 0; i < seats.length; i++) {
            if (seats[i] == 1) {
                len = (start < 0) ? i : Math.max(len, (i - start) / 2);
                start = i;
            }
        }
        
        len = Math.max(len, seats.length - 1 - start);
        
        return len;
        
    }
}