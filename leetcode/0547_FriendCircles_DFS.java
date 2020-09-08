class Solution {
    /**
    1 0 0 1
    0 1 1 0
    0 1 1 1
    1 0 1 1

    output : 1
    
    1 1 0
    1 1 0
    0 0 1
    
    output : 2
    
    At a glance, it's similar with Number of Islands. 
    Actually it's not. It's an Adjacent matrix problem.
    Interesting issue.
    */
    public int findCircleNum(int[][] M) {
        int len = 0;
        for (int i = 0; i < M.length; i++) {
            if (M[i][i] == 1) {
                len++;
                dfs(M, i);
            }
        }
        return len;
    }
    
    private void dfs(int[][] M, int i) {
        for (int j = 0; j < M.length; j++) {
            if (M[i][j] == 1) {
                M[i][j] = M[j][i] = 0;
                dfs(M,j);
            }
        }
    }
}