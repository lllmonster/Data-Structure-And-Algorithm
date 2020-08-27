class Solution {
    /**
    Using HashMap, o(n^2), o(n^2)
     */
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        Map<Integer, Integer> map = new HashMap<>();
        int ans = 0;
        
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B.length; j++) {
                int target = -(A[i] + B[j]);
                map.put(target, map.getOrDefault(target, 0) + 1);
            }
        }
        
        for (int i = 0; i < C.length; i++) {
            for (int j = 0; j < D.length; j++) {
                ans += map.getOrDefault((C[i]+D[j]),0);
            }
        }
        
        return ans;
    }
}