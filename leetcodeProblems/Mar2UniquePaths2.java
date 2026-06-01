class Solution {
    int m;
    int n;

    private int solve(int i, int j, int[][] obstacleGrid) {
        if (i >= m || j >= n)
            return 0; // no paths
        if (obstacleGrid[i][j] == 1)
            return 0; // no paths to last cell from this cell
        if (i == m - 1 && j == n - 1)
            return 1; // as we have reached here
        int pathsFromDown = solve(i + 1, j, obstacleGrid);
        int pathsFromRight = solve(i, j + 1, obstacleGrid);
        int totalPaths = pathsFromDown + pathsFromRight;
        return totalPaths;
    }

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
	// using simple recursion
        // TC : O(2^(m+n)) 
        // SC : O(m+n)
        m = obstacleGrid.length;
        n = obstacleGrid[0].length;
        // base case:
        // if obstacle is present at (m-1, n-1), answer is always 0
        if(obstacleGrid[m-1][n-1] == 1) 
            return 0;
        return solve(0, 0, obstacleGrid);
    }
}

class Solution {
    int m;
    int n;
    int[][] dp;
    private int solve(int[][] obstacleGrid, int i,int j){
        if (i >= m || j >= n) return 0;//no path to reach (m-1,n-1)
        if (obstacleGrid[i][j] == 1) return 0;//obstacle present 
        if (i == m-1 && j == n-1) return 1;//a path was found
        if (dp[i][j] != -1) return dp[i][j];
        int downPaths = solve(obstacleGrid, i+1, j);
        int rightPaths = solve(obstacleGrid, i,j+1);
        return dp[i][j] = downPaths + rightPaths;
    }
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        // using recursion + memoization
	// TC : O(m*n)
	// SC : O(m*n) + O(m+n)
        m = obstacleGrid.length;
        n = obstacleGrid[0].length;
        dp = new int[m][n];
        for(int[] row: dp) Arrays.fill(row, -1);
        return solve(obstacleGrid, 0, 0);
    }
}

class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        // using tabulation
        // TC : O(m*n)
        // SC : O(m*n)
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        // base case: if target cell is blocked
        if (obstacleGrid[m - 1][n - 1] == 1)
            return 0; // no paths
        int[][] dp = new int[m][n];
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (obstacleGrid[i][j] == 1)
                    dp[i][j] = 0; // no paths
                else if (i == m - 1 && j == n - 1)
                    dp[i][j] = 1;
                else {
                    int downPaths = (i + 1 >= m) ? 0 : dp[i + 1][j];
                    int rightPaths = (j + 1 >= n) ? 0 : dp[i][j + 1];
                    dp[i][j] = downPaths + rightPaths;
                }
            }
        }
        return dp[0][0];
    }
}

class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        // using tabulation + space-optimization
        // TC : O(m*n)
        // SC : O(n)
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        // base case: if target cell is blocked
        if (obstacleGrid[m - 1][n - 1] == 1)
            return 0; // no paths
        int[] next = new int[n];
        // next is filled acc. to mth row and nth col that is all values 0
        for (int i = m - 1; i >= 0; i--) {
            int[] curr = new int[n];
            for (int j = n - 1; j >= 0; j--) {
                if (obstacleGrid[i][j] == 1)
                    curr[j] = 0; // no paths
                else if (i == m - 1 && j == n - 1)
                    curr[j] = 1;
                else {
                    int downPaths = (i + 1 >= m) ? 0 : next[j];
                    int rightPaths = (j + 1 >= n) ? 0 : curr[j + 1];
                    curr[j] = downPaths + rightPaths;
                }
            }
            next = curr.clone();
        }
        return next[0];
    }
}
