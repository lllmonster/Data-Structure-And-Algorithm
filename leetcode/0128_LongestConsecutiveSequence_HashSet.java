class Solution {
    /**
    Method 1: Sort o(nlogn), o(1)
    Method 2: HashSet, look up is o(1), so o(n), o(n)
              The tricky part is to look up from one side instead of looking up from the middle to both sides.
              Otherwise, it will throw TimeOutException
     */
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        
        int len = 0;
        for (int num : nums) {
            if (!set.contains(num-1)) {
                int high = num;

                while (set.contains(high+1)) {
                    high++;
                }
                len = Math.max(len, high - num + 1);
            }
        }
        
        return len;
    }
}