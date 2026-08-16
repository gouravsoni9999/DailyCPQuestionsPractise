import java.util.*;

class RecursiveSolution {
    private int solve(int i, int j, int[] cuts) {
        if (i > j)
            return 0;
        int mini = Integer.MAX_VALUE;
        for (int ind = i; ind <= j; ind++) {
            int cost = cuts[j + 1] - cuts[i - 1] + solve(i, ind - 1, cuts) + solve(ind + 1, j, cuts); // length / cost -> cuts[j+1] - cuts[i-1] 
            // and f(i,ind-1) + f(ind+1, j) -> these 2 subproblems can be solved independently as cuts is sorted
            mini = Math.min(mini, cost);
        }
        return mini;
    }

    public int minCost(int n, int[] cuts) {
        // using recursion
        // TC : O(mlogm) + exponential (trying all partitions)
        // SC : O(m) -> recusion stack space
        int m = cuts.length;
        int[] arr = new int[m + 2];
        arr[0] = 0;
        arr[arr.length - 1] = n;
        for (int i = 0; i < m; i++) {
            arr[i + 1] = cuts[i];
        }
        Arrays.sort(arr);
        return solve(1, m, arr);
    }
}

class MemoizedSolution {
    Integer[][] dp;

    private int solve(int i, int j, int[] cuts) {
        if (i > j)
            return 0;
        if (dp[i][j] != null)
            return dp[i][j];
        int mini = Integer.MAX_VALUE;
        for (int ind = i; ind <= j; ind++) {
            int cost = cuts[j + 1] - cuts[i - 1] + solve(i, ind - 1, cuts) + solve(ind + 1, j, cuts); // length / cost -> cuts[j+1] - cuts[i-1] 
            // and f(i,ind-1) + f(ind+1, j) -> these 2 subproblems can be solved independently as cuts is sorted
            mini = Math.min(mini, cost);
        }
        return dp[i][j] = mini;
    }

    public int minCost(int n, int[] cuts) {
        // using recursion + memoization
        // TC : O(m^3)
        // SC : O(m^2) (dp array) + O(m) (stack space)
        int m = cuts.length;
        dp = new Integer[m + 1][m + 1];
        int[] arr = new int[m + 2];
        arr[0] = 0;
        arr[arr.length - 1] = n;
        for (int i = 0; i < m; i++) {
            arr[i + 1] = cuts[i];
        }
        Arrays.sort(arr);
        return solve(1, m, arr);
    }
}

class Solution {

    public int minCost(int n, int[] cuts) {
        // using tabulation
        // TC : O(m^3)
        // SC : O(m)
        int m = cuts.length;
        int[][] dp = new int[m + 2][m + 2];
        int[] arr = new int[m + 2];
        arr[0] = 0;
        arr[arr.length - 1] = n;
        for (int i = 0; i < m; i++) {
            arr[i + 1] = cuts[i];
        }
        Arrays.sort(arr);
        for (int i = m; i >= 1; i--) {
            for (int j = i; j <= m; j++) {
                // j starts from i because i > j is base case (0)
                int mini = Integer.MAX_VALUE;

                for (int ind = i; ind <= j; ind++) {
                    int cost = arr[j + 1] - arr[i - 1] + dp[i][ind - 1] + dp[ind + 1][j]; // length / cost -> cuts[j+1] - cuts[i-1] 
                    // and f(i,ind-1) + f(ind+1, j) -> these 2 subproblems can be solved independently as cuts is sorted
                    mini = Math.min(mini, cost);
                }
                dp[i][j] = mini;
            }
        }
        return dp[1][m];
    }
}