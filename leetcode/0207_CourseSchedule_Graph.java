class Solution {
    /**
    Idea:
        The problem is equivalent to detecting a cycle in the directed graph represented by prerequisites.
        Both BFS and DFS can be used to solve it using the idea of topological sort.
        Since pair<int,int> is inconvenient for implementing graph algorithms, we first transform it to the adjacency-list representation (List<List<Integer>>)
        If course u is a prerequisite of course v, then the adjacency list of u will contain v.
    Time Complexity O(V+E), Space Complexity O(V)
    Method 1: BFS (faster)
        BFS uses the indegree of each node.
        First, initialize and fullfill graph using prerequisites pair<int,int>
        Second, pair<ready, pre>, for each ready course, indegree[ready]++
        Third, find all courses with 0 indegree and put them into the queue. If there's no 0 indegree course, should return false.
        Fourth, using 0 indegree to find ready course and indegree[ready]--. When indegree[ready]==0, put it into queue and it becomes 0 indegree course, and repeat it again.
        Finally, cnt should be equal to numCourses
    Method 2: DFS (more straighforward)
        For DFS, in each visit, we start from a node and keep visiting its neighbors.
        If at a time we return a visited node, there's a cycle. Otherwise, start again from another unvisited node and repeat the process.
        Frst, initialize and fullfill graph using prerequisites pair<int,int>
        Second, start to find if there's any cycle in the graph.    
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        //return dfs(numCourses, prerequisites);
        return bfs(numCourses, prerequisites);
    }
    
    private boolean bfs(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>();
        int[] indegree = new int[numCourses];
        // Initialize graph
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList());
        }
        // fullfill graph
        for (int i = 0; i < prerequisites.length; i++) {
            int ready = prerequisites[i][1];
            int pre = prerequisites[i][0];
            graph.get(prerequisites[i][1]).add(prerequisites[i][0]);
            indegree[prerequisites[i][0]]++;
        }
        
        int cnt = 0;
        Queue<Integer> q = new LinkedList<>();
        // Initialize queue with course which indegree is 0. (The basic course). And put them into queue.
        for (int i = 0; i < indegree.length; i++) {
            if (indegree[i] == 0) {
                q.offer(i);
            }
        }
        
        // Start to count the number of courses using q.poll()
        while (!q.isEmpty()) {
            int course = q.poll();
            cnt++;
            for (int i = 0; i < graph.get(course).size(); i++) {
                indegree[graph.get(course).get(i)]--;
                if (indegree[graph.get(course).get(i)] == 0) {
                    q.offer(graph.get(course).get(i));
                }
            }
        }
        return cnt == numCourses;
        
    }
    
    private boolean dfs(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>();
        
        // Initialize graph
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList());
        }
        
        // fullfill graph using prerequisites
        for (int i = 0; i < prerequisites.length; i++) {
            graph.get(prerequisites[i][1]).add(prerequisites[i][0]);
        }

        // check it there's cycle in the graph
        boolean[] visit = new boolean[numCourses];
        for (int i = 0; i < numCourses; i++) {
            if (hasCycle(i, graph, visit)) {
                return false;
            }
        }
        
        return true;
    }
    
    private boolean hasCycle(int i, List<List<Integer>> graph, boolean[] visit) {
        if (visit[i]) return true;
        visit[i] = true;
        for (int k = 0; k < graph.get(i).size(); k++) {
            if (hasCycle(graph.get(i).get(k), graph, visit)) return true;
        }
        visit[i] = false;
        return false;
    }
}