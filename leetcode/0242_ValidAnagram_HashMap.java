class Solution {
    public boolean isAnagram(String s, String t) {
        // set or map or array, O(n), O(1)
        int[] freq = new int[26];
        for (int i = 0; i < s.length(); i++) {
            freq[s.charAt(i)-'a']++;
        }
        for (int i = 0; i < t.length(); i++) {
            freq[t.charAt(i)-'a']--;
            if (freq[t.charAt(i)-'a'] < 0) return false;
        }
        for (int fre: freq) {
            if (fre != 0) return false;
        }
        return true;
    }
}