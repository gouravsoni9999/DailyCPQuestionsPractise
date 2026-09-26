import java.util.*;
import java.util.Queue;
class Solution {
    int INF = (int) 1e9;
    int[][] directions = { { 1, 0 }, { 0, 1 }, { 0, -1 }, { -1, 0 } };

    public int minimumEffortPath(int[][] heights) {
        // using dijkstra's algo
        // TC : O(nxm . log(nxm)) ~ O(ElogV) in dijkstra's algo
        // SC : O(nxm)

        int m = heights.length;
        int n = heights[0].length;

        int[][] dist = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(dist[i], INF);
        }

        Queue<int[]> que = new PriorityQueue<>((a, b) -> {
            return Integer.compare(a[0], b[0]);
        }); // stores {distance from src, row, col} (minHeap)

        dist[0][0] = 0; // distance from src to src is 0
        que.add(new int[] { 0, 0, 0 });
        // ElogV -> (nxm) x log(nxm)
        while (!que.isEmpty()) {
            int[] curr = que.poll();

            int diff = curr[0]; // max. difference till now in path
            int row = curr[1];
            int col = curr[2];

            // if reached destination
            if (row == m - 1 && col == n - 1) {
                return diff; // this is the result
            }

            for (int[] dir : directions) {
                int x = row + dir[0];
                int y = col + dir[1];
                if (x >= 0 && y >= 0 && x < m && y < n) {
                    int newEffort = Math.max(diff, Math.abs(heights[x][y] - heights[row][col]));
                    if (newEffort < dist[x][y]) {
                        // relaxation
                        dist[x][y] = newEffort;
                        // put in minHeap
                        que.add(new int[] { dist[x][y], x, y });
                    }
                }
            }
        }
        return -1;
    }
}