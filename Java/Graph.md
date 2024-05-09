# Graph

## Union Find

```java
class UF {
        int[] root;
        int[] rank;
        public UF(int n) {
            root = new int[n];
            rank = new int[n];
            for(int i = 0; i < n; i++) {
                root[i] = i;
                rank[i] = 0;
            }
        }

        public int find(int i) {
            if (root[i] == i) {
                return i;
            }
            return root[i] = find(root[i]);
        }

        public boolean union(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);
            if (rootQ != rootP) {
                if (rank[rootQ] > rank[rootP]) {
                    root[rootP] = rootQ;
                } else if (rank[rootQ] < rank[rootP]) {
                    root[rootQ] = rootP;
                } else {
                    root[rootQ] = rootP;
                    rank[rootP]++;
                }
                return true;
            } else {
                return false;
            }
        }
    } 
```

## Minimum Spanning Tree (MST)

A spanning tree is a connected subgraph in an undirected graph where all vertices are connected with the minimum number of edges.

A minimum spanning tree is a spanning tree with the minimum possible total edge weight in a “weighted undirected graph”. 

- Kruskal’s Algorithm
  - expands the “minimum spanning tree” by adding vertices.
  - Time Complexity: O(E⋅logE)
  - Space Complexity: O(V)
- Prim’s algorithm
  - expands the “minimum spanning tree” by adding vertices.
  - Time Complexity: O(E⋅logV) for Binary heap, and O(E+V⋅logV) for Fibonacci heap.
  - Space Complexity: O(V)

```java
class Solution {
    // Kruskal's Algorithm
    public int minCostConnectPoints(int[][] points) {
        if (points == null || points.length == 0) {
            return 0;
        }
        int size = points.length;
        PriorityQueue<Edge> pq = new PriorityQueue<>((x, y) -> x.cost - y.cost);
        UnionFind uf = new UnionFind(size);

        for (int i = 0; i < size; i++) {
            int[] coordinate1 = points[i];
            for (int j = i+1; j < size; j++) {
                int[] coordinate2 = points[j];
                // Calculate the distance between two coordinates.
                int cost = Math.abs(coordinate1[0] - coordinate2[0]) + 
                           Math.abs(coordinate1[1] - coordinate2[1]);
                Edge edge = new Edge(i, j, cost);
                pq.add(edge);
            }
        }

        int result = 0;
        int count = size - 1;
        while (!pq.isEmpty() && count > 0) {
            Edge edge = pq.poll();
            if (!uf.connected(edge.point1, edge.point2)) {
                uf.union(edge.point1, edge.point2);
                result += edge.cost;
                count--;
            }
        }
        return result;
    }

    class Edge {
        int point1;
        int point2;
        int cost;

        Edge(int point1, int point2, int cost) {
            this.point1 = point1;
            this.point2 = point2;
            this.cost = cost;
        }
    }

    class UnionFind {
        int root[];
        int rank[];

        public UnionFind(int size) {
            root = new int[size];
            rank = new int[size];
            for (int i = 0; i < size; i++) {
                root[i] = i;
                rank[i] = 1; 
            }
        }

        public int find(int x) {
            if (x == root[x]) {
                return x;
            }
            return root[x] = find(root[x]);
        }

        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX != rootY) {
                if (rank[rootX] > rank[rootY]) {
                    root[rootY] = rootX;
                } else if (rank[rootX] < rank[rootY]) {
                    root[rootX] = rootY;
                } else {
                    root[rootY] = rootX;
                    rank[rootX] += 1;
                }
            }
        }

        public boolean connected(int x, int y) {
            return find(x) == find(y);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        int[][] points = {{0, 0}, {2, 2}, {3, 10}, {5, 2}, {7, 0}};
        Solution solution = new Solution();
        System.out.print("Minimum Cost to Connect Points = "); 
        System.out.println(solution.minCostConnectPoints(points)); 
    }
}
```

```java
class Solution {
    // Prim Algorithm
    public int minCostConnectPoints(int[][] points) {
        if (points == null || points.length == 0) {
            return 0;
        }
        int size = points.length;
        PriorityQueue<Edge> pq = new PriorityQueue<>((x, y) -> x.cost - y.cost);
        boolean[] visited = new boolean[size];
        int result = 0;
        int count = size - 1;
        // Add all edges from points[0] vertexs
        int[] coordinate1 = points[0];
        for (int j = 1; j < size; j++) {
            // Calculate the distance between two coordinates.
            int[] coordinate2 = points[j];
            int cost = Math.abs(coordinate1[0] - coordinate2[0]) + 
                       Math.abs(coordinate1[1] - coordinate2[1]);
            Edge edge = new Edge(0, j, cost);
            pq.add(edge);
        }
        visited[0] = true;

        while (!pq.isEmpty() && count > 0) {
            Edge edge = pq.poll();
            int point1 = edge.point1;
            int point2 = edge.point2;
            int cost = edge.cost;
            if (!visited[point2]) {
                result += cost;
                visited[point2] = true;
                for (int j = 0; j < size; j++) {
                    if (!visited[j]) {
                        int distance = Math.abs(points[point2][0] - points[j][0]) + 
                                       Math.abs(points[point2][1] - points[j][1]);
                        pq.add(new Edge(point2, j, distance));
                    }
                }
                count--;
            }
        }
        return result;
    }

    class Edge {
        int point1;
        int point2;
        int cost;

        Edge(int point1, int point2, int cost) {
            this.point1 = point1;
            this.point2 = point2;
            this.cost = cost;
        }
    }
}

public class Main {
    public static void main(String[] args) {
        int[][] points = {{0, 0}, {2, 2}, {3, 10}, {5, 2}, {7, 0}};
        Solution solution = new Solution();
        System.out.print("Minimum Cost to Connect Points = "); 
        System.out.println(solution.minCostConnectPoints(points)); 
    }
}
```

