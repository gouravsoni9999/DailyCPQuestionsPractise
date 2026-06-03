class Solution {
    int n;
    private int solve(int i,int j,List<List<Integer>> triangle){
        if(i >= n || j > i){
            return 0; // out of bounds
        }
        if(i == n-1){
            return triangle.get(i).get(j); // reached destination!
        }
        return triangle.get(i).get(j) + Math.min(solve(i+1,j,triangle),solve(i+1,j+1, triangle));
    }
    public int minimumTotal(List<List<Integer>> triangle) {
        // TC : O(2^n)
        // SC : O(n)
	// using simple recursion
        n = triangle.size();
        return solve(0, 0, triangle);
    }
}

class Solution {
    int n;
    Integer[][] dp;
    private int solve(int i,int j,List<List<Integer>> triangle){
        if(i == n-1)
            return triangle.get(i).get(j); // reached destination!
        if(dp[i][j] != null)
            return dp[i][j];
        return dp[i][j] = triangle.get(i).get(j) + Math.min(solve(i+1,j,triangle),solve(i+1,j+1, triangle));
    }
    public int minimumTotal(List<List<Integer>> triangle) {
        // TC : O(n^2)
        // SC : O(n^2)
	// using recursion + memoization
        n = triangle.size();
        dp = new Integer[n][n];
        return solve(0, 0, triangle);
    }
}


class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
	// using tabulation
	// TC : O(n^2)
	// SC : O(n^2)
        int n = triangle.size();
        int[][] dp = new int[n][n];
        for(int i = n-1;i >= 0;i--){
            for(int j = i;j >= 0;j--){
                if(i == n-1){
                    dp[i][j] = triangle.get(i).get(j);
                }else{
                    dp[i][j] = triangle.get(i).get(j) + Math.min(dp[i+1][j],dp[i+1][j+1]);
                }
            }
        }
        return dp[0][0];
    }
}

class Solution {
    int n;//no. of rows
    int[] dp;

    public int minimumTotal(List<List<Integer>> triangle) {
        n = triangle.size();
        // tabulation + space optimization
        // store dp = last row of triangle
        dp = new int[n];
        for (int i = 0; i < n; i++) {
            dp[i] = triangle.get(n - 1).get(i);
        }
        for (int row = n - 2; row >= 0; row--) {
            for (int col = 0; col <= row; col++) {
                dp[col] = triangle.get(row).get(col) + Math.min(dp[col], dp[col + 1]);
            }
        }
        return dp[0];//min path sum from 0 to n-1th row
    }
}//code takes O(n) SC and O(n^2) TC
