class Solution {
    public boolean checkStraightLine(int[][] coordinates) {
        int x0 = coordinates[0][0], y0=coordinates[0][1];
        int x1 = coordinates[1][0], y1=coordinates[1][1];
        // a-x0 / b-y0 = x1-x0 / y1-y0
        // a-x0 * y1-y0 = x1-x0 * b-y0
        for (int i = 2; i < coordinates.length; i++) {
            int a = coordinates[i][0];
            int b = coordinates[i][1];
            
            if ((a - x0) * (y1 - y0) != (x1 - x0) * (b - y0)) return false;
        }
        return true;
    }
}