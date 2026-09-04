import java.util.*;
import java.util.Queue;
class DFSSolution {
    int V;
    boolean[] vis;
    boolean[] pathVis;
    boolean[] isSafe;

    private boolean cycleDetectionUsingDFS(int node, int[][] graph) {
        vis[node] = true;
        pathVis[node] = true;

        // traverse all adj. nodes
        for (int adjNode : graph[node]) {
            if (vis[adjNode]) {
                if (pathVis[adjNode]) {
                    // cyclic path (in same path)
                    isSafe[node] = false; // cycle detected!
                    return true;
                }
            } else {
                // if not vis[node]
                if (cycleDetectionUsingDFS(adjNode, graph) == true) {
                    // further DFS calls are telling us that cycle is present
                    // so this adjNode is also part of/connected to that cycle
                    isSafe[node] = false;
                    return true; // cycle detected!
                }
            }
        }
        isSafe[node] = true; // since, all adjNodes were visited and still no cycle was found
        pathVis[node] = false; // to see other paths, we need to backtrack
        return false; // no cycle is returned
    }

    public List<Integer> eventualSafeNodes(int[][] graph) {
        // TC : O(V + E) same as DFS on directed graph
        // SC : O(V)
        // using DFS(cycle detection in directed graph algo)
        V = graph.length;
        vis = new boolean[V];
        pathVis = new boolean[V];
        isSafe = new boolean[V];

        // for exploring all nodes(connected components problem)
        for (int i = 0; i < V; i++) {
            if (!vis[i])
                cycleDetectionUsingDFS(i, graph);
        }

        List<Integer> res = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            if (isSafe[i]) {
                res.add(i);
            }
        }

        return res;
    }
}

class BFSSolution {
    public List<Integer> eventualSafeNodes(int[][] graph) {
        // using BFS -> Kahn's Topo Sort Algo
        // TC : O(V + E) similar to topsort + sorting of list + revAdjList making
        // SC : O(V) similar to toposort + sorting of list + revAdjList making
        int V = graph.length;

        // the only thing is to reverse the graph, means all directed edges' directions are reversed
        List<List<Integer>> revAdjList = new ArrayList<>();
        int[] indegree = new int[V]; 
        for(int i = 0;i < V;i++){
            revAdjList.add(new ArrayList<>());
        }
        for(int i = 0;i < V;i++){
            for(int adjNode : graph[i]){
                // adjNode -> i
                revAdjList.get(adjNode).add(i);
                indegree[i]++;
            }
        }
        
        List<Integer> safeNodesList = new ArrayList<>();
        // execute same Kahn's Algo on revAdjList
        Queue<Integer> que = new LinkedList<>(); // stores those nodes having indegree 0
        for(int i = 0;i < V;i++){
            if(indegree[i] == 0){
                que.add(i);
            }
        }

        // Std. BFS step
        while(!que.isEmpty()){
            int node = que.poll();
            safeNodesList.add(node);
            for(int adjNode : revAdjList.get(node)){
                indegree[adjNode]--;
                if(indegree[adjNode] == 0){
                    que.add(adjNode);
                }
            }
        }

        // sort resultant list in asc. order(as per question)
        Collections.sort(safeNodesList);
        return safeNodesList;
    }
}