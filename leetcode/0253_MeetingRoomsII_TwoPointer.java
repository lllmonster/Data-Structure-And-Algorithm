class Solution {
    /**
    Idea is so smart.
     */
    public int minMeetingRooms(int[][] intervals) {
        
        int[] start = new int[intervals.length];
        int[] end = new int[intervals.length];
        for (int i = 0; i < intervals.length; i++) {
            start[i] = intervals[i][0];
            end[i] = intervals[i][1];
        }
        Arrays.sort(start);
        Arrays.sort(end);
        
        int j = 0, cnt = 0;
        for (int i = 0; i < start.length; i++) {
            if (start[i] < end[j]) cnt++;
            else j++;
        }
        return cnt;
    }
}