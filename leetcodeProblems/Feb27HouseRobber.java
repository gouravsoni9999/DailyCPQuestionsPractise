class Solution {
    int[] dp;
    int n;
    private int solve(int[] nums,int idx){
        if (idx >= n) return 0;
        if (dp[idx] != -1) return dp[idx];
        int steal = nums[idx] + solve(nums, idx+2);// as I can't rob adj. house
        int skip = 0 + solve(nums, idx+1);// now I can rob adj. house
        dp[idx] = Math.max(steal, skip);
        return dp[idx];
    }
    public int rob(int[] nums) {
        // recursion+memoization solution
        n = nums.length;
        dp = new int[n+1];
        Arrays.fill(dp, -1);
        return solve(nums, 0);
    }
}
class Solution {
    int n;
    int[] dp;
    public int rob(int[] nums) {
        // tabulation
        n = nums.length;
        if (n == 1) return nums[0];
        if (n == 2) return Math.max(nums[0], nums[1]);
        dp = new int[n];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0],nums[1]);// loot either current or previous(not both)
        // dp[i] -> till ith house, max. loot I can perform
        for(int i = 2;i < n;i++){
            int skip = 0 + dp[i-1];
            int steal = nums[i] + dp[i-2];
            dp[i] = Math.max(skip, steal);
        }
        return Math.max(dp[n-1], dp[n-2]);
    }
}

class Solution {
    public int rob(int[] nums) {
        // using tabulation + space optimization
        // TC : O(n)
        // SC : O(1)
        int n = nums.length;
        int[] dp = new int[2];
        // dp[i] = max. stealing from i+1 th house to n th house
        dp[0] = 0; // no n+1 th house exist
        dp[1] = 0; // no n+2 th house exist
        for(int i = n-1;i >= 0;i--){
            int steal = nums[i] + dp[1];
            int skip = 0 + dp[0];
            int curr = Math.max(steal, skip);
            dp[1] = dp[0];
            dp[0] = curr;
        }
        return dp[0];
    }
}
