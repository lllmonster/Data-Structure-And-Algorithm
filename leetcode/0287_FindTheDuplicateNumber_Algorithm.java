class Solution {
    /**
    Method 1: Set O(n), O(n)
    Method 2: Sorting, O(nlogn), O(1)
    Method 3: Floyd's Tortoise and Hare (Crycle Detection), O(n), O(1)
                The idea is to reduce the problem to Linked List Cycle II (LC142)
                slow pointer vs fast pointer, fine the cycle entrypoint.
                diff between do while and while. (Different initial condition)
                do while is easier to understand.
     */
    public int findDuplicate(int[] nums) {
        int slow = nums[0], fast = nums[0];
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);
        
        fast = nums[0];
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        
        return slow;
    }
    
    private int anotherMethod(int[] nums) {
        if (nums.length <= 1) return -1;
        int slow = nums[0], fast = nums[slow];
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[nums[fast]];
        }
        
        fast = 0;
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        
        return slow;
    }
}