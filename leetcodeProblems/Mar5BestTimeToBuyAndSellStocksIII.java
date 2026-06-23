class Solution {
    int n;

    private int solve(int i, int[] prices, int buy, int txnNo) {
        if (txnNo == 3 || i == n) {
            return 0; // only 2 txns allowed
        }
        int ans = 0;
        if (buy == 1) {
            int willBuy = -prices[i] + solve(i + 1, prices, 0, txnNo);
            int willNotBuy = 0 + solve(i + 1, prices, 1, txnNo);
            ans = Math.max(willBuy, willNotBuy);
        } else {
            int willSell = prices[i] + solve(i + 1, prices, 1, txnNo + 1);
            int willNotSell = 0 + solve(i + 1, prices, 0, txnNo);
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
        return solve(0, prices, 1, 1);
    }
}

class Solution {
    int n;
    Integer[][][] dp;

    private int solve(int i, int[] prices, int buy, int txnNo) {
        if (txnNo == 2 || i == n) {
            return 0; // only 2 txns allowed
        }
        int ans = 0;
        if(dp[i][buy][txnNo] != null)  
            return dp[i][buy][txnNo];
        if (buy == 1) {
            int willBuy = -prices[i] + solve(i + 1, prices, 0, txnNo);
            int willNotBuy = 0 + solve(i + 1, prices, 1, txnNo);
            ans = Math.max(willBuy, willNotBuy);
        } else {
            int willSell = prices[i] + solve(i + 1, prices, 1, txnNo + 1);
            int willNotSell = 0 + solve(i + 1, prices, 0, txnNo);
            ans = Math.max(willSell, willNotSell);
        }
        return dp[i][buy][txnNo] = ans;
    }

    public int maxProfit(int[] prices) {
        /*
            using recursion + memoization
            TC : O(n*2*2)
            SC : O(n*2*2) + O(n) auxilary stack space
        */
        n = prices.length;
        dp = new Integer[n][2][2];
        return solve(0, prices, 1, 0);
    }
}

class Solution {
    public int maxProfit(int[] prices) {
        /*
            using tabulation
            TC : O(n*2*3)
            SC : O(n*2*3)
        */
        int n = prices.length;
        int[][][] dp = new int[n + 1][2][3];

        for (int i = n; i >= 0; i--) {
            for (int buy = 1; buy >= 0; buy--) {
                for (int txnNo = 2; txnNo >= 0; txnNo--) {
                    int ans = 0;
                    if (txnNo == 2 || i == n) {
                        ans = 0;
                    } else if (buy == 1) {
                        int willBuy = -prices[i] + dp[i+1][0][txnNo];
                        int willNotBuy = 0 + dp[i+1][1][txnNo];
                        ans = Math.max(willBuy, willNotBuy);
                    } else {
                        int willSell = prices[i] + dp[i+1][1][txnNo+1];
                        int willNotSell = 0 + dp[i+1][0][txnNo];
                        ans = Math.max(willSell, willNotSell);
                    }
                    dp[i][buy][txnNo] = ans;
                }
            }
        }

        return dp[0][1][0];
    }
}


class Solution {
    public int maxProfit(int[] prices) {
        /*
            using tabulation + space-optimization
            TC : O(n*2*3) ~ O(n)
            SC : O(6) ~ O(1)
        */
        int n = prices.length;
        int[][] next = new int[2][3];

        for (int i = n; i >= 0; i--) {
            int[][] curr = new int[2][3];
            for (int buy = 1; buy >= 0; buy--) {
                for (int txnNo = 2; txnNo >= 0; txnNo--) {
                    int ans = 0;
                    if (txnNo == 2 || i == n) {
                        ans = 0;
                    } else if (buy == 1) {
                        int willBuy = -prices[i] + next[0][txnNo];
                        int willNotBuy = 0 + next[1][txnNo];
                        ans = Math.max(willBuy, willNotBuy);
                    } else {
                        int willSell = prices[i] + next[1][txnNo+1];
                        int willNotSell = 0 + next[0][txnNo];
                        ans = Math.max(willSell, willNotSell);
                    }
                    curr[buy][txnNo] = ans;
                }
            }
            next = curr;
        }

        return next[1][0];
    }
}


class Solution {
    int n;
    Integer[][] dp;
    private int solve(int i,int[] prices,int txnNo){
        if(i == n || txnNo == 4){
            return 0;
        }
        if(dp[i][txnNo] != null)
            return dp[i][txnNo];
        int ans = 0;
        if(txnNo % 2 == 0){
            // explore possibilities of buy
            ans = Math.max(-prices[i] + solve(i+1, prices, txnNo+1), 0 + solve(i+1, prices, txnNo));
        }else{
            // explore possiblities of sell
            ans = Math.max(prices[i] + solve(i+1, prices, txnNo+1), 0 + solve(i+1, prices, txnNo));
        }
        return dp[i][txnNo] = ans;
    }
    public int maxProfit(int[] prices) {
        /*
            concept (i, txnNo)
            txnNo -> only 4 (0,1,2,3)
            txnNo%2 == 0 ie even -> can only explore buy
            else odd -> can only explore sell
            using recursion + memoization
            TC : O(n * 4)
            SC : O(n * 4) + O(n) auxilary stack space
        */
        n = prices.length;
        dp = new Integer[n][4];
        return solve(0, prices, 0);
    }
}


class Solution {
    public int maxProfit(int[] prices) {
        /*
            using tabulation
            TC : O(n * 4)
            SC : O(n * 4)
        */
        int n = prices.length;
        int[][] dp = new int[n + 1][5];

        for (int i = n; i >= 0; i--) {
            for (int txnNo = 4; txnNo >= 0; txnNo--) {
                int ans = 0;
                if (i == n || txnNo == 4) {
                    ans = 0;
                } else if (txnNo % 2 == 0) {
                    // explore possibilities of buy
                    ans = Math.max(-prices[i] + dp[i+1][txnNo+1], 0 + dp[i+1][txnNo]);
                } else {
                    // explore possiblities of sell
                    ans = Math.max(prices[i] + dp[i+1][txnNo+1], 0 + dp[i+1][txnNo]);
                }
                dp[i][txnNo] = ans;
            }
        }

        return dp[0][0];
    }
}


class Solution {
    public int maxProfit(int[] prices) {
        /*
            using tabulation + space-optimization
            TC : O(n * 4)   ~ O(n)
            SC : O(4)       ~ O(1)
        */
        int n = prices.length;
        int[] next = new int[5];

        for (int i = n; i >= 0; i--) {
            int[] curr = new int[5];
            for (int txnNo = 4; txnNo >= 0; txnNo--) {
                int ans = 0;
                if (i == n || txnNo == 4) {
                    ans = 0;
                } else if (txnNo % 2 == 0) {
                    // explore possibilities of buy
                    ans = Math.max(-prices[i] + next[txnNo+1], 0 + next[txnNo]);
                } else {
                    // explore possiblities of sell
                    ans = Math.max(prices[i] + next[txnNo+1], 0 + next[txnNo]);
                }
                curr[txnNo] = ans;
            }
            next = curr;
        }

        return next[0];
    }
}