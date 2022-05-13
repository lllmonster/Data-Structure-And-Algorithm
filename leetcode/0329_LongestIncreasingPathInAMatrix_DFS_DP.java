/**
Pure DFS 
Will OOM - O(mn*4^(m+n)*/
class Solution {
    public int longestIncreasingPath(int[][] matrix) {
        int length = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                length = Math.max(length, dfs(matrix, i, j));
            }
        }
        return length;
    }
    
    private int dfs(int[][] matrix, int i, int j) {
        int m = matrix.length, n = matrix[0].length;
        int ans = 0;
        if (isValid(i-1,j,m,n) && matrix[i-1][j] > matrix[i][j]) {
            ans = Math.max(ans,dfs(matrix, i-1, j));
        }
        if (isValid(i+1,j,m,n) && matrix[i+1][j] > matrix[i][j]) {
            ans = Math.max(ans,dfs(matrix, i+1, j));
        }
        if (isValid(i,j-1,m,n) && matrix[i][j-1] > matrix[i][j]) {
            ans = Math.max(ans,dfs(matrix, i, j-1));
        }
        if (isValid(i,j+1,m,n) && matrix[i][j+1] > matrix[i][j]) {
            ans = Math.max(ans,dfs(matrix, i, j+1));
        }
        return ++ans;
        
    }
    
    private boolean isValid(int i, int j, int m, int n) {
        return !(i < 0 || j < 0 || i >= m || j >= n);
    }
}

/**
DFS + Cache - o(mn),o(mn)*/
class Solution {
    public int longestIncreasingPath(int[][] matrix) {
        int length = 0;
        int[][] cache = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                length = Math.max(length, dfs(matrix, i, j, cache));
            }
        }
        return length;
    }
    
    private int dfs(int[][] matrix, int i, int j, int[][] cache) {
        if (cache[i][j] != 0) return cache[i][j];
        int m = matrix.length, n = matrix[0].length;
        int ans = 0;
        if (isValid(i-1,j,m,n) && matrix[i-1][j] > matrix[i][j]) {
            ans = Math.max(ans,dfs(matrix, i-1, j, cache));
        }
        if (isValid(i+1,j,m,n) && matrix[i+1][j] > matrix[i][j]) {
            ans = Math.max(ans,dfs(matrix, i+1, j, cache));
        }
        if (isValid(i,j-1,m,n) && matrix[i][j-1] > matrix[i][j]) {
            ans = Math.max(ans,dfs(matrix, i, j-1, cache));
        }
        if (isValid(i,j+1,m,n) && matrix[i][j+1] > matrix[i][j]) {
            ans = Math.max(ans,dfs(matrix, i, j+1, cache));
        }
        ans++;
        cache[i][j] = ans;
        return ans;
        
    }
    
    private boolean isValid(int i, int j, int m, int n) {
        return !(i < 0 || j < 0 || i >= m || j >= n);
    }
}