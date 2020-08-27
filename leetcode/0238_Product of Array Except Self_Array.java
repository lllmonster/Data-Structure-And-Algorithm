class Solution {
    /**
    Two method without division, left * right
    1. O(n), O(n)
    2. O(n), O(1)
     */
    public int[] productExceptSelf(int[] nums) {
        return constantSpace(nums);
        // return oNSpace(nums);
    }
    
    private int[] oNSpace(int[] nums) {
        int len = nums.length;
        int[] L = new int[len];
        int[] R = new int[len];
        
        L[0] = 1;
        for (int i = 1; i < len; i++) {
            L[i] = nums[i-1] * L[i-1];
        }

        R[len-1] = 1;
        for (int i = len-2; i >= 0; i--) {
            R[i] = R[i+1] * nums[i+1];
        }

        int[] output = new int[len];
        for (int i = 0; i < len; i++) {
            output[i] = L[i] * R[i];
        }
        
        return output;
    }
    
    
    private int[] constantSpace(int[] nums) {
        int len = nums.length;
        int[] ans = new int[len];
        ans[0] = 1;
        for (int i = 1; i < len; i++) {
            ans[i] = ans[i-1] * nums[i-1];
        }
        
        int R = 1;
        for (int i = len-1; i >= 0; i--) {
            ans[i] = ans[i] * R;
            R = R * nums[i];
        }
        return ans;
    }
}