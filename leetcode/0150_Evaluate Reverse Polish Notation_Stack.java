/**
Ideal Solution - if else
 */

class Solution {
    public int evalRPN(String[] tokens) {
        Stack<Integer> s = new Stack<Integer>();
        int a = 0, b = 0;
        for (String token : tokens) {
            if (token.equals("+")) {
                b = s.pop();
                a = s.pop();
                s.push(a + b);
            } else if (token.equals("-")) {
                b = s.pop();
                a = s.pop();
                s.push(a - b);
            } else if (token.equals("*")) {
                b = s.pop();
                a = s.pop();
                s.push(a * b);
            } else if (token.equals("/")) {
                b = s.pop();
                a = s.pop();
                s.push(a / b);
            } else {
                s.push(Integer.parseInt(token));
            }
        }
        
        return s.pop();
    }
}

/**
Ideal Solution - switch default
 */
class Solution {
    public int evalRPN(String[] tokens) {
        Stack<Integer> s = new Stack<Integer>();
        int a = 0, b = 0;
        for (String token : tokens) {
            switch (token) {
                case "+":
                    b = s.pop();
                    a = s.pop();
                    s.push(a + b);
                    break;
                case "-":
                    b = s.pop();
                    a = s.pop();
                    s.push(a - b);
                    break;
                case "*":
                    b = s.pop();
                    a = s.pop();
                    s.push(a * b);
                    break;
                case "/":
                    b = s.pop();
                    a = s.pop();
                    s.push(a / b);
                    break;
                default:
                    s.push(Integer.parseInt(token));
                    
            }
        }
        
        return s.pop();
    }
}

/**
My Own Solution
 */

 class Solution {
    public int evalRPN(String[] tokens) {
        if (tokens.length == 1) return Integer.valueOf(tokens[0]);
        int res = 0;
        int right = 0;
        while (right < tokens.length) {
            if (!isOperator(tokens[right])) {
                right++;
            } else {
                int left = right-2;
                while (tokens[left].length() == 0) {
                    left--;
                }
                tokens[right] = String.valueOf(operate(tokens[right], tokens[left], tokens[right-1]));
                tokens[right-1] = "";
                tokens[left] = "";
                right++;
            }
        }
        return Integer.valueOf(tokens[tokens.length-1]);
    }
    
    private boolean isOperator(String s) {
        return (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/"));
    }
    
    private int operate(String s, String s1, String s2) {
        int a = Integer.valueOf(s1);
        int b = Integer.valueOf(s2);
        if (s.equals("+")) {
            return a+b;
        } else if (s.equals("-")) {
            return a-b;
        } else if (s.equals("*")) {
            return a*b;
        } else if (s.equals("/")) {
            return a/b;
        }
        return 0;
    }
}