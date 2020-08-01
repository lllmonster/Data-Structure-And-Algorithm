class Solution {
    /**
    * Also can be done in DP. TODO
    O(n^2), O(1)
     */
    public String longestPalindrome(String s) {
        char[] chars = s.toCharArray();
        int len = 0, l = 0, r = 0;
        for (int i = 0 ; i < chars.length; i++) {
            int len1 = expandCenter(chars, i, i);
            int len2 = expandCenter(chars, i, i+1);
            if (len < Math.max(len1, len2)) {
                len = len1 > len2 ? len1 : len2;
                l = i - (len-1) / 2;
                r = i + len / 2 + 1;
            }
        }
        return s.substring(l, r);
    }
    
    private int expandCenter(char[] str, int i, int j) {
        while (i >= 0 && j < str.length && str[i]==str[j]) {
            i--;
            j++;
        }
        return j-i-1;
    }
}