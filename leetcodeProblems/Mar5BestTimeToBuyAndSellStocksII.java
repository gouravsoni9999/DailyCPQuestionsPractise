class Solution {
    int n;
    int[][] dp;

    private int solve(int[] prices, int idx, boolean buy) {
        if (idx >= n)
            return 0;
        if (dp[idx][buy?1:0] != -1) return dp[idx][buy?1:0];
        int maxProfit = 0;
        if (buy) {
            // you can only buy
            int buyThatDay = -prices[idx] + solve(prices, idx + 1, false);
            int notBuyThatDay = 0 + solve(prices, idx + 1, true);
            maxProfit = Math.max(maxProfit, Math.max(buyThatDay, notBuyThatDay));
        } else {
            // you can only sell
            int sellThatDay = prices[idx] + solve(prices, idx + 1, true);
            int notSellThatDay = 0 + solve(prices, idx + 1, false);
            maxProfit = Math.max(maxProfit, Math.max(sellThatDay, notSellThatDay));
        }
        return dp[idx][buy?1:0] = maxProfit;
    }

    public int maxProfit(int[] prices) {
        // recursion + memoization
        n = prices.length;
        dp = new int[n][2];
        for(int[] arr: dp)
            Arrays.fill(arr, -1);
        return solve(prices, 0, true);
    }
}
class Solution {
    public int maxProfit(int[] prices) {
        // tabulation(copy of recursion)
        int n = prices.length;
        int[][] dp = new int[n+1][2];
        for(int i = 0;i<2;i++){
            dp[n][i] = 0;
        }
        for(int i = n-1;i>=0;i--){
            for(int j = 1;j>=0;j--){
                int maxProfit = 0;
                if (j == 1){
                    // can buy
                    int buyThatDay = -prices[i] + dp[i+1][0];
                    int notBuyThatDay = 0 + dp[i+1][1];
                    maxProfit = Math.max(maxProfit,Math.max(buyThatDay,notBuyThatDay));
                }
                else{
                    // cannot buy
                    int sellThatDay = prices[i] + dp[i+1][1];
                    int notSellThatDay = 0+dp[i+1][0];
                    maxProfit = Math.max(maxProfit,Math.max(sellThatDay,notSellThatDay));
                }
                dp[i][j] = maxProfit;
            }
        }
        return dp[0][1];
    }
}
class Solution {
    public int maxProfit(int[] prices) {
        // tabulation(copy of recursion)->space-optimization
	// TC : O(2*n)
	// SC : O(1)
        int n = prices.length;
        int[] ahead = new int[2];
        int[] curr  = new int[2];
        for(int i = 0;i<2;i++){
            ahead[i] = 0;
        }
        for(int i = n-1;i>=0;i--){
            for(int j = 1;j>=0;j--){
                int maxProfit = 0;
                if (j == 1){
                    // can buy
                    int buyThatDay = -prices[i] + ahead[0];
                    int notBuyThatDay = 0 + ahead[1];
                    maxProfit = Math.max(maxProfit,Math.max(buyThatDay,notBuyThatDay));
                }
                else{
                    // cannot buy
                    int sellThatDay = prices[i] + ahead[1];
                    int notSellThatDay = 0+ahead[0];
                    maxProfit = Math.max(maxProfit,Math.max(sellThatDay,notSellThatDay));
                }
                curr[j] = maxProfit;
            }
            ahead = curr;
        }
        return ahead[1];
    }
}

class Solution {
    int n;
    int[][] dp;

    private int solve(int[] prices, int i, int buy) {
        if (i == n)
            return 0;
        if (dp[i][buy] != -1)
            return dp[i][buy];
        int todayPrice = prices[i];
        int willBuy = 0, willNotBuy = 0, willSell = 0, willNotSell = 0;
        if (buy == 1) {
            // on this day I can buy or not buy
            willBuy = -todayPrice + solve(prices, i + 1, 0);//next day i can sell only
            willNotBuy = 0 + solve(prices, i + 1, 1);// next day I can buy only
        } else {
            // I cannot buy today, ie., I can sell today
            willSell = todayPrice + solve(prices, i + 1, 1);//next day I can buy only
            willNotSell = 0 + solve(prices, i + 1, 0);//next day I can sell only
        }
        return dp[i][buy] = Math.max(Math.max(willBuy, willNotBuy), Math.max(willSell, willNotSell));
    }

    public int maxProfit(int[] prices) {
        // using recursion + memoization
        // TC : O(2*n)
        // SC : O(n)
        n = prices.length;
        dp = new int[n][2];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 2; j++) {
                dp[i][j] = -1;
            }
        }
        return solve(prices, 0, 1);
    }
}
