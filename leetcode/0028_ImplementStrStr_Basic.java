class Solution {
    public int strStr(String haystack, String needle) {
        //return normalMethod(haystack, needle);
        return elegantMethod(haystack, needle);
    }
    
    private int elegantMethod(String haystack, String needle) {
        if (needle.length() == 0) return 0;
        for (int i = 0;;i++) {
            for (int j = 0;;j++) {
                if (j == needle.length()) return i;
                if (i + j == haystack.length()) return -1;
                if (haystack.charAt(i+j) != needle.charAt(j)) break;
            }
        }
    }
    
    private int normalMethod(String haystack, String needle) {
        if (needle.length() == 0) return 0;
        for (int i = 0; i < haystack.length(); i++) {
            int j = 0;
            int k = i;
            while(k < haystack.length() && j < needle.length() && haystack.charAt(k) == needle.charAt(j)) {
                k++;j++;
            }
            if (j == needle.length()) return i;
        }
        return -1;
    }
}