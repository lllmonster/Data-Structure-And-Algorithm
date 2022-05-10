/**
one more solution
 */
class Solution {
    public boolean areAlmostEqual(String s1, String s2) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                list.add(i);
            }
            if (list.size() > 2) return false;
        }
        
        if (list.size() == 0) {
            return true;
        } else if (list.size() == 1) {
            return false;
        } else {
            return (s1.charAt(list.get(0)) == s2.charAt(list.get(1))) &&
                (s2.charAt(list.get(0)) == s1.charAt(list.get(1)));
        }
        
    }
}


/**
My own solution - straightforward
 */
class Solution {
    public boolean areAlmostEqual(String s1, String s2) {
        char a = '0';
        char b = '0';
        int cnt = 0;
        
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                cnt++;
                if (cnt > 2) return false;
                if (cnt == 2) {
                    if ( a!=s2.charAt(i) || b!=s1.charAt(i) ) {
                        return false;
                    }
                } else {
                    a = s1.charAt(i);
                    b = s2.charAt(i);
                } 
            }
        }
        return cnt != 1;
    }
}