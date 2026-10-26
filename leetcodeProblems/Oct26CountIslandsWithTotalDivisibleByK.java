class Solution {
    int m;
    int n;
    boolean[][] visited;

    private boolean isValid(int i, int j) {
        return (i >= 0 && j >= 0 && i < m && j < n);
    }

    private long dfs(int[][] grid, int i, int j) {
        // mark it as visited
        visited[i][j] = true;

        // take a totalValue
        long totalValue = grid[i][j];
        // iterate over neighbours and if not visited, do dfs
        int[] row = { 1, 0, -1, 0 };
        int[] col = { 0, 1, 0, -1 };

        for (int d = 0; d < 4; d++) {
            int adjR = i + row[d];
            int adjC = j + col[d];
            if (isValid(adjR, adjC) && grid[adjR][adjC] > 0 && !visited[adjR][adjC]) {
                totalValue += dfs(grid, adjR, adjC);
            }
        }

        return totalValue;
    }

    public int countIslands(int[][] grid, int k) {
        // using DFS
        // TC : O(m*n)
        // SC : O(m*n)
        m = grid.length;
        n = grid[0].length;
        int islands = 0;
        visited = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] > 0 && !visited[i][j]) {
                    long totalValue = dfs(grid, i, j); // to avoid integer overflow
                    if (totalValue % k == 0) {
                        islands++;
                    }
                }
            }
        }

        return islands;
    }
}