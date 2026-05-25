// Memoization
class Solution {
    // TC : O(n)
    // SC : O(n) (auxilary space) + O(n) (stack space)
    public int fib(int n) {
        int[] dp = new int[n+1];
        Arrays.fill(dp, -1);
        return fib(n, dp);
    }
    private int fib(int n,int[] dp){
        if(n <= 1) return n;
        if(dp[n] != -1) return dp[n];
        return dp[n] = fib(n-1, dp) + fib(n-2, dp);
    }
}

// Tabulation
class Solution {
    public int fib(int n) {
	// TC : O(n)
	// SC : O(n) auxilary space
        if (n == 0) return n;
        int[] dp = new int[n+1];
        dp[0] = 0;
        dp[1] = 1;
        for(int i = 2;i <= n;i++){
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n];
    }
}

// Space optimized approach
class Solution {
    public int fib(int n) {
        // TC : O(N)
        // SC : O(1)
        if (n == 0) return n;
        int[] prev = new int[2];
        prev[0] = 0;
        prev[1] = 1;
        for(int i = 2;i <= n;i++){
            int curr = prev[0] + prev[1];
            prev[0] = prev[1];
            prev[1] = curr;
        }
        return prev[1];
    }
}
