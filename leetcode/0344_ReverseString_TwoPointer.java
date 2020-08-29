class Solution {
    /**
     swap, O(n), O(1)
     */
    public void reverseString(char[] s) {
        int l = 0, r = s.length-1;
        while (l < r) {
            char tmp = s[l];
            s[l] = s[r];
            s[r] = tmp;
            l++;r--;
        }
    }
}