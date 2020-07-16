class Solution {
    /**
    HashSet O(n) O(n)
    Math 2*(a+b+c) - (a+a+b+b+c), O(n) O(n)
    Bit Operation O(n) O(1)
     */
    public int singleNumber(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : nums) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        for (int i : nums) {
            if (map.get(i) == 1) return i;
        }
        return 0;
    }
}