class Solution {
    /**
    Using map, optimization, O(n), O(min(m,n))
     */
    public int lengthOfLongestSubstring(String s) {
        char[] c = s.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        int len = 0;
        for (int i = 0, j = 0 ; j < c.length; j++) {
            if (map.containsKey(c[j])){
                i = Math.max(i,map.get(c[j])+1);
            }
            map.put(c[j],j);
            len = Math.max(len, j-i+1);
        }
        return len;
    }

    /**
    Using Set, straightforward, O(2n), O(min(m,n))
     */
    private int slidingWindowUsingSet(String s) {
        int len = 0, i = 0, j = 0;
        Set<Character> set = new HashSet<>();
        while (i < s.length() && j < s.length()) {
            if (!set.contains(s.charAt(j))) {
                set.add(s.charAt(j++));
                len = Math.max(len, j - i);
            } else {
                set.remove(s.charAt(i++));
            }
        }
        return len;
    }
}
