import java.util.*;
import java.util.Queue;

class Solution {
    int INF = (int) 1e9;
    int MOD = 1000;

    public int minSteps(int[] arr, int start, int end) {
        // using dijkstra's algo
        // TC : O(MOD * n)
        // SC : O(MOD)
        // using simple BFS
        Queue<int[]> que = new LinkedList<>(); // stores {steps, node}

        int[] dist = new int[MOD]; // stores distance from src to node i

        Arrays.fill(dist, INF);

        dist[start] = 0; // distance b/w start to start is 0

        que.add(new int[] { 0, start });

        while (!que.isEmpty()) {
            int[] curr = que.poll();

            int steps = curr[0];
            int node = curr[1];

            if (node == end) {
                return dist[node]; // reached end
            }

            // explore adjacent ones
            for (int num : arr) {
                int adjNode = (num * node) % MOD;

                if (steps + 1 < dist[adjNode]) {
                    // relaxation
                    dist[adjNode] = steps + 1;

                    if (node == end) {
                        return dist[adjNode];
                    }

                    que.add(new int[] { dist[adjNode], adjNode });
                }
            }

        }

        return -1; // cannot reach end
    }
}
