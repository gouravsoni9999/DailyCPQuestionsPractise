class Solution {
    int n;
    private int solve(int i,int[] prices,boolean buy){
        if(i == n){
            return 0;
        }

        int ans = 0;
        if(buy){
            int willBuy = -prices[i] + solve(i+1, prices, false); // now only sell
            int willNotBuy = 0 + solve(i+1, prices, true);
            ans = Math.max(willBuy, willNotBuy);
        }
        else{
            int willSell = prices[i];
            int willNotSell = 0 + solve(i+1, prices, false); // only sell
            ans = Math.max(willSell, willNotSell);
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
        return solve(0, prices, true);
    }
}

class Solution {
    int n;
    Integer[][] dp;

    private int solve(int i, int[] prices, int buy) {
        if (i == n) {
            return 0;
        }

        if (dp[i][buy] != null) {
            return dp[i][buy];
        }

        int ans = 0;
        if (buy == 1) {
            int willBuy = -prices[i] + solve(i + 1, prices, 0); // now only sell
            int willNotBuy = 0 + solve(i + 1, prices, 1);
            ans = Math.max(willBuy, willNotBuy);
        } else {
            int willSell = prices[i];
            int willNotSell = 0 + solve(i + 1, prices, 0); // only sell
            ans = Math.max(willSell, willNotSell);
        }
        return dp[i][buy] = ans;
    }

    public int maxProfit(int[] prices) {
        /*  
            using recursion + memoization
            TC : O(2*n)
            SC : O(2*n) + O(n) auxilary stack space
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
            TC : O(2 * n)
            SC : O(2 * n)
        */
        int n = prices.length;
        int[][] dp = new int[n+1][2];

        for(int i = n;i >= 0;i--){
            for(int j = 1;j >= 0;j--){
                if(i == n) 
                    dp[i][j] = 0;
                else if(j == 1){
                    int willBuy = -prices[i] + dp[i+1][0];
                    int willNotBuy = 0 + dp[i+1][1];
                    dp[i][j] = Math.max(willBuy, willNotBuy);
                }else{ // if (j == 0)
                    int willSell = prices[i];
                    int willNotSell = 0 + dp[i+1][0];
                    dp[i][j] = Math.max(willSell, willNotSell);
                }
            }
        }

        return dp[0][1];
    }
}

class Solution {
    public int maxProfit(int[] prices) {
        /*
            using tabulation + space-optimization
            TC : O(2 * n)
            SC : O(2) = O(1)
        */
        int n = prices.length;
        int[] next = new int[2];

        for(int i = n;i >= 0;i--){
            int[] curr = new int[2];
            for(int j = 1;j >= 0;j--){
                if(i == n) 
                    curr[j] = 0;
                else if(j == 1){
                    int willBuy = -prices[i] + next[0];
                    int willNotBuy = 0 + next[1];
                    curr[j] = Math.max(willBuy, willNotBuy);
                }else{ // if (j == 0)
                    int willSell = prices[i];
                    int willNotSell = 0 + next[0];
                    curr[j] = Math.max(willSell, willNotSell);
                }
            }
            next = curr;
        }

        return next[1];
    }
}

class Solution {
    public int maxProfit(int[] prices) {
        // TC : O(n)
        // SC : O(1)
        int n = prices.length;
        int currMinBuyingPrice = prices[0];
        int maxiProfit = 0;
        for(int i = 1;i < n;i++){
            int profit = prices[i] - currMinBuyingPrice;//selling-buying
            maxiProfit = Math.max(maxiProfit, profit);
            // there is a possiblity that this ith day has min buying price
            currMinBuyingPrice = Math.min(currMinBuyingPrice, prices[i]);
        }
        return maxiProfit;
    }
}

class Solution {
    public int maxProfit(int[] prices) {
        // TC : O(n^2)
        // SC : O(1)
        int n = prices.length;
        int profit = Integer.MIN_VALUE;
        for(int i = 0;i < n;i++){
            int currProfit = -prices[i];//buyed that stock on ith day
            for(int j = i+1;j < n;j++){
                // sold stock on jth day
                profit = Math.max(profit, currProfit+prices[j]);
            }
        }
        return (profit < 0) ? 0 : profit;
    }
}
