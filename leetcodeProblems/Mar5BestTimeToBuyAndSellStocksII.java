class Solution {
    int n;
    private int solve(int i,int[] prices,int buy){
        if(i == n)
            return 0;
        int ans = 0;
        if(buy == 1){
            int willBuyOnThatDay = -prices[i] + solve(i+1, prices, 0); // can only sell now
            int willNotBuyThatDay = 0 + solve(i+1, prices, 1); // can only buy next day(not sell)
            ans = Math.max(willBuyOnThatDay, willNotBuyThatDay);
        }
        else{
            int willSellOnThatDay = prices[i] + solve(i+1, prices, 1); // now, I can buy only
            int willNotSellThatDay = 0 + solve(i+1, prices, 0); // can only sell next day(not buy)
            ans = Math.max(willSellOnThatDay, willNotSellThatDay);
        }
        return ans;
    }
    public int maxProfit(int[] prices) {
        /*
            using recursion
            TC : O(2^n)
            SC : O(n) auxilary stack space
        */
        n = prices.length;
        return solve(0, prices, 1); 
    }
}

class Solution {
    int n;
    Integer[][] dp;

    private int solve(int i, int[] prices, int buy) {
        if (i == n)
            return 0;
        int ans = 0;
        if (dp[i][buy] != null)
            return dp[i][buy];
        if (buy == 1) {
            int willBuyOnThatDay = -prices[i] + solve(i + 1, prices, 0); // can only sell now
            int willNotBuyThatDay = 0 + solve(i + 1, prices, 1); // can only buy next day(not sell)
            ans = Math.max(willBuyOnThatDay, willNotBuyThatDay);
        } else {
            int willSellOnThatDay = prices[i] + solve(i + 1, prices, 1); // now, I can buy only
            int willNotSellThatDay = 0 + solve(i + 1, prices, 0); // can only sell next day(not buy)
            ans = Math.max(willSellOnThatDay, willNotSellThatDay);
        }
        return dp[i][buy] = ans;
    }

    public int maxProfit(int[] prices) {
        /*
            using recursion + memoization
            TC : O(2 * n)
            SC : O(2 * n) + O(n) auxilary stack space
        */
        n = prices.length;
        dp = new Integer[n][2];
        return solve(0, prices, 1);
    }
}

class Solution {
    public int maxProfit(int[] prices) {
        /*
            using tabulation
            TC : O(2*n)
            SC : O(2*n)
        */
        int n = prices.length;
        int[][] dp = new int[n + 1][2];
        for (int i = n; i >= 0; i--) {
            for (int buy = 1; buy >= 0; buy--) {
                if (i == n) {
                    dp[i][buy] = 0;
                } else if (buy == 1) {
                    int willBuyOnThatDay = -prices[i] + dp[i+1][0]; // can only sell now
                    int willNotBuyThatDay = 0 + dp[i+1][1]; // can only buy next day(not sell)
                    dp[i][buy] = Math.max(willBuyOnThatDay, willNotBuyThatDay);
                } else {
                    int willSellOnThatDay = prices[i] + dp[i+1][1]; // now, I can buy only
                    int willNotSellThatDay = 0 + dp[i+1][0]; // can only sell next day(not buy)
                    dp[i][buy] = Math.max(willSellOnThatDay, willNotSellThatDay);
                }
            }
        }

        return dp[0][1];
    }
}

class Solution {
    public int maxProfit(int[] prices) {
        /*
            using tabulation + space optimization
            TC : O(2*n) ~ O(n)
            SC : O(2) ~   O(1)
        */
        int n = prices.length;
        int[] next = new int[2];
        for (int i = n; i >= 0; i--) {
            int[] curr = new int[2];
            for (int buy = 1; buy >= 0; buy--) {
                if (i == n) {
                    curr[buy] = 0;
                } else if (buy == 1) {
                    int willBuyOnThatDay = -prices[i] + next[0]; // can only sell now
                    int willNotBuyThatDay = 0 + next[1]; // can only buy next day(not sell)
                    curr[buy] = Math.max(willBuyOnThatDay, willNotBuyThatDay);
                } else {
                    int willSellOnThatDay = prices[i] + next[1]; // now, I can buy only
                    int willNotSellThatDay = 0 + next[0]; // can only sell next day(not buy)
                    curr[buy] = Math.max(willSellOnThatDay, willNotSellThatDay);
                }
            }
            next = curr;
        }
        return next[1];
    }
}