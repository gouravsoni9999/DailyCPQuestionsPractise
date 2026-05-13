class Solution {
    int n;
    private int solve(int i,int[] nums){
        if(i >= n) return Integer.MAX_VALUE;
        if(i == n-1) return 0;// to reach from n-1 to n-1 takes 0 jumps

        int minJumps = Integer.MAX_VALUE;
        for(int jumps = 1;jumps <= nums[i];jumps++){
            int foundMinJumps = solve(i + jumps, nums);
            if(foundMinJumps != Integer.MAX_VALUE)
                minJumps = Math.min(minJumps, foundMinJumps+1);
        }
        return minJumps;
    }
    public int jump(int[] nums) {
        // using recursion
        // TC : O(k^n) where k is the avg of all elements
        // SC : O(n) due to system stack space
        n = nums.length;
        return solve(0, nums);
    }
}

class Solution {
    int n;
    int[] dp;
    private int solve(int i,int[] nums){
        if(i >= n) return Integer.MAX_VALUE;
        if(i == n-1) return 0;// to reach from n-1 to n-1 takes 0 jumps
        if(dp[i] != -1) return dp[i];
        int minJumps = Integer.MAX_VALUE;
        for(int jumps = 1;jumps <= nums[i];jumps++){
            int foundMinJumps = solve(i + jumps, nums);
            if(foundMinJumps != Integer.MAX_VALUE)
                minJumps = Math.min(minJumps, foundMinJumps+1);
        }
        return dp[i] = minJumps;
    }
    public int jump(int[] nums) {
        // using recursion + memoization
        // TC : O(n^2)
        // SC : O(n) due to system stack space
        n = nums.length;
        dp = new int[n];
        Arrays.fill(dp, -1);
        return solve(0, nums);
    }
}

class Solution {
    public int jump(int[] nums) {
        // using bottom-up DP
        // TC : O(n^2)
        // SC : O(n)
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, Integer.MAX_VALUE);
        // dp[i] = min. jumps from 0 to i
        dp[0] = 0;
        for (int i = 0; i < n; i++) {
            if (dp[i] == Integer.MAX_VALUE)
                // can't reach that index
                continue;
            int maxReach = Math.min(n - 1, i + nums[i]);
            for (int j = i+1; j <= maxReach; j++) {
                if(dp[i] != Integer.MAX_VALUE)
                    dp[j] = Math.min(dp[j], dp[i] + 1);
            }
        }
        return dp[n - 1];
    }
}
