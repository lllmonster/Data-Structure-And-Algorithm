/**
Simple, O(1),O(nlogn)
 */
class Solution {
    public boolean canMakeArithmeticProgression(int[] arr) {
        Arrays.sort(arr);
        for (int i = 2; i < arr.length; i++) {
            if (arr[i-1]-arr[i-2] != arr[i]-arr[i-1]) return false;
        }
        return true;
    }
}

/**
O(n),O(n)
1, Find the max and min, find the step
2, check every number is on the steps;
3, use set to make sure no duplicates;
 */
class Solution {
    public boolean canMakeArithmeticProgression(int[] arr) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int num : arr) {
            max = Math.max(max, num);
            min = Math.min(min, num);
        }
        if((max-min)%(arr.length-1) != 0) return false; // [1,2,4]
        
        int step = (max-min)/(arr.length-1);
        if (step == 0) return true; // corner case: [0,0,0,0]
        
        Set<Integer> set = new HashSet<Integer>();
        for(int num : arr) {
            if ((num-min)%step != 0) return false;
            if (!set.add(num)) return false;
        }
        return true;
    }
}