class Solution {
    /**
    Similar with LC207 Course Schedule
    Method 1: BFS (straightforward)
    Method 2: DFS
        visit[] matrix need to be more tricky
        change from boolean[] to int[], which can represent 3 status.
        0 - not visit
        1 - visiting
        2 - visited
     */
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        //return dfs(numCourses, prerequisites);
        return bfs(numCourses, prerequisites);
    }
    
    private int[] bfs(int numCourses, int[][] prerequisites) {
        List<Integer> ans = new ArrayList<>();
        List<List<Integer>> graph = new ArrayList<>();
        int[] indegree = new int[numCourses];
        // Initialize graph
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList());
        }
        
        // fullfill graph
        for (int i = 0; i < prerequisites.length; i++) {
            graph.get(prerequisites[i][1]).add(prerequisites[i][0]);
            indegree[prerequisites[i][0]]++;
        }
        
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < indegree.length; i++) {
            if(indegree[i] == 0) q.offer(i);
        }
        while(!q.isEmpty()) {
            int course = q.poll();
            ans.add(course);
            for (int i = 0; i < graph.get(course).size(); i++) {
                indegree[graph.get(course).get(i)]--;
                if(indegree[graph.get(course).get(i)] == 0) {
                    q.offer(graph.get(course).get(i));
                }
            }
        }
        if (ans.size() != numCourses) return new int[0];
        int[] ans_arr = new int[ans.size()];
        for (int i = 0; i < ans.size(); i++) {
            ans_arr[i] = ans.get(i);
        }
        return ans_arr;
    }
    
    private int[] dfs(int numCourses, int[][] prerequisites) {
        List<Integer> ans = new ArrayList<>();
        List<List<Integer>> graph = new ArrayList<>();
        
        // Initialize graph
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList());
        }
        
        // fullfill graph
        for (int i = 0; i < prerequisites.length; i++) {
            graph.get(prerequisites[i][1]).add(prerequisites[i][0]);
        }
        
        // iterate courses to find if there's any cycle in the graph
        int[] visit = new int[numCourses]; // 1 is visting, 2 is visited
        for (int i = 0; i < numCourses; i++) {
            if (hasCycle(i, graph, visit, ans)) return new int[0];
        }
        
        int[] ans_arr = new int[ans.size()];
        for (int i = 0; i < ans.size(); i++) {
            ans_arr[i] = ans.get(ans.size()-1-i);
        }
        return ans_arr;
        //return ans.toArray(new int[ans.size()]);
    }
    
    private boolean hasCycle(int num, List<List<Integer>> graph, int[] visit, List<Integer> ans) {
        if (visit[num] == 1) return true;
        if (visit[num] == 2) return false;
        visit[num] = 1;
        for (int i = 0; i < graph.get(num).size(); i++) {
            int course = graph.get(num).get(i);
            if (hasCycle(course, graph, visit, ans)) return true;
        }
        ans.add(num);
        visit[num] = 2;
        return false;
    }
}