class Solution {
    public int countSquares(int[][] matrix) {
        // TC : O(m.n)
        // SC : O(m.n)
        // using DP tabulation approach
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] dp = new int[m][n];
        // dp[i][j] -> no. of squares ending at [i][j]
        // fill first row and first column with same elements as of matrix
        for(int i = 0;i < m;i++){
            dp[i][0] = matrix[i][0];
        }
        for(int j = 0;j < n;j++){
            dp[0][j] = matrix[0][j];
        }

        // fill rest cells 
        // if (matrix[i][j] == 0) dp[i][j] = 0
        // if (matrix[i][j] == 1) dp[i][j] = 1 + min(dp[i-1][j-1], dp[i-1][j], dp[i][j-1]);
        for(int i = 1;i < m;i++){
            for(int j = 1;j < n;j++){
                if(matrix[i][j] == 1)
                    dp[i][j] = 1 + Math.min(dp[i-1][j-1], Math.min(dp[i-1][j], dp[i][j-1]));
                else
                    // matrix[i][j] = 0
                    dp[i][j] = 0;
            }
        }

        int sum = 0;
        for(int[] arr : dp){
            for(int val : arr){
                sum += val;
            }
        }
        return sum;
    }
}