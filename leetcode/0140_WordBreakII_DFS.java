class Solution {
    /**
    DFS
        don't know why :(, ANYWAY :)
     */
    public List<String> wordBreak(String s, List<String> wordDict) {
        return dfs(s, wordDict, new HashMap<String, ArrayList<String>>());
    }
    
    private List<String> dfs(String s, List<String> wordDict, HashMap<String, ArrayList<String>> map) {
        if (map.containsKey(s)) 
            return map.get(s);
        
        ArrayList<String> res = new ArrayList<>();
        if (s.length() == 0) {
            res.add("");
            return res;
        }
        
        for (String word : wordDict) {
            if (s.startsWith(word)) {
                List<String> sublist = dfs(s.substring(word.length()), wordDict, map);
                for(String sub : sublist) {
                    res.add(word + (sub.length() == 0 ? "" : " ")+ sub);
                }
            }
        }
        
        map.put(s, res);
        return res;
        
    }
}