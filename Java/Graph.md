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