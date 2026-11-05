class MemoizedSolution {
    Integer[] dp;

    private int solve(int i) {
        if (i == 0 || i == 1)
            return i;

        if (i == 2)
            return 1;

        if (dp[i] != null)
            return dp[i];

        return dp[i] = solve(i - 1) + solve(i - 2) + solve(i - 3);
    }

    public int tribonacci(int n) {
        // TC & SC : O(n)
        dp = new Integer[n + 1];
        return solve(n);
    }
}

class TabulatedSolution {
    int[] dp;

    public int tribonacci(int n) {
        // TC & SC : O(n)
        dp = new int[n + 1];
        for (int i = 0;i <= n;i++) {
            if (i == 0 || i == 1)
                dp[i] = i;
            else if (i == 2)
                dp[i] = 1;
            else
                dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
        }
        return dp[n];
    }
}