class Solution {
    int n;
    Integer[][] dp;
    private int solve(int i,int[] prices,int buy){
        if(i >= n)
            return 0;
        
        if(dp[i][buy] != null) 
            return dp[i][buy];

        int ans = 0;

        if(buy == 1){
            int willBuy = -prices[i] + solve(i+1, prices, 0);
            int willNotBuy = 0 + solve(i+1, prices, 1);
            ans = Math.max(willBuy, willNotBuy);    
        }

        else{
            int willSell = prices[i] + solve(i+2, prices, 1); // now will buy on day after tommorow
            int willNotSell = 0 + solve(i+1, prices, 0);
            ans = Math.max(willSell, willNotSell);
        }
        return dp[i][buy] = ans;
    }
    public int maxProfit(int[] prices) {
        /*
            using recursion + memoization
            TC : O(2*n)
            SC : O(2*n) + O(n) stack space
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
                int ans = 0;
                if (i >= n)
                    ans = 0;
                else if (buy == 1) {
                    int willBuy = -prices[i] + dp[i + 1][0];
                    int willNotBuy = 0 + dp[i + 1][1];
                    ans = Math.max(willBuy, willNotBuy);
                }

                else {
                    int willSell = prices[i] + (((i + 2) >= n) ? 0 : dp[i + 2][1]); // now will buy on day after tommorow
                    int willNotSell = 0 + dp[i + 1][0];
                    ans = Math.max(willSell, willNotSell);
                }
                dp[i][buy] = ans;
            }
        }

        return dp[0][1];
    }
}

class Solution {
    public int maxProfit(int[] prices) {
        /*
            using tabulation + space-optimization
            TC : O(2*n) ~ O(n)
            SC : O(2*3)   ~ O(1)
        */
        int n = prices.length;
        int[] next_next = new int[2];
        int[] next = new int[2];
        int[] curr = new int[2];

        for (int i = n; i >= 0; i--) {
            for (int buy = 1; buy >= 0; buy--) {
                int ans = 0;
                if (i >= n)
                    ans = 0;
                else if (buy == 1) {
                    int willBuy = -prices[i] + next[0];
                    int willNotBuy = 0 + next[1];
                    ans = Math.max(willBuy, willNotBuy);
                }

                else {
                    int willSell = prices[i] + (((i + 2) >= n) ? 0 : next_next[1]); // now will buy on day after tommorow
                    int willNotSell = 0 + next[0];
                    ans = Math.max(willSell, willNotSell);
                }
                curr[buy] = ans;
            }
            next_next = next.clone();
            next = curr.clone();
        }

        return next[1];
    }
}