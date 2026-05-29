class Solution {
    int n;
    private int solve(int s,int e,int[] nums){
        if(s == e) return nums[e];
        if(s > e) return 0;
        int steal = nums[s] + solve(s+2, e, nums);
        int skip = 0 + solve(s+1, e, nums);
        return Math.max(steal, skip);
    }
    public int rob(int[] nums) {
        // as per hints
        // using recursive approach
        // TC : O(2^n)
        // SC : O(n)
        n = nums.length;
        if(n == 1) return nums[0];
        // since houses are present in circular manner
        // therefore 0th and n-1th house are adjacent
        // so steal would be max from (0 to n-2), (1 to n-1)
        return Math.max(solve(0, n-2, nums),solve(1, n-1, nums));
    }
}

class Solution {
    int n;
    int[] dp;
    private int solve(int s,int e,int[] nums){
        if(s == e) return nums[e];
        if(s > e) return 0;
        if(dp[s] != -1) return dp[s];
        int steal = nums[s] + solve(s+2, e, nums);
        int skip = 0 + solve(s+1, e, nums);
        return dp[s] = Math.max(steal, skip);
    }
    public int rob(int[] nums) {
        // as per hints
        // using recursion + memoization
        // TC : O(n)
        // SC : O(n) + O(n)
        n = nums.length;
        dp = new int[n];
        Arrays.fill(dp, -1);
        if(n == 1) return nums[0];
        // since houses are present in circular manner
        // therefore 0th and n-1th house are adjacent
        // so steal would be max from (0 to n-2), (1 to n-1)
        int ans1 = solve(0, n-2, nums);
        Arrays.fill(dp, -1); // reinitialize dp array
        int ans2 = solve(1, n-1, nums);
        return Math.max(ans1, ans2);
    }
}

class Solution {
    public int robLinear(int[] nums) {
        // using tabulation
        int n = nums.length;
        int[] dp = new int[n + 2];
        // dp[i] = max. stealing from i+1 th house to n th house
        dp[n] = 0; // no n+1 th house exist
        dp[n + 1] = 0; // no n+2 th house exist
        for (int i = n - 1; i >= 0; i--) {
            int steal = nums[i] + dp[i + 2];
            int skip = 0 + dp[i + 1];
            dp[i] = Math.max(steal, skip);
        }
        return dp[0];
    }

    public int rob(int[] nums) {
	// TC : O(n)
	// SC : O(n)
        int n = nums.length;
        if(n == 1) return nums[0]; // edge case
        // iterative : tabulation approach
        int[] temp = new int[n - 1];
        // fill only indices 0 to n-2
        for(int i = 0;i < n-1;i++){
            temp[i] = nums[i];
        }
        int ans1 = robLinear(temp);
        // fill only indices 1 to n-1
        for(int i = 1;i < n;i++){
            temp[i-1] = nums[i];
        }
        int ans2 = robLinear(temp);
        return Math.max(ans1, ans2);
    }
}

class Solution {
    public int robLinear(int[] nums,int start,int end) {
        // using tabulation
        int n = end-start+1;
        int[] dp = new int[2];
        // dp[i] = max. stealing from i+1 th house to n th house
        dp[0] = 0; // no n+1 th house exist
        dp[1] = 0; // no n+2 th house exist
        for (int i = end; i >= start; i--) {
            int steal = nums[i] + dp[1];
            int skip = 0 + dp[0];
            int curr = Math.max(steal, skip);
            dp[1] = dp[0];
            dp[0] = curr;
        }
        return dp[0];
    }

    public int rob(int[] nums) {
        // tabulation + space optimization approach
        // TC: O(n)
        // SC: O(1)
        int n = nums.length;
        if(n == 1) return nums[0];
        return Math.max(robLinear(nums, 0, n-2),robLinear(nums,1,n-1));
    }
}
