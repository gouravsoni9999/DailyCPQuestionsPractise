class MemoizedSolution {
    int n;
    Integer[][] dp;

    private int solve(int row, int col, int[][] matrix) {
        if (row == n) {
            return 0; // finally, reached end
        }

        if (row < 0 || col < 0 || row >= n || col >= n)
            return Integer.MAX_VALUE; // out-of-bounds

        if (dp[row][col] != null)
            return dp[row][col];

        int val1 = solve(row + 1, col, matrix);
        int val2 = solve(row + 1, col + 1, matrix);
        int val3 = solve(row + 1, col - 1, matrix);

        return dp[row][col] = matrix[row][col] + Math.min(val1, Math.min(val2, val3));
    }

    public int minFallingPathSum(int[][] matrix) {
        // TC : O(n^2)
        // SC : O(n^2)
        // recursion + memoization
        n = matrix.length;
        int mini = Integer.MAX_VALUE;
        dp = new Integer[n][n];
        for (int i = 0; i < n; i++) {
            mini = Math.min(mini, solve(0, i, matrix));
        }
        return mini;
    }
}

class TabulatedSolution {
    int n;
    Integer[][] dp;

    private boolean isValid(int i, int j) {
        return (i >= 0 && j >= 0 && i < n && j < n);
    }

    public int minFallingPathSum(int[][] matrix) {
        // TC : O(n^2)
        // SC : O(n^2)
        // tabulation
        n = matrix.length;
        int mini = Integer.MAX_VALUE;
        dp = new Integer[n][n];

        for (int col = 0; col < n; col++) {
            // fill the last row, with the same value
            dp[n - 1][col] = matrix[n - 1][col];
        }

        for (int row = n - 2; row >= 0; row--) {
            for (int col = n - 1; col >= 0; col--) {

                int val1 = (!isValid(row + 1, col)) ? Integer.MAX_VALUE : dp[row + 1][col];
                int val2 = (!isValid(row + 1, col + 1)) ? Integer.MAX_VALUE : dp[row + 1][col + 1];
                int val3 = (!isValid(row + 1, col - 1)) ? Integer.MAX_VALUE : dp[row + 1][col - 1];

                dp[row][col] = matrix[row][col] + Math.min(val1, Math.min(val2, val3));

            }
        }
        for (int i = 0; i < n; i++) {
            mini = Math.min(mini, dp[0][i]);
        }
        return mini;
    }
}

class SpaceOptimizedAndTabulatedSolution {
    int n;
    int[] next;

    private boolean isValid(int i, int j) {
        return (i >= 0 && j >= 0 && i < n && j < n);
    }

    public int minFallingPathSum(int[][] matrix) {
        // TC : O(n^2)
        // SC : O(n)
        // tabulation + space-optimization
        n = matrix.length;
        int mini = Integer.MAX_VALUE;
        next = new int[n];

        for (int col = 0; col < n; col++) {
            // fill the last row, with the same value
            next[col] = matrix[n - 1][col];
        }

        for (int row = n - 2; row >= 0; row--) {
            int[] curr = new int[n];
            for (int col = n - 1; col >= 0; col--) {

                int val1 = (!isValid(row + 1, col)) ? Integer.MAX_VALUE : next[col];
                int val2 = (!isValid(row + 1, col + 1)) ? Integer.MAX_VALUE : next[col + 1];
                int val3 = (!isValid(row + 1, col - 1)) ? Integer.MAX_VALUE : next[col - 1];

                curr[col] = matrix[row][col] + Math.min(val1, Math.min(val2, val3));

            }
            next = curr;
        }
        for (int i = 0; i < n; i++) {
            mini = Math.min(mini, next[i]);
        }
        return mini;
    }
}