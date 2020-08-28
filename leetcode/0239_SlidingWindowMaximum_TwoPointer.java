class Solution {
    /**
    Method 1: Two pass, O(n), O(n)
                nums = [1,3,-1,-3,5,3,6,7], and k = 3
                left = [1,3,3,-3,5,3,6,7]
                right= [3,3,-1,5,5,3,7,7]
                ans[i] = max(right[i], left[i+k-1])
    Method 2: Using Deque, double ended queue, O(n), O(n)
              queue is used to store index.
              q.peek(), q.peekLast(), q.poll(), q.pollLast(), q.offer()
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        // return usingDeque(nums, k);
        return usingTwoPointer(nums, k);
    }

    private int[] usingTwoPointer(int[] nums, int k) {
        int n = nums.length;
        int[] left = new int[n];
        left[0] = nums[0];
        int[] right = new int[n];
        right[n-1] = nums[n-1];
        // find the max value from left and right 
        for (int i = 1; i < n; i++) {
            left[i] = (i % k == 0) ? nums[i] : Math.max(left[i-1], nums[i]);
            int j = n - i - 1;
            right[j] = (j % k == k-1) ? nums[j] : Math.max(right[j+1], nums[j]);
        }
        
        // sliding-max(i) = max{right_max(i), left_max(i+w-1)}
        int[] ans = new int[n-k+1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = Math.max(right[i], left[i+k-1]);
        }
        return ans;
    }

    private int[] usingDeque(int[] nums, int k) {
        int n = nums.length;
        int[] ans = new int[n-k+1];
        int id = 0;
        Deque<Integer> q = new ArrayDeque<Integer>();
        for (int i = 0; i < n; i++) {
            // remove numbers out of range k
            while (!q.isEmpty() && q.peek() < i - k + 1) q.poll();
            // remove smaller numbers in k range
            while (!q.isEmpty() && nums[q.peekLast()] < nums[i]) q.pollLast();
            // q contains index, ans contains maximum number
            q.offer(i);
            if (i >= k - 1) {
                ans[id++] = nums[q.peek()];
            }
        }
        
        return ans;
    }
}