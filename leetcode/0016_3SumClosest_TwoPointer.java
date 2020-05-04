class Solution {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int res = Integer.MAX_VALUE;
        int sol = 0;
        for (int i = 0; i < nums.length -1; i++) {
            if (i == 0 || nums[i] != nums[i-1]) {
                int l = i+1, r = nums.length -1, sum = target - nums[i];
                while (l < r) {
                    if (Math.abs(nums[l]+nums[r]-sum) < res) {
                        res = Math.abs(nums[l]+nums[r]-sum);
                        sol = nums[i]+nums[l]+nums[r];
                    } 
                    if (nums[l]+nums[r] > sum) {
                        r--;
                    } else {
                        l++;
                    }
                    
                }
            }
        }
        return sol;
    }
}