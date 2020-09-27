class Solution {
    /**
    Method 1: backTracking
    Method 2: FIFO queue
     */
    private String[] KEYS = {"0","1","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
    
    public List<String> letterCombinations(String digits) {
       // return usingRecursive(digits);
        return usingFIFOQueue(digits);
    }
    
    private List<String> usingFIFOQueue(String digits) {
        LinkedList<String> ans = new LinkedList<>();
        if (digits == null || digits.length() == 0) return ans;
        ans.add("");
        for(int i = 0; i < digits.length(); i++) {
            while(ans.peek().length() == i) {
                String t = ans.remove();
                String str = KEYS[Character.getNumericValue(digits.charAt(i))];
                for(Character c : letter.toCharArray()) {
                    ans.add(t+c);
                }
            }
        }
        return ans;
    }
    
    private List<String> usingRecursive(String digits) {
        List<String> ans = new ArrayList<>();
        if(digits == null || digits.length() == 0) return ans;
        combination("", 0, digits, ans);
        return ans;
    }
    
    private void combination(String prefix, int offset, String digits, List<String> ans) {
        if (offset >= digits.length()) {
            ans.add(prefix);
            return;
        }
        
        String letter = KEYS[Character.getNumericValue(digits.charAt(offset))];
        for (int i = 0; i < letter.length(); i++) {
            combination(prefix+letter.charAt(i), offset+1, digits, ans);
        }
    }
}