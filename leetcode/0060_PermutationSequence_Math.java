class Solution {
    public String getPermutation(int n, int k) {
        StringBuilder sb = new StringBuilder();
        List<Integer> nums = new ArrayList<>();
        int[] factorial = new int[n];
        factorial[0] = 1;
        for (int i = 1; i < n; i++) {
            factorial[i] = i * factorial[i-1];
        }
        for (int i = 1; i <= n; i++) {
            nums.add(i);
        }
        k--;
        for (int i = 0; i < n; i++) {
            sb.append(nums.remove(k/factorial[n-i-1]));
            k = k % factorial[n-i-1];
        }
        return sb.toString();
    }
}