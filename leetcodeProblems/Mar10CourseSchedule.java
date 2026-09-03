import java.util.*;
import java.util.Queue;

class Solution {
    public boolean canFinish(int V, int[][] edges) {
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

        int cnt = 0; // stores how many nodes are present in topo sort list

        // std. BFS algo
        while (!que.isEmpty()) {
            int node = que.poll();
            cnt++;

            // for all adjacent nodes, decrease there indegree
            for (int adjNode : adjList.get(node)) {
                indegree[adjNode]--;
                if (indegree[adjNode] == 0) {
                    que.add(adjNode);
                }
            }

        }

        // if set contains all nodes, no cycle, means can finish
        return cnt == V;
    }
}
