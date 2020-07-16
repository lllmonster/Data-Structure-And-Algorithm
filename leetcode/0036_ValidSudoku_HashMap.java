class Solution {
    /**
    One Set is more straightforward, store string for different situation, row/col/cube.
    MultiSet is more efficiency, store Character instead of String.
     */
    public boolean isValidSudoku(char[][] board) {
        return oneSet(board);
        //return multiSet(board);
    }
    
    private boolean oneSet(char[][] board) {
        Set<String> set = new HashSet<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] == '.') continue;
                if (!set.add("row_"+i+"_"+board[i][j]) ||
                    !set.add("col_"+j+"_"+board[i][j]) ||
                    !set.add("cube_"+i/3+"_"+j/3+"_"+board[i][j])) {
                    return false;
                }
            }
        }
        return true;
    }
    
    private boolean multiSet(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            Set<Character> rows = new HashSet<>();
            Set<Character> cols = new HashSet<>();
            Set<Character> cube = new HashSet<>();
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] != '.' && !rows.add(board[i][j]))
                    return false;
                if (board[j][i] != '.' && !cols.add(board[j][i]))
                    return false;
                int rowId = 3*(i/3);
                int colId = 3*(i%3);
                if (board[rowId+j/3][colId+j%3] != '.' &&
                    !cube.add(board[rowId+j/3][colId+j%3]))
                    return false;
            }
        }
        return true;
    }
}