class Solution {
    int m;
    int n;
    private boolean isValid(int i,int j1,int j2){
        if(i < 0 || j1 < 0 || j2 < 0 || i >= m || j1 >= n || j2 >= n){
            return false;
        }
        return true;
    }
    private int solve(int[][] grid, int i,int j1,int j2){
        // i is the row where alice and bob both are
        // j1 is the col where alice is 
        // j2 is the col where bob is
        if(!isValid(i,j1,j2)){
            return -1; // no possible sum
        }
        if(i == m-1){
            // at last row(our destination)
            if(j1 == j2){
                // both reach same col
                // add only once
                return grid[i][j1];
            }else{
                // both reach on different col.
                return grid[i][j1] + grid[i][j2];
            }
        }
        int maxi = 0;
        for(int dj1 = -1; dj1 <= 1; dj1++){
            for(int dj2 = -1;dj2 <= 1;dj2++){
                if(j1 == j2){
                    // same column
                    // add only once
                    maxi = Math.max(maxi, grid[i][j1] + solve(grid, i+1, j1+dj1, j2+dj2));
                }else{
                    // add both cells value
                    maxi = Math.max(maxi, grid[i][j1]+grid[i][j2]+solve(grid, i+1, j1+dj1,j2+dj2));
                }
            }
        }
        return maxi;
    }
    public int cherryPickup(int[][] grid) {
        // using recursion
        // TC : O(3^n * 3^n) // 3 options for every nth row for both alice and bob
        // SC : O(n) recursion stack space
        m = grid.length;
        n = grid[0].length;
        return solve(grid, 0, 0, n-1);
    }
}

class Solution {
    int m;
    int n;
    int[][][] dp;
    private boolean isValid(int i,int j1,int j2){
        if(i < 0 || j1 < 0 || j2 < 0 || i >= m || j1 >= n || j2 >= n){
            return false;
        }
        return true;
    }
    private int solve(int[][] grid, int i,int j1,int j2){
        // i is the row where alice and bob both are
        // j1 is the col where alice is 
        // j2 is the col where bob is
        if(!isValid(i,j1,j2)){
            return -1; // no possible sum
        }
        if(i == m-1){
            // at last row(our destination)
            if(j1 == j2){
                // both reach same col
                // add only once
                return grid[i][j1];
            }else{
                // both reach on different col.
                return grid[i][j1] + grid[i][j2];
            }
        }
        if(dp[i][j1][j2] != -1) 
            return dp[i][j1][j2];
        int maxi = 0;
        for(int dj1 = -1; dj1 <= 1; dj1++){
            for(int dj2 = -1;dj2 <= 1;dj2++){
                if(j1 == j2){
                    // same column
                    // add only once
                    maxi = Math.max(maxi, grid[i][j1] + solve(grid, i+1, j1+dj1, j2+dj2));
                }else{
                    // add both cells value
                    maxi = Math.max(maxi, grid[i][j1]+grid[i][j2]+solve(grid, i+1, j1+dj1,j2+dj2));
                }
            }
        }
        return dp[i][j1][j2] = maxi;
    }
    public int cherryPickup(int[][] grid) {
        // using recursion + memoization
        // TC : O(m * n * n) * 3 * 3 // for loop runs 9 times
        // SC : O(m * n * n) + O(n) recursion stack space
        m = grid.length;
        n = grid[0].length;
        dp = new int[m][n][n];
        for(int[][] arr1: dp){
            for(int[] arr2: arr1){
                Arrays.fill(arr2,-1);
            }
        }
        return solve(grid, 0, 0, n-1);
    }
}

class Solution {
    int n;
    private boolean isValid(int j){
        if(j < 0 || j >= n){
            return false;
        }
        return true;
    }
    public int cherryPickup(int[][] grid) {
        // using tabulation
        // TC : O(n^2 + m*n^2)
        // SC : O(m*n*n)
        int m = grid.length;
        n = grid[0].length;
        int[][][] dp = new int[m][n][n];
        // last row base case
        for (int j1 = 0; j1 < n; j1++) {
            for (int j2 = 0; j2 < n; j2++) {
                if (j1 == j2) {
                    dp[m - 1][j1][j2] = grid[m - 1][j1];
                } else {
                    dp[m - 1][j1][j2] = grid[m - 1][j1] + grid[m - 1][j2];
                }
            }
        }
        for (int i = m - 2; i >= 0; i--) {
            for (int j1 = n - 1; j1 >= 0; j1--) {
                for (int j2 = n - 1; j2 >= 0; j2--) {
                    int maxi = 0;
                    for (int dj1 = -1; dj1 <= 1; dj1++) {
                        for (int dj2 = -1; dj2 <= 1; dj2++) {
                            int val = 0;
                            if (j1 == j2) {
                                val = grid[i][j1];
                            } else {
                                val = grid[i][j1] + grid[i][j2];
                            }
                            if (isValid(j1 + dj1) && isValid(j2 + dj2)) {
                                val += dp[i + 1][j1 + dj1][j2 + dj2];
                            } else {
                                val = -1; // reset to not possible value
                            }
                            maxi = Math.max(maxi, val);
                        }
                    }
                    dp[i][j1][j2] = maxi;
                }
            }
        }
        return dp[0][0][n - 1];
    }
}

class Solution {
    int n;
    private boolean isValid(int j){
        if(j < 0 || j >= n){
            return false;
        }
        return true;
    }
    public int cherryPickup(int[][] grid) {
        // using tabulation + space optimization
        // TC : O(n^2 + m*(n^2)*3*3)
        // SC : O(n*n)
        int m = grid.length;
        n = grid[0].length;
        int[][] next = new int[n][n];
        
        // last row base case
        for (int j1 = 0; j1 < n; j1++) {
            for (int j2 = 0; j2 < n; j2++) {
                if (j1 == j2) {
                    next[j1][j2] = grid[m - 1][j1];
                } else {
                    next[j1][j2] = grid[m - 1][j1] + grid[m - 1][j2];
                }
            }
        }
        for (int i = m - 2; i >= 0; i--) {
            int[][] curr = new int[n][n];
            for (int j1 = n - 1; j1 >= 0; j1--) {
                for (int j2 = n - 1; j2 >= 0; j2--) {
                    int maxi = 0;
                    for (int dj1 = -1; dj1 <= 1; dj1++) {
                        for (int dj2 = -1; dj2 <= 1; dj2++) {
                            int val = 0;
                            if (j1 == j2) {
                                val = grid[i][j1];
                            } else {
                                val = grid[i][j1] + grid[i][j2];
                            }
                            if (isValid(j1 + dj1) && isValid(j2 + dj2)) {
                                val += next[j1 + dj1][j2 + dj2];
                            } else {
                                val = -1; // reset to not possible value
                            }
                            maxi = Math.max(maxi, val);
                        }
                    }
                    curr[j1][j2] = maxi;
                } 
            }
            next = curr;
        }
        return next[0][n - 1];
    }
}
