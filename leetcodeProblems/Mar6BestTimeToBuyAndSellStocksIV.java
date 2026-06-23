class Solution {
    int n;
    Integer[][] dp;

    private int solve(int i, int[] prices, int txnNo, int k) {
        if (txnNo == 2 * k || i == n) {
            // not a valid txn
            return 0;
        }

        if (dp[i][txnNo] != null)
            return dp[i][txnNo];

        int ans = 0;

        if (txnNo % 2 == 0) {
            // can only buy
            ans = Math.max(-prices[i] + solve(i + 1, prices, txnNo + 1, k),
                    0 + solve(i + 1, prices, txnNo, k));
        } else {
            // can only sell
            ans = Math.max(prices[i] + solve(i + 1, prices, txnNo + 1, k),
                    0 + solve(i + 1, prices, txnNo, k));
        }
        return dp[i][txnNo] = ans;
    }

    public int maxProfit(int k, int[] prices) {
        /*
            using recursion + memoization
            TC : O(2*n*k)
            SC : O(n*2*k) + O(n) auxilary stack space
        */

        n = prices.length;
        dp = new Integer[n][2*k];
        return solve(0, prices, 0, k);
    }
}


class Solution {
    public int maxProfit(int k, int[] prices) {
        /*
            using tabulation
            TC : O(n*2*k)
            SC : O(n*2*k)
        */

        int n = prices.length;

        int[][] dp = new int[n + 1][2 * k + 1];

        for (int i = n; i >= 0; i--) {
            for (int txnNo = 2 * k; txnNo >= 0; txnNo--) {
                int ans = 0;
                if (txnNo == 2 * k || i == n) {
                    ans = 0;
                } else if (txnNo % 2 == 0) {
                    // can only buy
                    ans = Math.max(-prices[i] + dp[i+1][txnNo+1],
                            0 + dp[i+1][txnNo]);
                } else {
                    // can only sell
                    ans = Math.max(prices[i] + dp[i+1][txnNo+1],
                            0 + dp[i+1][txnNo]);
                }
                dp[i][txnNo] = ans;
            }
        }
        return dp[0][0];
    }
}


class Solution {
    public int maxProfit(int k, int[] prices) {
        /*
            using tabulation + space-optimization
            TC : O(n*2*k)
            SC : O(2*k)
        */

        int n = prices.length;

        int[] next = new int[2 * k + 1];

        for (int i = n; i >= 0; i--) {
            int[] curr = new int[2 * k + 1];
            for (int txnNo = 2 * k; txnNo >= 0; txnNo--) {
                int ans = 0;
                if (txnNo == 2 * k || i == n) {
                    ans = 0;
                } else if (txnNo % 2 == 0) {
                    // can only buy
                    ans = Math.max(-prices[i] + next[txnNo+1],
                            0 + next[txnNo]);
                } else {
                    // can only sell
                    ans = Math.max(prices[i] + next[txnNo+1],
                            0 + next[txnNo]);
                }
                curr[txnNo] = ans;
            }
            next = curr;
        }
        
        return next[0];
    }
}