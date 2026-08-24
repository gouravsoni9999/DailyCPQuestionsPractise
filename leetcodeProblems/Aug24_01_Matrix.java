import java.util.*;
import java.util.Queue;
class Solution {
    int[][] dir = { { 0, -1 }, { -1, 0 }, { 0, 1 }, { 1, 0 } };

    public int[][] updateMatrix(int[][] mat) {
        // TC : O(m.n)
        // SC : O(m.n)
        // using BFS(multi-source BFS)
        int m = mat.length;
        int n = mat[0].length;

        int[][] dist = new int[m][n]; // stores distance to nearest 0
        boolean[][] visited = new boolean[m][n]; // stores whichever is visited
        Queue<int[]> que = new LinkedList<>();

        // find all 0's and they have nearest dist 0
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 0) {
                    que.add(new int[] { i, j, 0 });
                    visited[i][j] = true;
                }
            }
        }

        while (!que.isEmpty()) {
            int i = que.peek()[0];
            int j = que.peek()[1];
            int steps = que.peek()[2];
            que.poll();

            dist[i][j] = steps; // first put in dist matrix
            // for all adjacent neighbours, find 1 which is not visited
            for (int[] d : dir) {
                int x = i + d[0];
                int y = j + d[1];

                // if x and y are out-of-bounds and {x,y} is not visited
                if (x >= 0 && y >= 0 && x < m && y < n && !visited[x][y]) {
                    que.add(new int[] { x, y, steps + 1 });
                    visited[x][y] = true;
                }
            }
        }

        return dist;
    }
}