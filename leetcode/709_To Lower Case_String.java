/**
Library */
class Solution {
    public String toLowerCase(String s) {
        return s.toLowerCase();   
    }
}

/**
Without library */
class Solution {
    public String toLowerCase(String s) {
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] >= 'A' && chars[i] <= 'Z') {
                chars[i] = (char) (chars[i] - 'A' + 'a');   
            }
        }
        return new String(chars);
    }
}