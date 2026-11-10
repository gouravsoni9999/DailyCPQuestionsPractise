class MemoizedSolution {
    Integer[] dp;

    private int solve(int curr, int n, int[] costs) {
        if (dp[curr] != null)
            return dp[curr];

        int minCost = Integer.MAX_VALUE;

        for (int next = curr + 1; next <= curr + 3 && next <= n; next++) {
            int currCost = ((next - curr) * (next - curr)) + costs[next - 1];
            int nextCost = (next == n) ? 0 : solve(next, n, costs);
            minCost = Math.min(minCost, nextCost + currCost);
        }

        return dp[curr] = minCost;
    }

    public int climbStairs(int n, int[] costs) {
        // TC : O(n)
        // SC : O(n)
        dp = new Integer[n];
        return solve(0, n, costs);
    }
}

class TabulatedSolution {

    public int climbStairs(int n, int[] costs) {
        // TC : O(n)
        // SC : O(n)
        int[] dp = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            int minCost = Integer.MAX_VALUE;

            for (int next = i + 1; next <= i + 3 && next <= n; next++) {
                int currCost = ((next - i) * (next - i)) + costs[next - 1];
                int nextCost = (next == n) ? 0 : dp[next];
                minCost = Math.min(minCost, nextCost + currCost);
            }

            dp[i] = minCost;
        }
        return dp[0];
    }
}

class TabulatedAndSpaceOptimizedSolution {

    public int climbStairs(int n, int[] costs) {
        // TC : O(n)
        // SC : O(1)
        int[] dp = new int[4];
        for (int i = n - 1; i >= 0; i--) {
            int minCost = Integer.MAX_VALUE;

            for (int next = i + 1; next <= i + 3 && next <= n; next++) {
                int currCost = ((next - i) * (next - i)) + costs[next - 1];
                int nextCost = (next == n) ? 0 : dp[next - i];
                minCost = Math.min(minCost, nextCost + currCost);
            }

            dp[0] = minCost;

            dp[3] = dp[2];
            dp[2] = dp[1];
            dp[1] = dp[0];

        }
        return dp[0];
    }
}