class Solution {
    /**
    The tricky part is used PriorityQueue to solve sort issue.
    O(NlogN) for sort.
    O(NlogK) for priority queue.

    Idea is easy but hard to implement.
     */
    public double mincostToHireWorkers(int[] quality, int[] wage, int K) {
        double[][] workers = new double[quality.length][2];
        for (int i = 0; i < workers.length; i++) {
            workers[i] = new double[]{(double)wage[i]/quality[i], (double)quality[i]};
        }
        Arrays.sort(workers, (a, b) -> Double.compare(a[0], b[0]));
        
        PriorityQueue<Double> q = new PriorityQueue<>();
        double res = Double.MAX_VALUE;
        int qsum = 0;
        for (double[] worker : workers) {
            qsum += worker[1];
            q.add(-worker[1]);
            if (q.size() > K) {
                qsum += q.poll();
            }
            if (q.size() == K) {
                res = Math.min(res, qsum * worker[0]);
            }
        }
        
        return res;
    }
}