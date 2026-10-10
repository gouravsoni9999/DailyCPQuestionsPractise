import java.util.*;
import java.util.Queue;
class BinarySearchSolutionUsingDFS {
    int n;
    int[][] dir = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };

    private boolean isReachableDFS(int i, int j, int[][] grid, int mid, boolean[][] visited) {
        // use BFS/DFS to explore 
        // DFS
        if (i < 0 || j < 0 || i >= n || j >= n || visited[i][j] || grid[i][j] > mid) {
            // can't explore this grid[i][j], as it is not reachable
            return false;
        }

        visited[i][j] = true; // mark it visited

        if (i == n - 1 && j == n - 1) {
            // reached destination, therefore isReachable
            return true;
        }

        for (int[] d : dir) {
            int x = i + d[0];
            int y = j + d[1];

            if (isReachableDFS(x, y, grid, mid, visited)) {
                return true;
            }
        }

        return false;

    }

    public int swimInWater(int[][] grid) {
        // TC : O(n^2.log(n^2)) ~ O(n^2.logn)
        // SC : O(n^2)
        // using binary search
        n = grid.length;
        int res = 0;

        int low = grid[0][0];
        int high = n * n - 1;

        // O(log n^2)
        while (low <= high) {
            int mid = low + (high - low) / 2;
            boolean[][] visited = new boolean[n][n];
            if (isReachableDFS(0, 0, grid, mid, visited)) { // DFS on all nodes -> O(n^2)
                res = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return res;
    }
}

class BinarySearchSolutionUsingBFS {
    int n;
    int[][] dir = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };

    private boolean isReachableBFS(int[][] grid, int mid) {
        // use BFS/DFS to explore 
        // BFS

        boolean[][] visited = new boolean[n][n];

        visited[0][0] = true;
        Queue<int[]> que = new LinkedList<>();
        que.add(new int[] { 0, 0 });

        while (!que.isEmpty()) {
            int i = que.peek()[0];
            int j = que.peek()[1];
            que.poll();

            if (i == n - 1 && j == n - 1) {
                // reached destination, therefore isReachable
                return true;
            }

            for (int[] d : dir) {
                int x = i + d[0];
                int y = j + d[1];

                if (x < 0 || y < 0 || x >= n || y >= n || visited[x][y] || grid[x][y] > mid) {
                    // can't explore this grid[i][j], as it is not reachable
                    continue;
                }

                que.add(new int[] { x, y });
                visited[x][y] = true;
            }
        }

        return false;

    }

    public int swimInWater(int[][] grid) {
        // TC : O(n^2.log(n^2)) ~ O(n^2.logn)
        // SC : O(n^2)
        // using binary search
        n = grid.length;
        int res = 0;

        int low = grid[0][0];
        int high = n * n - 1;

        // O(log n^2)
        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (isReachableBFS(grid, mid)) { // BFS on all nodes -> O(n^2)
                res = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return res;
    }
}

class Solution {
    int INF = (int) 1e9;
    int[][] dir = { { 1, 0 }, { 0, 1 }, { 0, -1 }, { -1, 0 } };

    public int swimInWater(int[][] grid) {
        // TC : O(n.n.logn)
        // SC  O(n.n)
        // using dijkstra's algo
        int n = grid.length;

        int[][] dist = new int[n][n];
        for(int[] d : dist){
            Arrays.fill(d, INF);
        }

        Queue<int[]> que = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0])); // stores {dist, {i, j}}
        que.add(new int[] { grid[0][0], 0, 0 });
        dist[0][0] = grid[0][0];

        while (!que.isEmpty()) {
            int distFromSrc = que.peek()[0];
            int i = que.peek()[1];
            int j = que.peek()[2];
            que.poll();

            if (i == n - 1 && j == n - 1) {
                return distFromSrc;
            }

            // iterate over adj. nodes
            for (int[] d : dir) {
                int x = i + d[0];
                int y = j + d[1];

                if (x < 0 || y < 0 || x >= n || y >= n) {
                    // invalid coord
                    continue;
                }

                int mini = Math.max(distFromSrc, grid[x][y]);
                if (mini < dist[x][y]) {
                    dist[x][y] = mini;
                    que.add(new int[] { dist[x][y], x, y });
                }
            }
        }

        return -1;
    }
}