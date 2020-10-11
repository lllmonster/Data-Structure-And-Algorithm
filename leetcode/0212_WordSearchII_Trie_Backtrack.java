class Solution {
    /**
    Trie + Backtrack is better
    if use pure dfs, will throw TimeLimitException.
     */
    public List<String> findWords(char[][] board, String[] words) {
        List<String> ans = new ArrayList<>();
        TrieNode root = buildTrie(words);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                backtrack(board, i, j, root, ans);
            }
        }
        return ans;
    }
    
    private void backtrack(char[][] board, int i, int j, TrieNode root, List<String> ans) {
        if (root == null) return;
        if (root.word != null) {
            ans.add(root.word);
            root.word = null;
            return;
        }
        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length || board[i][j] == '#') {
            return;
        } 
        Character c = board[i][j];
        board[i][j] = '#';
        backtrack(board, i+1, j, root.next[c-'a'], ans);
        backtrack(board, i-1, j, root.next[c-'a'], ans);
        backtrack(board, i, j+1, root.next[c-'a'], ans);
        backtrack(board, i, j-1, root.next[c-'a'], ans);
        board[i][j] = c;
    }
    
    private TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();
        for (String word : words) {
            TrieNode cur = root;
            for (Character c : word.toCharArray()) {
                int i = c - 'a';
                if (cur.next[i] == null) cur.next[i] = new TrieNode();
                cur = cur.next[i];
            }
            cur.word = word;
        }
        return root;
    }
    
    class TrieNode{
        String word;
        TrieNode[] next = new TrieNode[26];
    }
}