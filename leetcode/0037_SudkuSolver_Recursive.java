class Solution {
    public void solveSudoku(char[][] board) {
        if (board == null || board.length == 0) return;
        solve(board);
    }
    
    private boolean solve(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] != '.') continue;
                for (char c  = '1'; c <= '9'; c++) {
                    if(isValid2(board, i, j, c)) {
                        board[i][j] = c;
                        if (solve(board)) {
                            return true;
                        } else {
                            board[i][j] = '.';
                        }
                    } 
                }
                return false;
            }
        }
        return true;
    }
    
    private boolean isValid(char[][] board, int m, int n, char c) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (i == m && board[i][j] == c) return false;
                if (j == n && board[i][j] == c) return false;
                int id = m/3, jd = n/3;
                if (i >= 3*id && i <= 3*id+2 &&
                    j >= 3*jd && j <= 3*jd+2 &&
                   board[i][j] == c) return false;
            }
        }
        return true;
    }
    
    private boolean isValid2(char[][] board, int m, int n, char c) {
        for (int i = 0; i < board.length; i++) {
            if(board[m][i] == c) return false;
            if(board[i][n] == c) return false;
            if(board[3*(m/3)+i/3][3*(n/3)+i%3] == c) return false;
        }
        return true;
    }
}