class Solution {
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
}