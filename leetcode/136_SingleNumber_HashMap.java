class Solution {
    /**
    * There are two methods.
    * One is using HashMap, o(n), o(n)
    * One is using bit operation, o(n), o(1)
    */
    public int singleNumber(int[] nums) {
        
        //return usingHashMap(nums);
        return usingBitOp(nums);
    }
    
    private int usingHashMap(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        
        for (int num : nums) {
            if (map.get(num) == 1)
                return num;
        }
        
        return 0;
    }
    
    private int usingBitOp(int[] nums) {
        int a = 0;
        for (int num : nums) {
            a ^= num;
        }
        return a;
    }
}