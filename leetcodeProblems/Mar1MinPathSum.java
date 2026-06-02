class Solution {
    int m;
    int n;
    private int solve(int i,int j,int[][] grid){
        if(i >= m || j >= n)
            return Integer.MAX_VALUE;//no valid sum
        if(i == m-1 && j == n-1)
            return grid[i][j]; // answer
        int rightSum = solve(i, j+1, grid);
        int downSum = solve(i+1, j, grid);
        return grid[i][j] + Math.min(rightSum, downSum);
    }
    public int minPathSum(int[][] grid) {
        // using simple recursion
        // TC : O(2^(m+n))
        // SC : O(m+n)
        m = grid.length;
        n = grid[0].length;
        return solve(0, 0, grid);
    }
}

class Solution {
        int m;
        int n;
        int[][] dp;
        private int solve(int i,int j,int[][] grid){
	    // base case: can't reach destination
            if(i >= m || j >= n)
                return Integer.MAX_VALUE;//no valid sum
            if(i == m-1 && j == n-1)
                return grid[i][j]; // answer
            if(dp[i][j] != -1) return dp[i][j];
            int rightSum = solve(i, j+1, grid);
            int downSum = solve(i+1, j, grid);
            return dp[i][j] = grid[i][j] + Math.min(rightSum, downSum);
        }
        public int minPathSum(int[][] grid) {
            // using recursion + memoization
            // TC : O(m*n)
            // SC : O(m+n) + O(m*n)
            m = grid.length;
            n = grid[0].length;
            dp = new int[m][n];
            for(int[] arr: dp){
                Arrays.fill(arr, -1);
            }
            return solve(0, 0, grid);
        }
}

class Solution {
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        /*
            using tabulation
            TC : O(m*n)
            SC : O(m*n)
        */
        int[][] dp = new int[m][n];
        for(int i = m-1;i >= 0;i--){
            for(int j = n-1;j >= 0;j--){
                if(i == m-1 && j == n-1){
                    dp[m-1][n-1] = grid[m-1][n-1]; // base case
                    continue;
                }
                int rightSum = (j+1 >= n) ? Integer.MAX_VALUE : dp[i][j+1];
                int leftSum = (i+1 >= m) ? Integer.MAX_VALUE : dp[i+1][j];
                int minSum = Math.min(rightSum, leftSum);
                int minSumForThisPath = grid[i][j] + minSum;
                dp[i][j] = minSumForThisPath;
            }
        }
        return dp[0][0];
    }
}

class Solution {
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        /*
            using tabulation + space-optimization
            TC : O(m*n)
            SC : O(n)
        */
        int[] dp = new int[n];
        for(int i = m-1;i >= 0;i--){
            for(int j = n-1;j >= 0;j--){
                if(i == m-1 && j == n-1){
                    dp[j] = grid[m-1][n-1]; // base case
                    continue;
                }
                int rightSum = (j+1 >= n) ? Integer.MAX_VALUE : dp[j+1];
                int leftSum = (i+1 >= m) ? Integer.MAX_VALUE : dp[j];
                int minSum = Math.min(rightSum, leftSum);
                int minSumForThisPath = grid[i][j] + minSum;
                dp[j] = minSumForThisPath;
            }
        }
        return dp[0];
    }
}
