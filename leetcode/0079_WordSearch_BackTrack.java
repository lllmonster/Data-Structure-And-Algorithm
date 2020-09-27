class Solution {
    public boolean exist(char[][] board, String word) {
        for (int i = 0; i < board.length; i++) {
            for (int j =0; j < board[0].length; j++) {
                boolean[][] visit = new boolean[board.length][board[0].length];
                if(backtrack(i, j, visit, 0, board, word)) {
                    return true;
                }
            }
        }
        return false;
        
    }
    
    private boolean backtrack(int i, int j, boolean[][] visit, int offset, char[][] board, String word) {
        if (offset == word.length()) return true;
        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length || visit[i][j]) return false;
        if (board[i][j] != word.charAt(offset)) return false;
        visit[i][j] = true;
        boolean res = backtrack(i+1, j, visit, offset+1, board, word) ||
                    backtrack(i-1, j, visit, offset+1, board, word) ||
                    backtrack(i, j+1, visit, offset+1, board, word) ||
                    backtrack(i, j-1, visit, offset+1, board, word);
        visit[i][j] = false;
        return res;
    }
}