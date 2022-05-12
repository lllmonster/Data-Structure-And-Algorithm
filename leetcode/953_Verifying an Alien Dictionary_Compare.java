/**
my own solution */
class Solution {
    public boolean isAlienSorted(String[] words, String order) {
        if (words.length == 1) return true;
        boolean isSorted = true;
        for (int i = 0 ; i < words.length-1; i++) {
            String a = words[i];
            String b = words[i+1];
            boolean allEqual = true;
            for (int j = 0; j < a.length() && j < b.length(); j++) {
                if (a.charAt(j) == b.charAt(j)) {
                    continue;
                }  
                allEqual = false;
                if (! checkOrder(a.charAt(j), b.charAt(j), order)) {
                    return false;
                } else {
                    isSorted &= true;
                    break;
                }
            }
            if (allEqual && a.length() > b.length()) return false;
        }
        return isSorted;
    }
    
    private boolean checkOrder(char a, char b, String order) {
        return order.indexOf(a) < order.indexOf(b);
    }
    
    /**
    Optimal, can use map as well
    here I use string.indexOf instead of map. */
    class Solution {
    public boolean isAlienSorted(String[] words, String order) {
        for (int i = 0 ; i < words.length-1; i++) {
            if (!checkOrder(words[i],words[i+1], order)) {
                return false;
            }
        }
        return true;
    }
    
    private boolean checkOrder(String a, String b, String order) {
        int n = a.length(), m = b.length();
        for (int i = 0; i < n && i < m; ++i)
            if (a.charAt(i) != b.charAt(i))
                return order.indexOf(a.charAt(i)) < order.indexOf(b.charAt(i));
        return n <= m;
    }
}
}