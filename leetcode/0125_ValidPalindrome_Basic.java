class Solution {
    public boolean isPalindrome(String s) {
        if (s.length() == 0 || s.length() == 1) return true;
        int l = 0, r = s.length()-1;
        while (l < r) {
            if (!Character.isLetterOrDigit(s.charAt(l))) {
                l++;
                continue;
            }
            if (!Character.isLetterOrDigit(s.charAt(r))) {
                r--;
                continue;
            }
            if (Character.toLowerCase(s.charAt(l)) != Character.toLowerCase(s.charAt(r))) return false;
            l++;r--;
            
        }
        return true;
    }
}