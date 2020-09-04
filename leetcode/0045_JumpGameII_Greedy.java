class Solution {
    public int jump(int[] nums) {
        int curend = 0, furthest = 0;
        int jump = 0;
        for (int i = 0; i < nums.length-1; i++) {
            furthest = Math.max(furthest, i + nums[i]);
            if (i == curend) {
                jump++;
                curend= furthest;
            }
        }
        
        return jump;
    }

}