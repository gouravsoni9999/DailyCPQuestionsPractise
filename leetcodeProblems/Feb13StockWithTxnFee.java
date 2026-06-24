class Solution {
    int n;
    Integer[][] dp;
    private int solve(int i,int buy,int[] prices, int fee){
        
        if(i == n){
            return 0;
        }

        if(dp[i][buy] != null){
            return dp[i][buy];
        }  

        int ans = 0;
        
        if(buy == 1){
            ans = Math.max(-prices[i] + solve(i+1, 0, prices, fee), 0 + solve(i+1, 1, prices, fee));
        }else{
            int willSell = prices[i] - fee + solve(i+1, 1, prices, fee);
            int willNotSell = 0 + solve(i+1, 0, prices, fee);
            ans = Math.max(willSell, willNotSell);
        }
        return dp[i][buy] = ans;
    }
    public int maxProfit(int[] prices, int fee) {
        /*
            using recursion + memoization
            TC : O(2*n)
            SC : O(2*n) + O(n)
        */
        n = prices.length;
        dp = new Integer[n][2];
        return solve(0, 1, prices, fee);
    }
}


class Solution {
    public int maxProfit(int[] prices, int fee) {
        /*
            using tabulation
            TC : O(2*n)
            SC : O(2*n)
        */

        int n = prices.length;

        int[][] dp = new int[n + 1][2];

        for (int i = n; i >= 0; i--) {
            for (int buy = 1; buy >= 0; buy--) {
                int ans = 0;
                if (i == n) {
                    ans = 0;
                } else if (buy == 1) {
                    ans = Math.max(-prices[i] + dp[i+1][0], 0 + dp[i+1][1]);
                } else {
                    int willSell = prices[i] - fee + dp[i+1][1];
                    int willNotSell = 0 + dp[i+1][0];
                    ans = Math.max(willSell, willNotSell);
                }
                dp[i][buy] = ans;
            }
        }

        return dp[0][1];
    }
}

class Solution {
    public int maxProfit(int[] prices, int fee) {
        /*
            using tabulation + space-optimization
            TC : O(2*n) ~ O(n)
            SC : O(2)   ~ O(1)
        */

        int n = prices.length;

        int[] next = new int[2];

        for (int i = n; i >= 0; i--) {
            int[] curr = new int[2];
            for (int buy = 1; buy >= 0; buy--) {
                int ans = 0;
                if (i == n) {
                    ans = 0;
                } else if (buy == 1) {
                    ans = Math.max(-prices[i] + next[0], 0 + next[1]);
                } else {
                    int willSell = prices[i] - fee + next[1];
                    int willNotSell = 0 + next[0];
                    ans = Math.max(willSell, willNotSell);
                }
                curr[buy] = ans;
            }
            next = curr;
        }

        return next[1];
    }
}