class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> ans = new ArrayList<>();
        if (matrix == null || matrix.length == 0) return ans;
        int rs = 0, cs = 0;
        int re = matrix.length-1, ce = matrix[0].length-1;
        while (rs <= re && cs <= ce) {
            for (int j = cs; j <= ce; j++) {ans.add(matrix[rs][j]);}
            rs++;
            for (int i = rs; i <= re; i++) {ans.add(matrix[i][ce]);}
            ce--;
            if (rs <= re) {
                for (int j = ce; j>=cs; j--) {ans.add(matrix[re][j]);}
            }
            re--;
            if (cs <= ce) {
                for (int i = re; i >= rs; i--) {ans.add(matrix[i][cs]);}
            }
            cs++;
        }
        return ans;
    }
}

/* previous solution
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
*/