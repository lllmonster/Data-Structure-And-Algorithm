class Solution {
    /**
    Stack: first in last out.
     */
    public int calculate(String s) {
        if (s == null || s.length() == 0) return 0;
        Stack<Integer> stack = new Stack();
        int num = 0;
        char sign = '+';
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                num = s.charAt(i) - '0' + num * 10;
            } 
            if ((s.charAt(i) <'0' || s.charAt(i) >'9') && s.charAt(i) != ' ' || i == s.length()-1 ){
                if (sign == '+') {
                    stack.push(num);
                } else if (sign == '-') {
                    stack.push(-num);
                } else if (sign == '*') {
                    stack.push(stack.pop() * num);
                } else if (sign == '/') {
                    stack.push(stack.pop() / num);
                }
                sign = s.charAt(i);
                num = 0;
            }
        }
        int sum = 0;
        while (!stack.isEmpty()) {
            sum += stack.pop();
        }     
        
        return sum;
    }
}