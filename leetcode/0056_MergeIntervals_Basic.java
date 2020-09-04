class Solution {
    /**
    * Sorting takes O(n log(n)) and merging the intervals takes O(n). 
    * So, the resulting algorithm takes O(n log(n)).
    */
    public int[][] merge(int[][] intervals) {
        if (intervals.length <= 1) return intervals;
        
        Arrays.sort(intervals, (i1, i2) -> Integer.compare(i1[0], i2[0]));
        List<int[]> ans = new ArrayList<>();
        int[] prev = intervals[0];
        for(int[] interval : intervals) {
            if (interval[0] <= prev[1]) {
                prev[1] = Math.max(prev[1], interval[1]);
            } else {
                ans.add(prev);
                prev = interval;
            }
        }
        ans.add(prev);
        return ans.toArray(new int[ans.size()][]);
    }
}