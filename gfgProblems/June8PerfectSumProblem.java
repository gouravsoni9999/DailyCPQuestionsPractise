class Solution {
    int n;
    private int solve(int[] nums,int i,int target){
        if(target < 0){
            // base case: target can't be -ve
            return 0;
        }
        if(i == n){
            return (target == 0)?1:0;
        }
        int take = solve(nums, i+1, target-nums[i]);
        int not_take = solve(nums, i+1, target);
        return take + not_take;
    }
    // Function to calculate the number of subsets with a given sum
    public int perfectSum(int[] nums, int target) {
        // code here
	// using simple recursion
        // TC : O(2^n)
        // SC : O(n)
        n = nums.length;
        return solve(nums, 0, target);
    }
}

class Solution {
    int n;
    int[][] dp;
    private int solve(int[] nums,int i,int target){
        if(target < 0){
            // base case: target can't be -ve
            return 0;
        }
        if(i == n){
            return (target == 0)?1:0;
        }
        if(dp[i][target] != -1) return dp[i][target];
        int take = solve(nums, i+1, target-nums[i]);
        int not_take = solve(nums, i+1, target);
        return dp[i][target] = take + not_take;
    }
    // Function to calculate the number of subsets with a given sum
    public int perfectSum(int[] nums, int target) {
        // code here
        // using recursion + memoization
        // TC : O(n*target)
        // SC : O(n*target) + O(n) (stack space)
        n = nums.length;
        dp = new int[n][target+1];
        for(int[] arr: dp){
            Arrays.fill(arr, -1);
        }
        return solve(nums, 0, target);
    }
}

class Solution {
    // Function to calculate the number of subsets with a given sum
    public int perfectSum(int[] nums, int target) {
        // code here
        int n = nums.length;
        int[][] dp = new int[n+1][target+1];
        // using tabulation
        // TC : O(n*target)
        // SC : O(n*target)
        for(int i = n;i >= 0;i--){
            for(int k = target;k >=0;k--){
                if(k < 0){
                    // base case where target < 0
                    dp[i][k] = 0;
                }else if(i == n){
                    // base case where i is out-of-bounds
                    dp[i][0] = 1;
                    // rest all is 0, which is handled automatically
                }else{
                    int take = 0;
                    if(k-nums[i] >= 0){
                        take = dp[i+1][k-nums[i]];
                    }
                    int not_take = dp[i+1][k];
                    dp[i][k] = take + not_take;
                }
            }
        }
        return dp[0][target];
    }
}

class Solution {
    // Function to calculate the number of subsets with a given sum
    public int perfectSum(int[] nums, int target) {
        // code here
        int n = nums.length;
        int[] next = new int[target+1];
        // using tabulation + space-optimization
        // TC : O(n*target)
        // SC : O(target)
        for(int i = n;i >= 0;i--){
            int[] curr = new int[target+1];
            for(int k = target;k >=0;k--){
                if(k < 0){
                    // base case where target < 0
                    curr[k] = 0;
                }else if(i == n){
                    // base case where i is out-of-bounds
                    curr[0] = 1;
                    // rest all is 0, which is handled automatically
                }else{
                    int take = 0;
                    if(k-nums[i] >= 0){
                        take = next[k-nums[i]];
                    }
                    int not_take = next[k];
                    curr[k] = take + not_take;
                }
            }
            next = curr;
        }
        return next[target];
    }
}
