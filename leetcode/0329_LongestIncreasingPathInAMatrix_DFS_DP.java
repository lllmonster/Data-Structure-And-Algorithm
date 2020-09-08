class Solution {
    /**
    DFS+DP
    if u only use dfs (pure dfs), it will OOM. O(mn*4^(m+n))
    plus DP (optimization with memorization), because there's a lot of revisited cells. O(mn*mn)
    
     */
    public int longestIncreasingPath(int[][] matrix) {
        if (matrix.length == 0) return 0;
        int[][] cache = new int[matrix.length][matrix[0].length];
        int ans = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                //int len = dfs(matrix, i, j, cache);
                int len = elegant_dfs(matrix, i, j, cache);
                ans = Math.max(ans, len);
            }
        }
        return ans;
    }
    
    private static final int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    private int elegant_dfs(int[][] matrix, int i,int j, int[][] cache) {
        if (cache[i][j] != 0) return cache[i][j];
        int max = 1;
        for (int[] dir : dirs) {
            int x = i + dir[0];
            int y = j + dir[1];
            if (x < 0 || y < 0 || x >= matrix.length || y >= matrix[0].length || matrix[x][y] <= matrix[i][j]) {
                continue;
            }
            int len = 1+elegant_dfs(matrix, x, y, cache);
            max = Math.max(max, len);
        }
        cache[i][j] = max;
        return max;
    }
    
    private int dfs(int[][] matrix, int i,int j, int[][] cache) {
        if (i < 0 || j < 0 || i >= matrix.length || j >= matrix[0].length) {
            return 0;
        }
        if (cache[i][j] != 0) return cache[i][j];
        int max = 1;
        int len = 0;
        if (i-1>=0 && matrix[i-1][j] > matrix[i][j]) {
            len = 1 + dfs(matrix, i-1, j, cache);
            max = Math.max(max, len);
        }
        if (i+1<matrix.length && matrix[i+1][j] > matrix[i][j]) {
            len = 1 + dfs(matrix, i+1, j, cache);
            max = Math.max(max, len);
        }
        if (j-1>=0 && matrix[i][j-1] > matrix[i][j]) {
            len = 1 + dfs(matrix, i, j-1, cache);
            max = Math.max(max, len);
        }
        if (j+1<matrix[0].length && matrix[i][j+1] > matrix[i][j]) {
            len = 1 + dfs(matrix, i, j+1, cache);
            max = Math.max(max, len);
        }
        cache[i][j] = max;
        return max;
    }
}