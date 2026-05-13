class Solution {
    public boolean canJump(int[] nums) {
        // TC : O(n)
        // SC : O(1)
        int n = nums.length;
        // greedy approach
        int maxIdx = 0;//max. idx reached so far
        for(int i = 0;i < n;i++){
            if(i > maxIdx)
                // maxIdx I can reach is only maxIdx,
                // how come i became greater than that?
                // therefore return false, becoz I can't go
                // to i
                return false;
            maxIdx = Math.max(maxIdx, i+nums[i]);
        }
        return true;
    }
}

class Solution {
    int n;
    private boolean solve(int i,int[] nums){
        if(i >= n-1) return true;
        for(int x = 1;x <= nums[i];x++){
            if(solve(i+x, nums)) 
                return true;
        }
        return false;
    }
    public boolean canJump(int[] nums) {
        // using recursion
        // TC : O(k^n) where k is the avg. of all elements in nums
        // SC : O(n) (system stack space)
        n = nums.length;
        return solve(0, nums);
    }
}

class Solution {
    int n;
    Boolean[] dp;
    private boolean solve(int i,int[] nums){
        if(i >= n-1) return true;
        if(dp[i] != null) return dp[i];
        for(int x = 1;x <= nums[i];x++){
            if(solve(i+x, nums) == true) 
                return dp[i] = true;
        }
        return dp[i] = false;
    }
    public boolean canJump(int[] nums) {
        // using recursion + memoization
        // TC : O(n^2) // as worst case (due to for loop)
        // SC : O(n) (system stack space)
        n = nums.length;
        dp = new Boolean[n];
        return solve(0, nums);
    }
}

class Solution {
    public boolean canJump(int[] nums) {
        // using tabulation 
        // TC : O(n^2)
        // SC : O(1)
        int n = nums.length;
        boolean[] dp = new boolean[n];
        // dp[i] = can I reach from idx 0 to i?
        dp[0] = true;// I am already at 0 and my destination was also 0
        for(int i = 1;i < n;i++){
            for(int j = i-1;j >= 0;j--){
                if((dp[j] == true) && (j+nums[j] >= i)){
                    // if can go from 0 to j and then from j to i
                    // then it is possible
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[n-1];
    }
}

