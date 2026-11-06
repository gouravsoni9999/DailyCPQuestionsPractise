class MemoizedSolution {
    int n;
    Integer[] dp;

    private int solve(int i, int[] cost) {

        if (i > n) {
            return Integer.MAX_VALUE; // not reached destination
        }

        if (i == n) {
            return 0; // reached destination
        }

        if(dp[i] != null)
            return dp[i];

        int goOneStep = solve(i + 1, cost);
        int gotTwoSteps = solve(i + 2, cost);

        return dp[i] = cost[i] + Math.min(goOneStep, gotTwoSteps);

    }

    public int minCostClimbingStairs(int[] cost) {
        // TC & SC : O(n)
        n = cost.length;
        dp = new Integer[n + 1];
        return Math.min(solve(0, cost), solve(1, cost));
    }
}

class TabulatedSolution {
    int n;
    int[] dp;

    public int minCostClimbingStairs(int[] cost) {
        // TC & SC : O(n)
        n = cost.length;
        dp = new int[n + 1];
        dp[n] = 0;
        for (int i = n - 1; i >= 0; i--) {

            int goOneStepUp = (i + 1 > n) ? Integer.MAX_VALUE : dp[i + 1];
            int goTwoStepsUp = (i + 2 > n) ? Integer.MAX_VALUE : dp[i + 2];
            dp[i] = cost[i] + Math.min(goOneStepUp, goTwoStepsUp);
        }

        return Math.min(dp[0], dp[1]);
    }
}