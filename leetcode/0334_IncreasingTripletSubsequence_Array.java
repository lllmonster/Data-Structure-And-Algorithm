class Solution {
    /**
    Find the three minimum value.
    O(n), O(1)
     */
    public boolean increasingTriplet(int[] nums) {
        int s = Integer.MAX_VALUE;
        int l = Integer.MAX_VALUE;
        for (int num : nums) {
            if (num <= s) {
                s = num;
            } else if (num <= l) {
                l = num;
            } else {
                return true;
            }
        }
        return false;
    }
}