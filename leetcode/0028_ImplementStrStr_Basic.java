/**
my own solu */
class Solution {
    public int strStr(String haystack, String needle) {
        for (int i = 0; i < haystack.length(); i++) {
            int m = i;
            int n = 0;
            while (n < needle.length() && m < haystack.length()) {
                if (needle.charAt(n) != haystack.charAt(m)) {
                    break;
                } else {
                    m++;
                    n++;
                }
            }
            if (n == needle.length()) return i;
        }
        return -1;
    }
}

/**
elegant solu */
class Solution {
    public int strStr(String haystack, String needle) {
        if (needle.length() == 0) return 0;
        for (int i = 0;;i++) {
            for (int j = 0;; j++) {
                if (j == needle.length()) return i;
                if (i + j == haystack.length()) return -1;
                if (haystack.charAt(i+j) != needle.charAt(j)) break;
            }
        }
    }
}