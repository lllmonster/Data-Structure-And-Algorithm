class Solution {
    public int countOdds(int low, int high) {
        return math(low, high);
    }
    
    /**
    the count of odd numbers between 1 and low - 1 is low / 2
    the count of odd numbers between 1 and high is (high + 1 ) / 2
    **/
    private int math(int low, int high) {
        return (high+1)/2 - low/2;   
    }
    
    private int my_own_way(int low, int high) {
        if ((high-low)%2 == 0) {
            if (high % 2 == 1) return (high-low)/2+1;
            else return (high-low)/2;
        }
        return (high-low+1)/2;
    }
}