class Solution {
    /**
    The theory is that find the Kth smallest number.
    Same method with LC215
    use quick select sort, partition()
     */
    public int[][] kClosest(int[][] points, int K) {
        //return easy(points, K);
        return optimize(points, K);
    }
    
    private int[][] optimize(int[][] points, int K) {
        int lo = 0, hi = points.length - 1;
        while (lo < hi) {
            int pivot = partition(points, lo, hi);
            if (pivot == K) break;
            else if (pivot > K) {
                hi = pivot - 1;
            } else {
                lo = pivot + 1;
            }
        }
        return Arrays.copyOfRange(points, 0, K);
    }
    
    private int partition(int[][] points, int lo, int hi) {
        int i = lo, j = hi+1;
        while (true) {
            while (i < hi && compare(points, ++i, lo));
            while (j > lo && compare(points, lo, --j));
            if (i >= j) break;
            swap(points, i, j);
        }
        swap(points, lo, j);
        return j;
    }
    
    private boolean compare(int[][] points, int i, int j) {
        return points[i][0] * points[i][0] + points[i][1] * points[i][1] <= points[j][0] * points[j][0] + points[j][1] * points[j][1];
    }
    
    private void swap(int[][] points, int i, int j) {
        int[] tmp = new int[2];
        tmp[0] = points[i][0];
        tmp[1] = points[i][1];
        points[i][0] = points[j][0];
        points[i][1] = points[j][1];
        points[j][0] = tmp[0];
        points[j][1] = tmp[1];
    }
    
    private int[][] easy(int[][] points, int K) {
        int[][] distance = new int[points.length][2];
        for (int i = 0; i < points.length; i++) {
            distance[i][0] = (int) Math.pow(points[i][0],2) + (int) Math.pow(points[i][1],2);
            distance[i][1] = i;
        }
        
        Arrays.sort(distance, (a,b) -> Integer.compare(a[0], b[0]));
        int[][] res = new int[K][2];
        for (int i = 0; i < K; i++) {
            int idx = distance[i][1];
            res[i][0] = points[idx][0];
            res[i][1] = points[idx][1];
        }
        return res;
    }
}