class Solution {
    int n;
    private int solve(int i,int[] heights,int k){
        if(i == n-1) return 0;
        int ans = Integer.MAX_VALUE;
        for(int dist = 1;dist <= k && i+dist < n;dist++){
            int currJump = Math.abs(heights[i]-heights[i+dist]) + solve(i+dist,heights, k);
            ans = Math.min(ans, currJump);
        }
        return ans;
    }
    public int frogJump(int[] heights, int k) {
	// using recursion
	// TC : O(k^n)
	// SC : O(n)
        n = heights.length;
        return solve(0, heights, k);
    }
}

class Solution {
    int n;
    int[] dp;
    private int solve(int i,int[] heights,int k){
        if(i == n-1) return 0;
        if(dp[i] != -1) return dp[i];
        int ans = Integer.MAX_VALUE;
        for(int dist = 1;dist <= k && i+dist < n;dist++){
            int currJump = Math.abs(heights[i]-heights[i+dist]) + solve(i+dist,heights, k);
            ans = Math.min(ans, currJump);
        }
        return dp[i] = ans;
    }
    public int frogJump(int[] heights, int k) {
	// using recursion + memoization
	// TC : O(n*k)
	// SC : O(n)+O(n)
        n = heights.length;
        dp = new int[n];
        Arrays.fill(dp, -1);
        return solve(0, heights, k);
    }
}

class Solution {
    public int frogJump(int[] heights, int k) {
	// using tabulation: 
	// TC : O(n*k)
	// SC : O(k)
        int n = heights.length;
        int[] dp = new int[n];
        dp[n-1] = 0;
        for(int i = n-2;i >= 0;i--){
            dp[i] = Integer.MAX_VALUE;
            for(int dist = 1;dist <= k && dist+i < n;dist++){
                int currJump = Math.abs(heights[i]-heights[i+dist])+dp[i+dist];
                dp[i] = Math.min(dp[i],currJump);
            }
        }
        return dp[0];
    }
}

class Solution {
    public int frogJump(int[] heights, int k) {
	// SC : O(k)
	// TC : O(n)
        int n = heights.length;
        LinkedList<Integer> nextKCosts = new LinkedList<>();
        nextKCosts.add(0); // for dp[n-1] = 0
        for(int i = n-2;i >= 0;i--){
            int currMin = Integer.MAX_VALUE;
            for(int dist = 1;dist <= k && dist+i < n;dist++){
                int jumpCost = Math.abs(heights[i]-heights[i+dist])+nextKCosts.get(dist-1);
                currMin = Math.min(currMin,jumpCost);
            }
            nextKCosts.addFirst(currMin); // add to front
            // maintain window size to max. k elements
            if(nextKCosts.size() > k){
                nextKCosts.removeLast();
            }
        }
        return nextKCosts.getFirst();
    }
}
