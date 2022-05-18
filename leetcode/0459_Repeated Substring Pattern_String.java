/**
Super smart */
class Solution {
    public boolean repeatedSubstringPattern(String s) {
        String str = s + s;
        return str.substring(1,str.length()-1).contains(s);
    }
}

/**
Normal solu - still good */
class Solution {
    public boolean repeatedSubstringPattern(String s) {
        int l = s.length();
        for (int i=l/2; i>=1; i--) {
            if (l % i == 0) {
                int m = l/i;
                String sub = s.substring(0,i);
                StringBuilder sb = new StringBuilder();
                for (int k = 0; k < m; k++) {
                    sb.append(sub);
                }
                if (s.equals(sb.toString())) return true;
            }
        }
        return false;
    }
}