class Solution {
    /**
    Two Pointer + HashMap + counter.
     */
    public String minWindow(String s, String t) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : t.toCharArray()) {
            if (map.containsKey(c)) {
                map.put(c, map.get(c)+1);
            } else {
                map.put(c, 1);
            }
        }
        int len = Integer.MAX_VALUE, begin = 0, end = 0, head = 0;
        int counter = t.length();
        while (end < s.length()) {
            char c = s.charAt(end);
            end++;
            if (map.containsKey(c)) {
                map.put(c, map.get(c)-1);
                if (map.get(c) >= 0) {
                    counter--;
                }
            }
            while (counter == 0) {
                if ((end - begin) < len) {
                    len = end - begin;
                    head = begin;
                }
                char bc = s.charAt(begin);
                if (map.containsKey(bc)) {
                    map.put(bc, map.get(bc) + 1);
                    if (map.get(bc) > 0) {
                        counter++;
                    }
                }
                begin++;
            }
        }
        if (len == Integer.MAX_VALUE) {
            return "";
        }
        return s.substring(head, head + len);
    }
}