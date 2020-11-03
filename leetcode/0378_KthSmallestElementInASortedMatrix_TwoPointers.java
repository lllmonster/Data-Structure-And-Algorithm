class Solution {
    /**
    Two Pointer
     */
    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        int lo = matrix[0][0], hi = matrix[n-1][n-1];
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int count = getLessCount(matrix, mid);
            if (count < k) lo = mid + 1;
            else hi = mid - 1;
        }
        return lo;
    }
    
    private int getLessCount(int[][] matrix, int mid) {
        int n = matrix.length;
        int i = n - 1, j = 0;
        int res = 0;
        while (i >= 0 && j < n) {
            if (matrix[i][j] > mid) i--;
            else {
                res = res + i + 1;
                j++;
            }
        }
        return res;
    }
}