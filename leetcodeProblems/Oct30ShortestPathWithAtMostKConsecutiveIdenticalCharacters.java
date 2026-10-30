import java.util.*;
import java.util.Queue;

class Solution {
    public int shortestPath(int n, int[][] edges, String labels, int k) {
        // using dijkstra's algo
        // TC : O(ElogV) -> O(E.k.log(n.k))
        // SC : O(V + E) = O(nk + Ek) = O(k(n+E))
        
        // make adjList
        List<List<int[]>> adjList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int[] e : edges) {
            int u = e[0];
            int v = e[1];
            int wt = e[2];

            adjList.get(u).add(new int[] { v, wt });
        }

        // perform dijkstra's algo
        int[][] dist = new int[n][k + 1]; // for every count value at node, we find paths
        for (int[] d : dist)
            Arrays.fill(d, Integer.MAX_VALUE);

        Queue<int[]> que = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0])); // min heap

        dist[0][1] = 0; // distance from src 0 with count 1 of src labels[src], we have 0 distance

        que.add(new int[] { 0, 0, 1 });

        while (!que.isEmpty()) {
            int d = que.peek()[0];
            int node = que.peek()[1];
            int count = que.peek()[2];

            que.poll();

            if (d > dist[node][count])
                continue; // we already have more smaller distance

            if (node == n - 1)
                return d; // found valid path

            for (int[] adj : adjList.get(node)) {
                int adjNode = adj[0];
                int wt = adj[1];

                int newCount = (labels.charAt(node) == labels.charAt(adjNode)) ? count + 1 : 1;

                if (newCount > k)
                    continue; // not a valid path

                int newDist = d + wt;
                if (newDist < dist[adjNode][newCount]) {
                    dist[adjNode][newCount] = newDist;
                    que.add(new int[] { newDist, adjNode, newCount });
                }
            }
        }

        return -1; // not got till last node
    }
}