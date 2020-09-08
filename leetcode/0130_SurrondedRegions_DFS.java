class Solution {
    /**
    Idea: 
        First, turn all board and its neighbor from 'O' to '1'
        Second, turn all 'O' to 'X'
        Third, turn all '1' to 'O'
    */
    public void solve(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if ((i == 0 || j == 0 || i == board.length-1 || j == board[0].length-1) && board[i][j] == 'O') {
                    dfs(board, i, j);
                }
            }
        }
        
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
                if (board[i][j] == '1') {
                    board[i][j] = 'O';
                }
            }
        }
        
    }
    
    private void dfs(char[][] board, int i, int j) {
        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length || board[i][j] == 'X' || board[i][j] == '1')
            return;
        if (board[i][j] == 'O') board[i][j] = '1';
        dfs(board, i-1,j);
        dfs(board, i+1,j);
        dfs(board, i,j-1);
        dfs(board, i,j+1);
    }
}