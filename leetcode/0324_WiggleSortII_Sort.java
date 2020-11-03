class Solution {
    /**
    The idea is simple, sort at first and assign one by one.
    Method 1: o(nlogn), o(1)
    Method 2: TODO: follow up: o(n) average time and o(1) space.
                o(n) can use kLargestNumber (Leetcode 215)
                o(1) can use swap.
    */
    public void wiggleSort(int[] nums) {
        int[] copy = Arrays.copyOf(nums, nums.length);
        Arrays.sort(copy);
        int left = 0, right = nums.length-1;
        int median = (left + right) / 2;
        for (int i = 0; i < nums.length; i++) {
            if (i%2 == 0) {
                nums[i] = copy[median--];
            } else {
                nums[i] = copy[right--];
            }
        }
    }
}