/**
Math problem
Need to get the repeated times of each position
 */
class Solution {
    public int sumOddLengthSubarrays(int[] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            int times = ((i+1) * (arr.length-i) + 1) / 2;
            sum += arr[i] * times;
        }
        return sum;
    }
}