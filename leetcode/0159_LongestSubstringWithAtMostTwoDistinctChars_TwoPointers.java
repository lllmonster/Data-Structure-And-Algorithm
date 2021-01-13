class Solution {
    /**
    Two Pointer.
    Similar with LC76. Minimum Window Substring  
    Common Solution 
    https://leetcode.com/problems/longest-substring-with-at-most-two-distinct-characters/discuss/49708/Sliding-Window-algorithm-template-to-solve-all-the-Leetcode-substring-search-problem.
     */
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int start = 0, end = 0;
        int cnt = 0;
        int len = 0;
        
        while (end < s.length()) {
            char c = s.charAt(end);
            map.put(c, map.getOrDefault(c, 0) + 1);
            if (map.get(c) == 1) cnt++;
            end++;
            
            while (cnt > 2) {
                char sc = s.charAt(start);
                map.put(sc, map.get(sc)-1);
                if (map.get(sc) == 0) cnt--;
                start++;
            }
            len = Math.max(len, end - start);
        }
        return len;
    }
}