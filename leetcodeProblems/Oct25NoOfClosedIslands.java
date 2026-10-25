import java.util.*;
import java.util.Queue;

class DFSSolution {
    int m;
    int n;
    boolean[][] visited;
    int[][] dir = { { 1, 0 }, { 0, 1 }, { 0, -1 }, { -1, 0 } };

    private boolean isValid(int i, int j) {
        return (i >= 0 && j >= 0 && i < m && j < n);
    }

    private void dfs(int i, int j, int[][] grid) {

        for (int[] d : dir) {
            int adjR = i + d[0];
            int adjC = j + d[1];

            if (isValid(adjR, adjC) && grid[adjR][adjC] == 0 && !visited[adjR][adjC]) {
                visited[adjR][adjC] = true;
                dfs(adjR, adjC, grid);
            }
        }
    }

    public int closedIsland(int[][] grid) {
        // TC and SC : O(m*n)
        int cnt = 0; // no. of connected components that are 0s (no boundary 0s)

        m = grid.length;
        n = grid[0].length;
        visited = new boolean[m][n];
        // mark boundary 0s and their connected 0s as visited(and not part of our ans)
        for (int i = 0; i < m; i++) {
            if (grid[i][0] == 0 && !visited[i][0]) {
                // land at boundary
                visited[i][0] = true;
                dfs(i, 0, grid);
            }
            if (grid[i][n - 1] == 0 && !visited[i][n - 1]) {
                // land at boundary
                visited[i][n - 1] = true;
                dfs(i, n - 1, grid);
            }
        }
        for (int j = 0; j < n; j++) {
            if (grid[0][j] == 0 && !visited[0][j]) {
                visited[0][j] = true;
                dfs(0, j, grid);
            }
            if (grid[m - 1][j] == 0 && !visited[m - 1][j]) {
                visited[m - 1][j] = true;
                dfs(m - 1, j, grid);
            }
        }

        // iterate over other 0s within boundary and cnt all connected components (0s)
        for (int i = 1; i < m - 1; i++) {
            for (int j = 1; j < n - 1; j++) {
                if (grid[i][j] == 0 && !visited[i][j]) {
                    visited[i][j] = true;
                    cnt++;
                    dfs(i, j, grid);
                }
            }
        }

        return cnt;
    }
}

class BFSSolution {
    int m;
    int n;
    boolean[][] visited;
    int[][] dir = { { 1, 0 }, { 0, 1 }, { 0, -1 }, { -1, 0 } };

    private boolean isValid(int i, int j) {
        return (i >= 0 && j >= 0 && i < m && j < n);
    }

    private void bfs(int i, int j, int[][] grid) {
        Queue<int[]> que = new LinkedList<>();
        que.add(new int[] { i, j });

        while (!que.isEmpty()) {
            // mark all adjacent 0s as visited
            i = que.peek()[0];
            j = que.peek()[1];
            que.poll();

            for (int[] d : dir) {
                int adjR = i + d[0];
                int adjC = j + d[1];

                if (isValid(adjR, adjC) && grid[adjR][adjC] == 0 && !visited[adjR][adjC]) {
                    visited[adjR][adjC] = true;
                    que.add(new int[] { adjR, adjC });
                }
            }
        }
    }

    public int closedIsland(int[][] grid) {
        // TC and SC : O(m*n)
        int cnt = 0; // no. of connected components that are 0s (no boundary 0s)

        m = grid.length;
        n = grid[0].length;
        visited = new boolean[m][n];
        // mark boundary 0s and their connected 0s as visited(and not part of our ans)

        for (int i = 0; i < m; i++) {
            if (grid[i][0] == 0 && !visited[i][0]) {
                // land at boundary
                visited[i][0] = true;
                bfs(i, 0, grid);
            }
            if (grid[i][n - 1] == 0 && !visited[i][n - 1]) {
                // land at boundary
                visited[i][n - 1] = true;
                bfs(i, n - 1, grid);
            }
        }
        for (int j = 0; j < n; j++) {
            if (grid[0][j] == 0 && !visited[0][j]) {
                visited[0][j] = true;
                bfs(0, j, grid);
            }
            if (grid[m - 1][j] == 0 && !visited[m - 1][j]) {
                visited[m - 1][j] = true;
                bfs(m - 1, j, grid);
            }
        }

        // iterate over other 0s within boundary and cnt all connected components (0s)
        for (int i = 1; i < m - 1; i++) {
            for (int j = 1; j < n - 1; j++) {
                if (grid[i][j] == 0 && !visited[i][j]) {
                    visited[i][j] = true;
                    cnt++;
                    bfs(i, j, grid);
                }
            }
        }

        return cnt;
    }
}

