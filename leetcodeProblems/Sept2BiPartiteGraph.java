class Solution {
    private boolean dfs(int node, int colorNo, int[][] adjList, Integer[] color) {
        // mark the node with the colorNo
        color[node] = colorNo;
        for (int adjNode : adjList[node]) {

            if (color[adjNode] == null) {
                // if not already filled
                if (dfs(adjNode, 1 - colorNo, adjList, color) == false) {
                    // adjacent nodes -> same color
                    return false;
                }

            }

            else if (color[adjNode] == colorNo) {
                // adjacent nodes are of same color
                return false; // not a bipartite
            }

        }

        // since, till now, I have not got any adjacent nodes having same color
        return true; // therefore return true

    }

    public boolean isBipartite(int[][] graph) {
        // TC and SC (same as DFS Algo)
        // TC : O(V+2E)
        // SC : O(V) + (O(V) recursive stack space in skewed graph)
        int n = graph.length;

        // make a color[] array, containing null(no color), 0 or 1(color no.)
        Integer[] color = new Integer[n];

        for (int i = 0; i < n; i++) {
            // for covering all connected components, I need to traverse through all nodes
            if (color[i] == null) {
                // still unfilled(unvisited)
                if (dfs(i, 0, graph, color) == false) {
                    // adj. nodes are of same color
                    return false;
                }
            }
        }
        return true;
    }
}