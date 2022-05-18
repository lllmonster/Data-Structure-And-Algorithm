/**
Sort o(nlogn),o(n)*/
class Solution {
    public char findTheDifference(String s, String t) {
        char[] sl = s.toCharArray();
        char[] tl = t.toCharArray();
        Arrays.sort(sl);
        Arrays.sort(tl);
        for (int i = 0; i < sl.length; i++) {
            if (sl[i] != tl[i]) return tl[i];
        }
        return tl[sl.length];
    }
}

/**
HashMap o(n),o(1) */
class Solution {
    public char findTheDifference(String s, String t) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0)+1);
        }
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            if (!map.containsKey(c)) return c;
            if (map.get(c) == 0) return c;
            map.put(c, map.get(c)-1);
        }
        return '0';
    }
}

/**
Bit manipulation - XOR - o(n),o(1)
0^0=0
0^1=1
1^0=1
1^1=1*/
class Solution {
    public char findTheDifference(String s, String t) {
        char diff = 0;
        for (char c : s.toCharArray()) {
            diff ^= c;
        }
        for (char c : t.toCharArray()) {
            diff ^= c;
        }
        return diff;
    }
}
