class Solution {
    public String freqAlphabets(String s) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < s.length()) {
            if (i + 2 < s.length() && s.charAt(i+2) == '#') {
                sb.append(getLetter(s,i,i+2));
                i += 3;
            } else {
                sb.append(getLetter(s,i,i));
                i++;
            }
        }
        return sb.toString();
    }
    
    private char getLetter(String s, int l, int r) {
        int n = 0;
        if (l == r) {
            n = Character.getNumericValue(s.charAt(l));
        } else {
            n = Integer.valueOf(s.substring(l,r));            
        }
        
        return (char) ('a'+n-1);
    }
}