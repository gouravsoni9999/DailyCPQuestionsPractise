import java.util.*;
import java.util.Stack;

class Solution {

    boolean[] visited;
    Stack<Integer> stack;

    private void dfs(ArrayList<ArrayList<Integer>> adj, int node) {
        // mark node visited
        visited[node] = true;

        // find all its adjacent nodes, and call dfs for them
        for (Integer adjNode : adj.get(node)) {
            if (!visited[adjNode])
                dfs(adj, adjNode);
        }

        // push into stack
        stack.push(node);
    }

    public int countSCC(ArrayList<ArrayList<Integer>> adj) {
        // using Kosaraju's Algo
        // TC : O(N + E)
        // SC : O(N + E)(transposed Graph) + O(N)(visited array) + O(N) (stack)
        int n = adj.size();

        // step 1: find topo sort using DFS(sort all nodes in order of finishing time)
        // O(N + E)
        stack = new Stack<>(); // stores toposort
        visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(adj, i);
            }
        }

        // step 2: transpose the graph(reverse the edges and make a new graph)
        // O(N + E)
        ArrayList<ArrayList<Integer>> transposedGraph = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            transposedGraph.add(new ArrayList<>());
        }

        for (int i = 0; i < n; i++) {
            int u = i;
            for (int v : adj.get(u)) {
                // u -> v in adj
                // v -> u in transposedGraph
                transposedGraph.get(v).add(u);
            }
        }

        int cntSCC = 0;

        // step 3: call dfs() acc. to topoSort stored in stack
        // O(N + E)
        Arrays.fill(visited, false); // reset visited
        while (!stack.isEmpty()) {
            int node = stack.pop();
            if (!visited[node]) {
                dfs(transposedGraph, node);
                cntSCC++;
            }
        }

        return cntSCC;
    }
}