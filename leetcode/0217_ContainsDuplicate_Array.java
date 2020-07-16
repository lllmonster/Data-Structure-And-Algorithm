class Solution {
    /**
    sort O(nlogn) O(1)
    hashset O(n) O(n)
     */
    public boolean containsDuplicate(int[] nums) {
        //return sort(nums);
        return hashset(nums);
    }
    
    private boolean sort(int[] nums) {
        Arrays.sort(nums);
        for(int i = 0; i < nums.length-1; i++) {
            if (nums[i] == nums[i+1]) {
                return true;
            }
        }
        return false;
    }
    
    private boolean hashset(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int i : nums) {
            if (set.contains(i)) return true;
            set.add(i);
        }
        return false;
    }
}