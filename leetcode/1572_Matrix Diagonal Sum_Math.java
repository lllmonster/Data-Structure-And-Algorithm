/**
Optimal */
class Solution {
    public int diagonalSum(int[][] mat) {
        int sum = 0;
        int n = mat.length;
        
        for (int i = 0; i < n; i++) {
            sum += mat[i][i];
            sum += mat[i][n-1-i];
        }
        
        if (n % 2 != 0) {
            sum -= mat[n/2][n/2];
        }
        
        return sum;
    }
}

/**
My own solution */
class Solution {
    public int diagonalSum(int[][] mat) {
        if (mat.length == 1) return mat[0][0];
        if (mat.length == 2) return mat[0][1]+mat[0][0]+mat[1][0]+mat[1][1];
        int sum = 0;
        int n = mat.length-1;
        sum += mat[0][0];
        sum += mat[0][n];
        sum += mat[n][0];
        sum += mat[n][n];
        
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < n; j++) {
                if (i == j || i == n - j) {
                    sum += mat[i][j];
                }
            }
        }
        
        return sum;
    }
}