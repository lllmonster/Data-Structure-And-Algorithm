class Solution {
    /**
    O(n), O(1)
    The point is to put each number in its right place.
    For any array whose lenght is l, the first missing positive must be in range [1,...,l+1]
    So we can use the array index as the hash to restore the frequench of each number
    if (nums[i] > length || nums[i] <= 0) ignore it.
    if (nums[i]-1] == i) perfect place, continue
    if (nums[i] == nums[nums[i]-1]) swap is done, continue // to avoid duplicate situation. [1,1]
    else swap(nums[i], nums[nums[i-1]])
     */
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        int i = 0; 
        while (i < n) {
            if (nums[i] > n || nums[i] <= 0) {
                i++;
            } else if (nums[i]-1 == i || nums[i] == nums[nums[i]-1]){
                i++;
            } else {
                swap(nums, i, nums[i]-1); 
            }
        }
        
        i = 0;
        while (i < n && nums[i] == i+1) i++;
        return i+1;
    }
    
    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}