class MemoSolution {
    int m;
    int n;
    Integer[][][] dp;

    private int findMinCost(int i, int j, int currentXOR, int[][] grid) {
        currentXOR = currentXOR ^ grid[i][j]; // find out xor till now

        if (i == m - 1 && j == n - 1) {
            // reached destination via valid path
            return currentXOR;
        }

        if (dp[i][j][currentXOR] != null)
            return dp[i][j][currentXOR];

        int goDown = Integer.MAX_VALUE;
        int goRight = Integer.MAX_VALUE;

        // move down if within bounds
        if (i + 1 < m)
            goDown = findMinCost(i + 1, j, currentXOR, grid);

        // move right if within bounds
        if (j + 1 < n)
            goRight = findMinCost(i, j + 1, currentXOR, grid);

        return dp[i][j][currentXOR] = Math.min(goDown, goRight);
    }

    public int minCost(int[][] grid) {
        // TC & SC: O(m.n.1024) ~ O(m.n)
        m = grid.length;
        n = grid[0].length;

        // 3 states [i][j][currentXOR]
        dp = new Integer[m][n][1024]; // as grid[i][j] <= 1023

        return findMinCost(0, 0, 0, grid);
    }
}

class TabulationSolution {
    int m;
    int n;
    int[][][] dp;

    public int minCost(int[][] grid) {
        // TC & SC: O(m.n.1024) ~ O(m.n)
        m = grid.length;
        n = grid[0].length;

        // 3 states [i][j][currentXOR]
        dp = new int[m][n][1024]; // as grid[i][j] <= 1023

        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                for (int currXOR = 1023; currXOR >= 0; currXOR--) {
                    int nextXOR = currXOR ^ grid[i][j];

                    if (i == m - 1 && j == n - 1)
                        dp[i][j][currXOR] = nextXOR;

                    else {

                        int goDown = Integer.MAX_VALUE;
                        int goRight = Integer.MAX_VALUE;

                        // move down if within bounds, with updated nextXOR state
                        if (i + 1 < m)
                            goDown = dp[i + 1][j][nextXOR];

                        // move right if within bounds, with updated nextXOR state
                        if (j + 1 < n)
                            goRight = dp[i][j + 1][nextXOR];

                        dp[i][j][currXOR] = Math.min(goDown, goRight);
                    }
                }
            }
        }
        return dp[0][0][0];
    }
}

class SpaceOptimizedTabulationSolution {
    int m;
    int n;

    public int minCost(int[][] grid) {
        // TC: O(m.n.1024) ~ O(m.n)
        // SC : O(n.1024) ~ O(n)
        m = grid.length;
        n = grid[0].length;

        // 3 states [i][j][currentXOR]
        int[][] next = new int[n][1024]; // as grid[i][j] <= 1023

        for (int i = m - 1; i >= 0; i--) {
            int[][] curr = new int[n][1024];
            for (int j = n - 1; j >= 0; j--) {
                for (int currXOR = 1023; currXOR >= 0; currXOR--) {
                    int nextXOR = currXOR ^ grid[i][j];

                    if (i == m - 1 && j == n - 1)
                        curr[j][currXOR] = nextXOR;

                    else {

                        int goDown = Integer.MAX_VALUE;
                        int goRight = Integer.MAX_VALUE;

                        // move down if within bounds, with updated nextXOR state
                        if (i + 1 < m)
                            goDown = next[j][nextXOR];

                        // move right if within bounds, with updated nextXOR state
                        if (j + 1 < n)
                            goRight = curr[j + 1][nextXOR];

                        curr[j][currXOR] = Math.min(goDown, goRight);
                    }
                }
            }
            next = curr;
        }
        return next[0][0];
    }
}