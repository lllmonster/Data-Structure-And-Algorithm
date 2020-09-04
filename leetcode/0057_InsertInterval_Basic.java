class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        //Arrays.sort(intervals, (i1, i2) -> Integer.compare(i1[0], i2[0]));
        List<int[]> ans = new ArrayList<>();
        
        int id = 0;
        while (id < intervals.length && intervals[id][1] < newInterval[0]) {
            ans.add(intervals[id++]);
        }
        int[] cur = newInterval.clone();
        while (id < intervals.length && intervals[id][0] <= newInterval[1]) {
            cur[0] = Math.min(intervals[id][0], cur[0]);
            cur[1] = Math.max(intervals[id][1], cur[1]);
            id++;
        }
        ans.add(cur);
        while (id < intervals.length) {
            ans.add(intervals[id++]);
        }
        return ans.toArray(new int[ans.size()][]);
        
    }
}