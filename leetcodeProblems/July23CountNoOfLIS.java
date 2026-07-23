import java.util.*;
class Solution {
    public int findNumberOfLIS(int[] nums) {
        int n = nums.length;
        // TC : O(n^2)
        // SC : O(n)
        // lis array
        int[] dp = new int[n]; // contains the length of lis till index i
        Arrays.fill(dp, 1); // every element is itself a subsequence
        int[] cnt = new int[n]; // stores the cnt of subsequence ending at i of length lis[i]
        Arrays.fill(cnt, 1); // since every element is itself a subsequence, therefore cnt[i] is also 1

        // using lis code
        for(int i = 0;i < n;i++){
            // check prev elements
            for(int prev = 0;prev < i;prev++){
                if(nums[prev] < nums[i]){
                    // prev can be the part of lis
                    if(dp[prev]+1 > dp[i]){
                        // update dp[i]
                        dp[i] = dp[prev]+1; // new length of lis has increased
                        cnt[i] = cnt[prev]; // the counts of lis of length dp[prev]+1 is same now, only attach this new element
                    }else if(dp[prev]+1 == dp[i]){
                        // same lis
                        // add the prev cnt of lis to this element
                        cnt[i] += cnt[prev];
                    }
                }
            }
        }

        // find maxLen
        int maxLen = 0;
        for(int i = 0;i < n;i++){
            maxLen = Math.max(maxLen, dp[i]);
        }
        // sum count of LIS having maxLen
        int sum = 0;
        for(int i = 0;i < n;i++){
            if(dp[i] == maxLen){
                sum += cnt[i]; 
            }
        }
        return sum;
    }
}