class MemoizedSolution {

    int m, n;
    Integer[][][] dp;
    int MOD = (int) (1e9 + 7);

    private int solve(int i, int j, int[][] grid, int wasRight) {
        // wasRight:
        // if wasRight == 2 : start of recursion, ignore every time for saving in dp[]
        // array
        // if wasRight == 1 : came from right
        // if wasRight == 0 : came from down

        if (i == m - 1 && j == n - 1)
            // reached destination!
            // valid path!
            return 1;

        if (i < 0 || j < 0 || i >= m || j >= n)
            // invalid path!
            return 0;

        if (wasRight != 2 && dp[i][j][wasRight] != null)
            return dp[i][j][wasRight];

        if (grid[i][j] == 1) {
            // mirror present
            if (wasRight == 1) {
                // move just down
                return dp[i][j][wasRight] = solve(i + 1, j, grid, 0);
            } else {
                // move just right
                return dp[i][j][wasRight] = solve(i, j + 1, grid, 1);
            }
        }

        else {
            // no mirror present
            int totalPathsDown = solve(i + 1, j, grid, 0);
            int totalPathsRight = solve(i, j + 1, grid, 1);

            int totalPaths = (totalPathsDown + totalPathsRight) % MOD;
            if (wasRight != 2)
                dp[i][j][wasRight] = totalPaths;

            return totalPaths;
        }
    }

    public int uniquePaths(int[][] grid) {
        // using recursion + memoization
        // TC : O(m*n)
        // SC : O(m*n)

        m = grid.length;
        n = grid[0].length;

        dp = new Integer[m][n][2];

        return solve(0, 0, grid, 2);
    }
}

class TabulatedSolution {

    int m, n;
    int[][][] dp;
    int MOD = (int) (1e9 + 7);

    public int uniquePaths(int[][] grid) {
        // using tabulation
        // TC : O(m*n)
        // SC : O(m*n) 

        m = grid.length;
        n = grid[0].length;

        dp = new int[m][n][3];

        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                for (int wasRight = 2; wasRight >= 0; wasRight--) {

                    int ans = 0;

                    if (i == m - 1 && j == n - 1) {
                        // vaid path found
                        ans = 1;
                    }

                    else if (grid[i][j] == 1) {
                        // mirror present
                        if (wasRight == 1) {
                            // move just down
                            ans = (i + 1 < m) ? dp[i + 1][j][0] : 0;
                        } else {
                            // move just right
                            ans = (j + 1 < n) ? dp[i][j + 1][1] : 0;
                        }
                    }

                    else {
                        // no mirror present
                        int totalPathsDown = (i + 1 < m) ? dp[i + 1][j][0] : 0;
                        int totalPathsRight = (j + 1 < n) ? dp[i][j + 1][1] : 0;

                        int totalPaths = (totalPathsDown + totalPathsRight) % MOD;

                        ans = totalPaths;
                    }

                    dp[i][j][wasRight] = ans;
                }
            }
        }

        return dp[0][0][2];
    }
}

class TabulatedAndSpaceOptimizedSolution {

    int m, n;
    int MOD = (int) (1e9 + 7);

    public int uniquePaths(int[][] grid) {
        // using tabulation + space-optimization
        // TC : O(m*n)
        // SC : O(n) 

        m = grid.length;
        n = grid[0].length;

        int[][] next = new int[n][3];

        for (int i = m - 1; i >= 0; i--) {
            int[][] curr = new int[n][3];
            for (int j = n - 1; j >= 0; j--) {
                for (int wasRight = 2; wasRight >= 0; wasRight--) {

                    int ans = 0;

                    if (i == m - 1 && j == n - 1) {
                        // vaid path found
                        ans = 1;
                    }

                    else if (grid[i][j] == 1) {
                        // mirror present
                        if (wasRight == 1) {
                            // move just down
                            ans = (i + 1 < m) ? next[j][0] : 0;
                        } else {
                            // move just right
                            ans = (j + 1 < n) ? curr[j + 1][1] : 0;
                        }
                    }

                    else {
                        // no mirror present
                        int totalPathsDown = (i + 1 < m) ? next[j][0] : 0;
                        int totalPathsRight = (j + 1 < n) ? curr[j + 1][1] : 0;

                        int totalPaths = (totalPathsDown + totalPathsRight) % MOD;

                        ans = totalPaths;
                    }

                    curr[j][wasRight] = ans;
                }
            }
            next = curr;
        }

        return next[0][2];
    }
}