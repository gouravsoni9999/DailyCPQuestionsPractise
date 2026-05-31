class Solution {
    private int solve(int i,int j,int m,int n){
        // base case
        if(i >= m || j >= n) return 0; // no paths present
        if(i == m-1)
            return 1; // only way to reach (m-1, n-1) is to move right
        if(j == n-1)
            return 1; // only way to reach (m-1, n-1) is to move down
        
        int noOfPathsFromDown = solve(i+1, j, m, n);
        int noOfPathsFromRight = solve(i, j+1, m, n);
        int totalPaths = noOfPathsFromDown + noOfPathsFromRight;
        return totalPaths;
    }
    public int uniquePaths(int m, int n) {
        // using simple recursion
        // TC : O(2^(m+n))
	// SC : O(m+n)
        return solve(0, 0, m, n);
    }
}

class Solution {
    int[][] dp;
    private int solve(int i,int j,int m,int n){
        // base case
        if(i >= m || j >= n) return 0; // no paths present
        if(i == m-1)
            return 1; // only way to reach (m-1, n-1) is to move right
        if(j == n-1)
            return 1; // only way to reach (m-1, n-1) is to move down
        
        if(dp[i][j] != -1) return dp[i][j];
        int noOfPathsFromDown = solve(i+1, j, m, n);
        int noOfPathsFromRight = solve(i, j+1, m, n);
        int totalPaths = noOfPathsFromDown + noOfPathsFromRight;
        return dp[i][j] = totalPaths;
    }
    public int uniquePaths(int m, int n) {
        // using recursion + memoization
        // TC : O(mxn)
        // SC : O(mxn)
        dp = new int[m][n];
        for(int[] arr: dp){
            Arrays.fill(arr, -1);
        }
        return solve(0, 0, m, n);
    }
}

class Solution {
    public int uniquePaths(int m, int n) {
        // using tabulation
        // TC : O(mxn)
        // SC : O(mxn)
        // dp[i][j] = total no. of paths from (i,j) to (m-1,n-1)
        int[][] dp = new int[m+1][n+1];

        // base cases
        // for mth row and nth col, dp[m][i] = 0 and dp[j][n] = 0(automatically)
        for(int j = 0;j < n;j++){
            dp[m-1][j] = 1;
        }
        for(int i = 0;i < m;i++){
            dp[i][n-1] = 1;
        }

        for(int i = m-2;i >= 0;i--){
            for(int j = n-2;j >= 0;j--){
                int noOfPathsFromDown = dp[i+1][j];
                int noOfPathsFromRight = dp[i][j+1];
                int totalPaths =  noOfPathsFromDown + noOfPathsFromRight;
                dp[i][j] = totalPaths;
            }
        }
        return dp[0][0];
    }
}

class Solution {
    public int uniquePaths(int m, int n) {
        // using tabulation + space optimization
        // if there is a previous row and previous col, we can space optimize it
        // TC : O(mxn)
        // SC : O(n)
        // dp[i][j] = total no. of paths from (i,j) to (m-1,n-1)
        int[] next = new int[n];//all initialized to zero for i = m || j = n

        for (int i = m - 1; i >= 0; i--) {
            int[] curr = new int[n];
            for (int j = n - 1; j >= 0; j--) {
                // base case
                if (i == m - 1 || j == n - 1)
                    curr[j] = 1;
                else {
                    int noOfPathsFromDown = next[j];
                    int noOfPathsFromRight = (j + 1 == n) ? 0 : curr[j + 1];
                    int totalPaths = noOfPathsFromDown + noOfPathsFromRight;
                    curr[j] = totalPaths;
                }
            }
            next = curr.clone();
        }
        return next[0];
    }
}
