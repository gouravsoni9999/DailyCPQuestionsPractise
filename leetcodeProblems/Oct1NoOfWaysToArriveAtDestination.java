import java.util.*;
import java.util.Queue;
class Pair {
    long dis;
    int node;

    Pair(long dis, int node) {
        this.dis = dis;
        this.node = node;
    }
}

class Solution {
    int MOD = (int) 1e9 + 7;

    public int countPaths(int n, int[][] roads) {
        // TC : O(Elogn)
        // SC : O(n + E)
        // making adjacency list
        List<List<int[]>> adjList = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int[] e : roads) {
            int u = e[0], v = e[1], wt = e[2];
            adjList.get(u).add(new int[] { v, wt });
            adjList.get(v).add(new int[] { u, wt });
        }

        // using Dijkstra's Algo

        int[] ways = new int[n]; // stores no. of ways to reach from 0 to i through shortest paths
        long[] dist = new long[n]; // stores shortest distance b/w 0 to i
        Arrays.fill(dist, Long.MAX_VALUE);

        dist[0] = 0;
        ways[0] = 1; // only 1 way to reach from 0 to 0

        // using Priority Queue (min-Heap)
        Queue<Pair> que = new PriorityQueue<>((a, b) -> Long.compare(a.dis, b.dis)); // stores {dist, node}
        que.add(new Pair(0, 0));

        while (!que.isEmpty()) {
            long distFrom0 = que.peek().dis;
            int node = que.peek().node;

            que.poll();

            // iterate over adjacent nodes
            for (int[] it : adjList.get(node)) {
                int adjNode = it[0];
                int edW = it[1];

                if (edW + distFrom0 < dist[adjNode]) {
                    // relaxation
                    dist[adjNode] = edW + distFrom0;
                    ways[adjNode] = ways[node];
                    que.add(new Pair(dist[adjNode], adjNode));
                } else if (edW + distFrom0 == dist[adjNode]) {
                    // ways increases
                    ways[adjNode] = (ways[adjNode] + ways[node]) % MOD;
                }
            }
        }

        return ways[n - 1];
    }
}