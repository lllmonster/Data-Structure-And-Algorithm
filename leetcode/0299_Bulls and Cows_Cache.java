/**
One Pass
 */
class Solution {
    public String getHint(String secret, String guess) {
        int[] cnt = new int[10];
        int bull = 0, cow = 0;
        
        for (int i = 0 ; i < secret.length(); i++) {
            int sId = Character.getNumericValue(secret.charAt(i));
            int gId = Character.getNumericValue(guess.charAt(i));
            if (sId == gId) {
                bull++;
            } else {
                if (cnt[sId] < 0) {cow++;}
                if (cnt[gId] > 0) {cow++;}
                cnt[sId]++;
                cnt[gId]--;
            }
        }
        
        return bull+"A"+cow+"B";
    }
}

/**
Two Pass
 */

class Solution {
    public String getHint(String secret, String guess) {
        int[] s = new int[10];
        int[] g = new int[10];
        int bull = 0, cow = 0;
        
        for (int i = 0 ; i < secret.length(); i++) {
            int sId = Character.getNumericValue(secret.charAt(i));
            int gId = Character.getNumericValue(guess.charAt(i));
            if (sId == gId) {
                bull++;
            } else {
                s[sId]++;
                g[gId]++;
            }
        }
        
        for (int i = 0; i < 10; i++) {
            cow = cow + Math.min(s[i], g[i]);
        }
        
        return bull+"A"+cow+"B";
    }
}