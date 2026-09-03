import java.util.*;
import java.util.Queue;
import java.util.Stack;
class KahnSolution {
    public int[] findOrder(int V, int[][] edges) {
        // TC : O(V + E) 
        // SC : O(V)
        // using BFS (Kahn's Topo Sort Algo)

        // if the graph is cyclic, I cannot generate topo sort list of V nodes

        int[] indegree = new int[V];

        // make adjacency list
        List<List<Integer>> adjList = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            adjList.add(new ArrayList<>());
        }

        // build adj. list
        for (int i = 0; i < edges.length; i++) {
            int u = edges[i][0];
            int v = edges[i][1];

            // v -> u
            adjList.get(v).add(u);
            indegree[u]++;
        }

        // make a queue only containing nodes having indegree 0
        Queue<Integer> que = new LinkedList<>();
        for (int i = 0; i < V; i++) {
            if (indegree[i] == 0)
                que.add(i);
        }

        List<Integer> list = new ArrayList<>();

        // std. BFS algo
        while (!que.isEmpty()) {
            int node = que.poll();
            list.add(node);

            // for all adjacent nodes, decrease there indegree
            for (int adjNode : adjList.get(node)) {
                indegree[adjNode]--;
                if (indegree[adjNode] == 0) {
                    que.add(adjNode);
                }
            }

        }

        if (list.size() != V) {
            // graph has cycle
            return new int[0];
        }

        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }

        return res;
    }
}

// using DFS
class DFSTopoSolution {
    boolean[] visited;
    boolean[] inRecursion;
    Stack<Integer> stack;
    int[] order;
    private boolean hasCycleDFS(int vertex,int[][] edges){
        // mark it visited
        visited[vertex] = true;
        // for current Recursion, mark it true
        inRecursion[vertex] = true;
        // explore its neighbours before pushing in stack
        for(int[] revEdge: edges){
            int u = revEdge[1];
            int v = revEdge[0];
            if(u != vertex)
                continue;
            if(!visited[v] && hasCycleDFS(v, edges)){
                return true;
            }
            else if (visited[v] && inRecursion[v]){
                return true;
            }
        } 
        // push in stack
        stack.push(vertex);
        // recursion completed mark inRecursion false
        inRecursion[vertex] = false;
        return false;// no cycle detected till now
    }
    private int[] topoSort(int n, int[][] edges){
        // using DFS
        visited = new boolean[n];
        inRecursion = new boolean[n];
        stack = new Stack<>();
        order = new int[n];
        for(int i = 0;i < n;i++){
            if(!visited[i] && hasCycleDFS(i, edges)){
                return new int[0];//empty array
            }
        }
        // no cycle detected
        int i = 0;
        while(!stack.isEmpty()){
            order[i++] = stack.pop();
        }
        return order;
    }
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // detect cycle: if present : return an empty array
        // not detected: return the order of courses
        return topoSort(numCourses, prerequisites);
    }
}
