class Solution {
    int n;
    int solve(int i,int[] height){
        if(i == n-1) // cost for moving from n-1 to n-1 
            return 0;
        // frog took only single jump
        int oneJump = solve(i+1, height) + Math.abs(height[i]-height[i+1]);
        // frog took 2 jumps
        int twoJumps = (i == n-2)?Integer.MAX_VALUE:solve(i+2, height) + Math.abs(height[i]-height[i+2]);
        int ans = Math.min(oneJump, twoJumps);
        return ans;
    }
    int minCost(int[] height) {
        // code here
	// Simple recursive approach
	// TC : O(2^n)
	// SC : O(n)
        n = height.length;
        return solve(0, height);
    }
}

class Solution {
    int n;
    int[] dp;
    int solve(int i,int[] height){
        if(i == n-1) // cost for moving from n-1 to n-1 
            return 0;
        if(dp[i] != -1) return dp[i];
        // frog took only single jump
        int oneJump = solve(i+1, height) + Math.abs(height[i]-height[i+1]);
        // frog took 2 jumps
        int twoJumps = (i == n-2)?Integer.MAX_VALUE:solve(i+2, height) + Math.abs(height[i]-height[i+2]);
        int ans = Math.min(oneJump, twoJumps);
        return dp[i] = ans;
    }
    int minCost(int[] height) {
        // code here
	// Recursion + Memoization
	// TC : O(n)
	// SC : O(n)+O(n)
        n = height.length;
        dp = new int[n+1];
        Arrays.fill(dp, -1);
        return solve(0, height);
    }
}

class Solution {
    int minCost(int[] height) {
        // code here
	// TC : O(n)
	// SC : O(n)
        if(height.length == 1) return 0;
        // using tabulation
        int n = height.length;
        int[] dp = new int[n];
        // dp[i] = min. total cost required for reaching from i to n-1
        dp[n-1] = 0;
        dp[n-2] = Math.abs(height[n-2]-height[n-1]);
        for(int i = n-3;i >= 0;i--){
            int singleJump = Math.abs(height[i+1]-height[i]) + dp[i+1];
            int doubleJump = Math.abs(height[i+2]-height[i]) + dp[i+2];
            dp[i] = Math.min(singleJump, doubleJump);
        }
        return dp[0];
    }
}

class Solution {
    int minCost(int[] height) {
        // code here
        // TC : O(n)
        // SC : O(1)
        if(height.length == 1) return 0;
        // using tabulation + space optimization
        int n = height.length;
        int[] prev = new int[2];
        prev[1] = 0;
        prev[0] = Math.abs(height[n-2]-height[n-1]);
        for(int i = n-3;i >= 0;i--){
            int singleJump = Math.abs(height[i+1]-height[i]) + prev[0];
            int doubleJump = Math.abs(height[i+2]-height[i]) + prev[1];
            int curr = Math.min(singleJump, doubleJump);
            prev[1] = prev[0];
            prev[0] = curr;
        }
        return prev[0];
    }
}
