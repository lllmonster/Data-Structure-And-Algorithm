class Solution {
    /**
    M1: Two Pointer O(n),O(1). Optimized method
    M2: Stack O(n),O(n). Easy to think
     */
    public boolean backspaceCompare(String S, String T) {
        //return usingStack(S, T);
        return twoPointers(S, T);
    }
    
    private boolean twoPointers(String s, String t) {
        int i = s.length() - 1, j = t.length() - 1, back;
        while (true) {
            back = 0;
            while (i >= 0 && (s.charAt(i) == '#' || back > 0)) {
                back += s.charAt(i) == '#' ? 1 : -1;
                i--;
            }
            back = 0;
            while (j >= 0 && (t.charAt(j) == '#' || back > 0)) {
                back += t.charAt(j) == '#' ? 1 : -1;
                j--;
            }
            
            if (i >= 0 && j >=0 && s.charAt(i) == t.charAt(j)) {
                i--;
                j--;
            } else {
                break;
            }
            
        }
        return i == -1 && j == -1;
    }
    
    private boolean usingStack(String S, String T) {
        return clean(S).equals(clean(T));
    }
    
    private String clean(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (stack.isEmpty() && c == '#') continue;
            if (c == '#') stack.pop();
            else stack.push(c);
        }
        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.toString();
    }
}