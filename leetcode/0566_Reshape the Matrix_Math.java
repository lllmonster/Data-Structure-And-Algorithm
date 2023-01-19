/**
Optimal One */
class Solution {
    public int[][] matrixReshape(int[][] mat, int r, int c) {
        int m = mat.length;
        int n = mat[0].length;
        if (m * n != r * c) return mat;
        
        int[][] remat = new int[r][c];
        for (int i = 0; i < r*c; i++) {
            remat[i/c][i%c] = mat[i/n][i%n];
        }
        return remat;
    }
}

/**
my own solu */
class Solution {
    public int[][] matrixReshape(int[][] mat, int r, int c) {
        int m = mat.length;
        int n = mat[0].length;
        if (m * n != r * c) return mat;
        
        int[][] remat = new int[r][c];
        int x = 0, y = 0;
        int a = 0, b = 0;
        while (x < m && y < n && a < r && b < c) {
            remat[a][b] = mat[x][y];
            if (b != c-1) {
                b++;
            } else {
                b=0;
                a++;
            }
            
            if (y != n-1) {
                y++;
            } else {
                y=0;
                x++;
            }
        }
        return remat;
    }
}

/**
Another format */
class Solution {
    public int[][] matrixReshape(int[][] mat, int r, int c) {
        int m = mat.length;
        int n = mat[0].length;
        if (m * n != r * c) return mat;
        
        int[][] remat = new int[r][c];
        int x = 0, y = 0;
        for (int i = 0; i < m;i++) {
            for (int j = 0; j < n; j++) {
                remat[x][y] = mat[i][j];
                y++;
                if (y == c) {
                    y=0;
                    x++;
                }
            }
        }
        return remat;
    }
}

