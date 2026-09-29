import java.util.*;
import java.util.Queue;

class Solution {
    int INF = (int) 1e9;

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        // TC : O(flights.length) ~ O(E) // no. of edges
        // SC : O(V + E)
        List<List<int[]>> adjList = new ArrayList<>(); // adjList of graph

        // build adjList
        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int[] e : flights) {
            int wt = e[2];
            int u = e[0];
            int v = e[1];

            // u->v
            adjList.get(u).add(new int[] { v, wt });
        }

        // make distance array
        int[] dist = new int[n];
        Arrays.fill(dist, INF);

        dist[src] = 0; // as src to src is 0

        // using BFS (as stops are main thing, which increases one-by-one)
        Queue<int[]> que = new LinkedList<>(); // stores {stops, node, dist}

        que.add(new int[] { 0, src, 0 });

        // BFS
        while (!que.isEmpty()) {
            int[] curr = que.poll();
            int stops = curr[0];
            int node = curr[1];
            int cost = curr[2]; // distance from src to node

            if (stops > k)
                // max stops achieved, still no answer
                continue;

            // iterate over neighbours
            for (int[] it : adjList.get(node)) {
                int neigh = it[0];
                int edgeWt = it[1];
                if (edgeWt + cost < dist[neigh] && stops <= k) {
                    dist[neigh] = edgeWt + cost;
                    que.add(new int[] { stops + 1, neigh, dist[neigh] });
                }
            }
        }

        return (dist[dst] == INF) ? -1 : dist[dst];
    }
}