class Solution {
    public int nearestValidPoint(int x, int y, int[][] points) {
        int distance = Integer.MAX_VALUE;
        int id = Integer.MAX_VALUE;
        for (int i = 0; i < points.length; i++) {
            int a = points[i][0];
            int b = points[i][1];
            if (a == x || b == y) {
                int md = ManhattanDist(a, b, x, y);
                if (md < distance) {
                    distance = md;
                    id = i;
                }
            }
        }
        return id == Integer.MAX_VALUE ? -1 : id;
    }
    
    private int ManhattanDist(int a, int b, int x, int y) {
        return Math.abs(x-a) + Math.abs(y-b);
    }
}