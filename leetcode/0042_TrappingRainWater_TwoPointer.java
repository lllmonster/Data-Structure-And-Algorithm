class Solution {
    public int trap(int[] height) {
        int l = 0, r = height.length-1;
        int cnt = 0;
        int lmax = 0, rmax = 0;
        
        while(l < r) {
            if (height[l] < height[r]) {
                lmax = Math.max(lmax, height[l]);
                cnt += lmax - height[l];
                l++;
            } else {
                rmax = Math.max(rmax, height[r]);
                cnt += rmax - height[r];
                r--;
            }
        }
        
        return cnt;
    }
}