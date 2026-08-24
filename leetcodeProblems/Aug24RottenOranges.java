import java.util.*;
import java.util.Queue;
class Solution {
    int[][] dir = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };

    public int orangesRotting(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        // using BFS
        int time = 0;
        int freshOranges = 0;
        Queue<int[]> que = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 2) {
                    que.add(new int[] { i, j });
                } else if (grid[i][j] == 1) {
                    freshOranges++;
                }
            }
        }
        if (freshOranges == 0)
            return 0; // no fresh oranges

        while (!que.isEmpty()) {
            int size = que.size();
            boolean rottedThisTime = false;
            while (size-- > 0) {
                int[] coord = que.poll();
                // find all adj coordinates
                for (int[] d : dir) {
                    int x = coord[0] + d[0];
                    int y = coord[1] + d[1];
                    // if {x,y} coordinate is not out-of-bounds and is not visited(has 1)
                    if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 1) {
                        que.add(new int[] { x, y });
                        grid[x][y] = 2; // mark it visited
                        rottedThisTime = true;
                        freshOranges--; // fresh oranges have decreased
                    }
                }
            }
            if (rottedThisTime)
                time++;
        }

        // check if there are fresh oranges left
        if (freshOranges > 0)
            return -1;

        return time;
    }
}

class OptimizedSolution {
    // this is not tempering the inputs
    int[][] dir = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };

    public int orangesRotting(int[][] grid) {
        // SC : O(m.n)
        // TC : O(m.n)
        int m = grid.length;
        int n = grid[0].length;
        int[][] visited = new int[m][n];
        // using BFS

        int freshOranges = 0;
        Queue<int[]> que = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 2) {
                    que.add(new int[] { i, j });
                    visited[i][j] = 2; // mark it visited
                } else {
                    visited[i][j] = 0; // mark it unvisited
                }
                
                // count fresh oranges
                if (grid[i][j] == 1) {
                    freshOranges++;
                }
            }
        }
        if (freshOranges == 0)
            return 0; // no fresh oranges 
        int time = 0;
        while (!que.isEmpty()) {
            int size = que.size();

            while (size-- > 0) {
                int[] coord = que.poll();
                // find all adj coordinates
                for (int[] d : dir) {
                    int x = coord[0] + d[0];
                    int y = coord[1] + d[1];
                    // if {x,y} coordinate is not out-of-bounds and is not visited and actually has a fresh orange
                    if (x >= 0 && x < m && y >= 0 && y < n && visited[x][y] == 0 && grid[x][y] == 1) {
                        que.add(new int[] { x, y });
                        visited[x][y] = 2; // mark it visited
                    }
                }
            }
            time++;
        }

        // check if there are fresh oranges left
        if (freshOranges > 0)
            return -1;

        return time;
    }
}