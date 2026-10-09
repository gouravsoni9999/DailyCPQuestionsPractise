import java.util.*;
import java.util.Queue;
class Solution {
    int INF = (int) 1e9;

    public int networkDelayTime(int[][] times, int n, int k) {
        // TC : O(ElogV)
        // SC : O(V + E)
        // using dijkstra's algo with source node k to find out distance from k to other nodes
        int[] dist = new int[n+1];
        Arrays.fill(dist, INF);

        // make adjList
        List<List<int[]>> adjList = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int[] e : times) {
            int u = e[0];
            int v = e[1];
            int wt = e[2];
            adjList.get(u).add(new int[] { v, wt });
        }

        Queue<int[]> que = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));// stores {dist, node}
        que.add(new int[] { 0, k });
        dist[k] = 0; // distance from src to src is 0

        while (!que.isEmpty()) {
            int dis = que.peek()[0];
            int node = que.peek()[1];

            que.poll();

            // find all adjacent nodes
            for (int[] it : adjList.get(node)) {
                int adjNode = it[0];
                int edW = it[1];

                if (edW + dis < dist[adjNode]) {
                    dist[adjNode] = edW + dis;
                    que.add(new int[] { dist[adjNode], adjNode });
                }
            }
        }

        int maxDist = 0;

        // find out max. distance from src node
        for (int i = 1; i <= n; i++) {
            maxDist = Math.max(maxDist, dist[i]);
        }

        return maxDist == INF ? -1 : maxDist;
    }
}