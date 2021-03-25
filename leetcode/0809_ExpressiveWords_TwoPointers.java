class Solution {
    /**
    Easy to think. Some corner case. Two pointers.
     */
    public int expressiveWords(String S, String[] words) {
        if (S == null || words == null) {
            return 0;
        }
        
        int res = 0;
        for (String word : words) {
            if (valid(word, S)) {
                res++;
            }
        }
        return res;
    }
    
    private boolean valid(String word, String s) {
        if (word == null) return false;
        
        int si = 0, wi = 0;
        while (si < s.length() && wi < word.length()) {
            if (s.charAt(si) != word.charAt(wi)) return false;
            si++;wi++;
            int wcnt = 1;
            while (wi < word.length() && word.charAt(wi) == word.charAt(wi-1)) {
                wcnt++;
                wi++;
            }
            int scnt = 1;
            while (si < s.length() && s.charAt(si) == s.charAt(si-1)) {
                si++;
                scnt++;
            }
            if (scnt != wcnt && scnt < 3) return false;
            if (scnt < wcnt) return false;
        }
        if (si != s.length() || wi != word.length()) return false;
        return true;
    }
}