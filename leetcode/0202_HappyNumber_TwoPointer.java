/**
Slow n Fast, o(logn),o(1)
 */
class Solution {
    public boolean isHappy(int n) {
        int slow = happy(n), fast = happy(slow);
        while (slow != fast) {
            slow = happy(slow);
            fast = happy(happy(fast));
        }
        return slow == 1;
    }
    
    private int happy(int n) {
        int sum = 0;
        while (n > 0) {
            sum += (n % 10)*(n % 10);
            n = n / 10;
        }
        return sum;
    }
}

/**
HashSet, o(logn),o(logn)
 */
class Solution {
    public boolean isHappy(int n) {
        Set<Integer> set = new HashSet<Integer>();
        while (set.add(n)) {
            n = happy(n);
        }
        return n == 1;
    }
    
    private int happy(int n) {
        int sum = 0;
        while (n > 0) {
            sum += (n % 10)*(n % 10);
            n = n / 10;
        }
        return sum;
    }
}