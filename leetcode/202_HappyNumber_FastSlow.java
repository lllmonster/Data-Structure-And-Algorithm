class Solution {
    /**
    * Two methods
    * 1. HashSet to store the past value. O(n) space
    * 2. Fast and slow method, O(1) space
    */
    public boolean isHappy(int n) {
        // return usingSet(n);
        return usingFastSlow(n);
    }
    
    private boolean usingFastSlow(int n) {
        int s = n;
        int f = getSum(n);
        while (s != f) {
            s = getSum(s);
            f = getSum(getSum(f));
        }
        return s==1;
    }
    
    private boolean usingSet(int n) {
        HashSet<Integer> set = new HashSet<>();
        int sum = 0;
        while (!set.contains(sum)) {
            set.add(n);
            sum = getSum(n);
            n = sum;
        }
        return sum == 1;
    }
    
    private int getSum(int n) {
        int sum = 0;
        while (n/10 != 0) {
            sum += (n%10)*(n%10);
            n = n/10;
        }
        return sum+n*n;
    }
}