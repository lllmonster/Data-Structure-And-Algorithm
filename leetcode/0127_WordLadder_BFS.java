class Solution {
    /**
    Method 1: bfs
        A dict to see if newWord exists. A vist to see if newWord is used before to avoid dupilcate. 
        A queue to store all path we went through.
        For each word, iterate each character from 'a' to 'z' to create new word. 
        For each newWord, if it exists in dict and hasn't been visited, put it into queue, len++.
        Time complexity: O(n*26^l); n is len of wordlist, l is len of word.
        Space complexity: O(n)
    Method 2: bi-directional bfs
        The idea behind bidirectional search is to run two simultaneous searches—one forward from the initial state and the other backward from the goal—hoping that the two searches meet in the middle. The motivation is that b^(d/2) + b^(d/2) is much less than b^d. b is branch factor, d is depth.
        Time complexity: O(n*26^(l/2)); n is len of wordlist, l is len of word.
        Space complexity: O(n)
    */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        //return bfs(beginWord, endWord, wordList);
        return biBfs(beginWord, endWord, wordList);
    }
    
    private int bfs(String beginWord, String endWord, List<String> wordList) {
        Set<String> dict = new HashSet<>(wordList);
        Set<String> vist = new HashSet<>();
        Queue<String> q = new LinkedList<>();
        q.offer(beginWord);
        int len = 0;
        while (!q.isEmpty()) {
            len++;
            int size = q.size();
            while (size > 0) {
                size--;
                String word = q.poll();
                if (word.equals(endWord)) return len;
                
                for (int j = 0; j < word.length(); j++) {
                    char[] chars = word.toCharArray();
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (chars[j] == c) continue;
                        chars[j] = c;
                        String newWord = String.valueOf(chars);
                        if (dict.contains(newWord) && vist.add(newWord)) {
                            q.offer(newWord);
                        }
                    }
                }
            }
        }
        
        return 0;
    }
    
    private int biBfs(String beginWord, String endWord, List<String> wordList) {
        // check if end word exists in word list
        if(!wordList.contains(endWord)) return 0;
        // initialize
        Set<String> dict = new HashSet<>(wordList);
        Set<String> vist = new HashSet<>();
        Set<String> beginSet = new HashSet<>();
        Set<String> endSet = new HashSet<>();
        beginSet.add(beginWord);
        endSet.add(endWord);
        int len = 0;
        while(!beginSet.isEmpty() && !endSet.isEmpty()) {
            len++;
            
            // swap beginSet and endSet in turn
            if (beginSet.size() > endSet.size()) {
                Set<String> tmp = beginSet;
                beginSet = endSet;
                endSet = tmp;
            }
            
            Set<String> tmp = new HashSet<>();
            for (String word : beginSet) {
                for (int i = 0; i < word.length(); i++) {
                    char[] chars = word.toCharArray();
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (chars[i] == c) continue;
                        chars[i] = c;
                        String newWord = String.valueOf(chars);
                        
                        if (endSet.contains(newWord)) return len+1;
                        
                        if(dict.contains(newWord) && vist.add(newWord)) {
                            tmp.add(newWord);
                        }
                    }
                }
                
                beginSet = tmp;
            }
        }
        
        return 0;
    }
}