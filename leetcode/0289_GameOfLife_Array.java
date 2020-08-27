class Solution {
    /** 
    Method 1 : o(mn), o(mn), count liveNeighbors for each cell., and decide it based on 4 rules.
    Method 2 : o(mn), o(1), use -1/2 to represent the updated status (live/dead)
    */
    public void gameOfLife(int[][] board) {
        //omnSpace(board);
        o1Space(board);
    }
    
    private void o1Space(int[][] board) {
        int m = board.length;
        int n = board[0].length;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                
                int liveNeighbors = 0;
                for (int r = -1; r < 2; r++) {
                    for (int c = -1; c < 2; c++) {
                        if ((i+r)<0 || (i+r)>=m || (j+c)<0 || (j+c)>=n) {
                            continue;
                        }
                        
                        if ((board[i+r][j+c] == 1 || board[i+r][j+c] == -1) && !(r == 0 && c == 0)) {
                            liveNeighbors++;
                        }
                    }
                }
                
                //Rule 1
                if (board[i][j] == 1 && liveNeighbors < 2) board[i][j] = -1;
                //Rule 3
                if (board[i][j] == 1 && liveNeighbors > 3) board[i][j] = -1;
                //Rule 4
                if (board[i][j] == 0 && liveNeighbors == 3) board[i][j] = 2;
            }
        }
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] > 0) board[i][j] = 1;
                else board[i][j] = 0;
            }
        }
        
        return;
    }
    
    private void omnSpace(int[][] board) {
        int m = board.length;
        int n = board[0].length;
        
        int[][] copyBoard = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                copyBoard[i][j] = board[i][j];
            }
        }
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                
                int liveNeighbors = 0;
                for (int r = -1; r < 2; r++) {
                    for (int c = -1; c < 2; c++) {
                        if ((i+r)<0 || (i+r)>=m || (j+c)<0 || (j+c)>=n) {
                            continue;
                        }
                        
                        if (copyBoard[i+r][j+c] == 1 && !(r == 0 && c == 0)) {
                            liveNeighbors++;
                        }
                    }
                }
                
                //Rule 1
                if (copyBoard[i][j] == 1 && liveNeighbors < 2) board[i][j] = 0;
                //Rule 3
                if (copyBoard[i][j] == 1 && liveNeighbors > 3) board[i][j] = 0;
                //Rule 4
                if (copyBoard[i][j] == 0 && liveNeighbors == 3) board[i][j] = 1;
            }
        }
        
        return;
    }
}