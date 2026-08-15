class Solution {
    static int n;

    static int findMinOpns(int[] arr, int i, int j) {
        if (i == j)
            // base case
            return 0;

        int mini = (int) 1e9;

        for (int k = i; k < j; k++) {
            int steps = arr[i - 1] * arr[k] * arr[j] + findMinOpns(arr, i, k) + findMinOpns(arr, k + 1, j);
            mini = Math.min(mini, steps);
        }
        return mini;
    }

    static int matrixMultiplication(int arr[]) {
        // recursive solution
        // TC : exponential
        n = arr.length;
        return findMinOpns(arr, 1, n - 1);
    }
}

class MemoizedSolution {
    static int n;
    static Integer[][] dp;

    static int findMinOpns(int[] arr, int i, int j) {
        if (i == j)
            // base case
            return 0;

        if (dp[i][j] != null)
            return dp[i][j];

        int mini = (int) 1e9;

        for (int k = i; k < j; k++) {
            // recursively solve for matrices (i,k) and (k+1,j)
            int steps = arr[i - 1] * arr[k] * arr[j] + findMinOpns(arr, i, k) + findMinOpns(arr, k + 1, j);
            mini = Math.min(mini, steps);
        }
        return dp[i][j] = mini;
    }

    static int matrixMultiplication(int arr[]) {
        // using recursion + memoization
        // TC : n.O(n.n) -> for loop(running ~ n times) -> O(n^3)
        // SC : O(n.n) + O(n) recursion stack space
        n = arr.length;
        dp = new Integer[n][n];
        return findMinOpns(arr, 1, n - 1);// changing var. i & j
    }
}

class TabulatedSolution {
    static int matrixMultiplication(int arr[]) {
        // using tabulation
        // TC : O(n^3)
        // SC : O(n^2)
        int n = arr.length;
        int[][] dp = new int[n][n];
        for (int i = n - 1; i >= 1; i--) {
            for (int j = i + 1; j < n; j++) { // should start from i+1, which means always i is on left of j
                if (i == j)
                    dp[i][j] = 0; // single matrix : no multiplication operation
                else {
                    int mini = Integer.MAX_VALUE;
                    for (int k = i; k < j; k++) {
                        // recursively solve and add for (i,k) and (k+1,j)
                        int opns = arr[i - 1] * arr[k] * arr[j] + dp[i][k] + dp[k + 1][j];
                        mini = Math.min(mini, opns);
                    }
                    dp[i][j] = mini;
                }
            }
        }
        return dp[1][n - 1];
    }
}