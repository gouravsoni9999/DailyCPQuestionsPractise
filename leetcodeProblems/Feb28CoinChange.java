class Solution {
    int n;
    int[] dp;

    private int solve(int[] coins, int amount) {
        // base case
        if (amount == 0)
            return 0;
        if (amount < 0)
            return Integer.MAX_VALUE;
        if (dp[amount] != -1)
            return dp[amount];
        int cnt = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int calcRest = solve(coins, amount - coins[i]);
            if (calcRest != Integer.MAX_VALUE)
                cnt = Math.min(1+calcRest, cnt);// 1 added to get that coin
        }
        return dp[amount] = cnt;
    }

    public int coinChange(int[] coins, int amount) {
        // using recursion + memoization
        n = coins.length;
        dp = new int[amount+1];
        Arrays.fill(dp, -1);
        int ans = solve(coins,amount);
        return ans == Integer.MAX_VALUE?-1:ans;
    }
}
class Solution {
    public int coinChange(int[] coins, int amount) {
        // using tabulation
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;//to get 0, we need 0 coins
        for (int i = 1; i <= amount; i++) {
            for (int val : coins) {
                int remAmt = i - val;
                // done to prevent integer overflow check if 
                // dp[remAmt] != Infinity
                if (remAmt >= 0 && dp[remAmt] != Integer.MAX_VALUE) {
                    //becoz -ve value should not be considered
                    dp[i] = Math.min(1 + dp[remAmt], dp[i]);
                    
                }
            }
        }
        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }
}

class Solution {
    int n;
    private int solve(int i,int[] coins,int amount){
        if(i >= n || amount < 0){
            return Integer.MAX_VALUE;
        }
        if(i == n-1){
            if(amount % coins[i] == 0){
                // divisible, is possible to make the coins
                return amount / coins[i];
            }else{
                return Integer.MAX_VALUE;
            }
        }
        int notTake = 0 + solve(i+1, coins, amount);
        int take = Integer.MAX_VALUE;
        int calc_rest_if_take = solve(i, coins, amount-coins[i]);
        if(coins[i] <= amount && calc_rest_if_take < Integer.MAX_VALUE){
            take = 1 + calc_rest_if_take; // since we have inf. supply of coins, we can take ith coin again next time
        }
        int ans = Math.min(take, notTake);
        return ans;
    }
    public int coinChange(int[] coins, int amount) {
        n = coins.length;
        /*
            using recursion
            TC : O(2 ^ n) exponential
            SC : O(n) or O(amount) 
        */
        int ans = solve(0, coins, amount);
        return (ans == Integer.MAX_VALUE) ? -1 : ans;
    }
}

class Solution {
    int n;
    int[][] dp;
    private int solve(int i,int[] coins,int amount){
        if(i >= n || amount < 0){
            return Integer.MAX_VALUE;
        }
        if(i == n-1){
            if(amount % coins[i] == 0){
                // divisible, is possible to make the coins
                return amount / coins[i];
            }else{
                return Integer.MAX_VALUE;
            }
        }
        if(dp[i][amount] != -1) return dp[i][amount];
        int notTake = 0 + solve(i+1, coins, amount);
        int take = Integer.MAX_VALUE;
        int calc_rest_if_take = solve(i, coins, amount-coins[i]);
        if(coins[i] <= amount && calc_rest_if_take < Integer.MAX_VALUE){
            take = 1 + calc_rest_if_take; // since we have inf. supply of coins, we can take ith coin again next time
        }
        dp[i][amount] = Math.min(take, notTake);
        return dp[i][amount];
    }
    public int coinChange(int[] coins, int amount) {
        n = coins.length;
        /*
            using recursion + memoization
            TC : O(n * amount) 
            SC : O(n * amount) + O(amount) stack space
        */
        dp = new int[n][amount+1];
        for(int[] arr: dp){
            Arrays.fill(arr, -1);
        }
        int ans = solve(0, coins, amount);
        return (ans == Integer.MAX_VALUE) ? -1 : ans;
    }
}

class Solution {
    int n;
    int[][] dp;
    private int solve(int i,int[] coins,int amount){
        if(amount == 0){
            return 0; // found a combo
        }
        if(i >= n || amount < 0){
            return Integer.MAX_VALUE;
        }
        if(dp[i][amount] != -1) return dp[i][amount];
        int notTake = 0 + solve(i+1, coins, amount);
        int take = Integer.MAX_VALUE;
        int calc_rest_if_take = solve(i, coins, amount-coins[i]);
        if(calc_rest_if_take < Integer.MAX_VALUE){
            take = 1 + calc_rest_if_take; // since we have inf. supply of coins, we can take ith coin again next time
        }
        dp[i][amount] = Math.min(take, notTake);
        return dp[i][amount];
    }
    public int coinChange(int[] coins, int amount) {
        n = coins.length;
        /*
            using recursion
            TC : O(2 ^ n) exponential
            SC : O(n) or O(amount) 
        */
        dp = new int[n][amount+1];
        for(int[] arr: dp){
            Arrays.fill(arr, -1);
        }
        int ans = solve(0, coins, amount);
        return (ans == Integer.MAX_VALUE) ? -1 : ans;
    }
}

class Solution {
    public int coinChange(int[] coins, int amount) {
        /*
            using tabulation
            TC : O(n * amount)
            SC : O(n * amount)
        */
        int n = coins.length;
        int[][] dp = new int[n + 1][amount + 1];
        for (int i = n; i >= 0; i--) {
            for (int target = 0; target <= amount; target++) {
                // handle base cases
                if (target == 0) {
                    dp[i][target] = 0;
                } else if (i == n) {
                    dp[i][target] = Integer.MAX_VALUE;
                } else {
                    int not_take = (i + 1 <= n) ? dp[i + 1][target] : Integer.MAX_VALUE;
                    int take = Integer.MAX_VALUE;
                    if (coins[i] <= target && dp[i][target - coins[i]] < Integer.MAX_VALUE) {
                        take = 1 + dp[i][target - coins[i]];
                    }
                    dp[i][target] = Math.min(take, not_take);
                }
            }
        }
        return (dp[0][amount] == Integer.MAX_VALUE) ? -1 : dp[0][amount];
    }
}

class Solution {
    public int coinChange(int[] coins, int amount) {
        /*
            using tabulation + space-optimization
            TC : O(n * amount)
            SC : O(amount)
        */
        int n = coins.length;
        int[] next = new int[amount + 1];
        for (int i = n; i >= 0; i--) {
            int[] curr = new int[amount + 1];
            for (int target = 0; target <= amount; target++) {
		// I iterated from 0 to amount, because of the relation
		// relation stated that curr[target] depends upon previously computed target values (not the next values)
                // handle base cases
                if (target == 0) {
                    curr[target] = 0;
                } else if (i == n) {
                    curr[target] = Integer.MAX_VALUE;
                } else {
                    int not_take = (i + 1 <= n) ? next[target] : Integer.MAX_VALUE;
                    int take = Integer.MAX_VALUE;
                    if (coins[i] <= target && curr[target - coins[i]] < Integer.MAX_VALUE) {
                        take = 1 + curr[target - coins[i]];
                    }
                    curr[target] = Math.min(take, not_take);
                }
            }
            next = curr.clone();
        }
        return (next[amount] == Integer.MAX_VALUE) ? -1 : next[amount];
    }
}
