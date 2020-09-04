class Solution {
    public String convert(String s, int numRows) {
        StringBuilder[] res = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) res[i] = new StringBuilder();
        char[] chars = s.toCharArray();
        int num = 0;
        while (num < s.length()) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0 ; i < numRows && num < s.length(); i++) {
                res[i].append(chars[num++]);
            }
            for (int i = numRows-2; i > 0 && num < s.length(); i--) {
                res[i].append(chars[num++]);
            }
        }
        for (int i = 1; i < numRows; i++) {
            res[0].append(res[i]);
        }
        return res[0].toString();
    }
}