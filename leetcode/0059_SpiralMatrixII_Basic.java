class Solution {
    public int[][] generateMatrix(int n) {
        if (n == 0) return new int[0][0];
        int[][] ans = new int[n][n];
        int len = 1;
        int r1 = 0, c1 = 0, r2 = n-1, c2 = n-1;
        while (r1 < r2 && c1 < c2) {
            for (int i = c1; i < c2; i++) {ans[r1][i] = len++;}
            for (int i = r1; i < r2; i++) {ans[i][c2] = len++;}
            for (int i = c2; i > c1; i--) {ans[r2][i] = len++;}
            for (int i = r2; i > r1; i--) {ans[i][c1] = len++;}
            r1++;r2--;
            c1++;c2--;
        }
        if (ans[r1][c1] == 0) ans[r1][c1] = len;
        return ans;
    }
}