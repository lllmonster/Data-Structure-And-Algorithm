/**
Method 1 - Brute Force, o(mn),o(1)
 */
class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] res = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            res[i] = findEle(nums1[i], nums2);
        }
        return res;
    }
    
    private int findEle(int target, int[] nums2) {
        boolean isEqual = false;
        for (int i = 0; i < nums2.length; i++) {
            if (nums2[i] == target) {
                isEqual = true;
            }
            if (isEqual && nums2[i] > target) {
                return nums2[i];
            }
        }
        return -1;
    }
}

/**
Method 2 - Stack - o(n),o(n)
Each map store <num, the greater ele>
Use Stack to find the next greate number.
*/
class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] res = new int[nums1.length];
        
        Stack<Integer> stack = new Stack<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums2) {
            while (!stack.isEmpty() && num > stack.peek()) {
                map.put(stack.pop(), num);
            }
            stack.push(num);
        }
        while (!stack.isEmpty()) {
            map.put(stack.pop(), -1);
        }
        
        for (int i = 0; i < nums1.length; i++) {
            res[i] = map.get(nums1[i]);
        }
        return res;
    }
}