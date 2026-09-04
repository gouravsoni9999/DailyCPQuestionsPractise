import java.util.*;
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