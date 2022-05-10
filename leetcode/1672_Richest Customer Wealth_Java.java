/**
Basic Method */
class Solution {
    public int maximumWealth(int[][] accounts) {
        int max = 0;
        for (int i = 0; i < accounts.length; i++) {
            max = Math.max(max, getSum(accounts[i]));
        }
        return max;
    }
    
    private int getSum(int[] nums) {
        int sum = 0;
        for (int n : nums) {
            sum += n;
        }
        return sum;
    }
}

/**
Java 8 Method - Arrays.stream()*/
class Solution {
    public int maximumWealth(int[][] accounts) {
        return Arrays.stream(accounts)
            .mapToInt(i -> Arrays.stream(i).sum())
            .max().getAsInt();   
    }
    
}