import java.util.*;
class Solution {
    public static int longestBitonicSequence(int n, int[] nums) {
        // longest increasing subsequence using DP
        // TC : O(n^2)
        // SC : O(n)
        //dp1[i] stores the length of the Longest Increasing Subsequence (LIS) ending at i.
        int[] dp1 = new int[n];
        Arrays.fill(dp1, 1);
         
        for(int i = 0;i < n;i++){
            for(int prev = 0;prev < i;prev++){
                if(nums[prev] < nums[i] && 1 + dp1[prev] > dp1[i]){
                    dp1[i] = 1 + dp1[prev];
                }
            }
        }   
        
        //dp2[i] stores the length of the Longest Decreasing Subsequence (LDS) starting at i.
        int[] dp2 = new int[n];
        Arrays.fill(dp2,1);
        
        for(int i = n-1;i >= 0;i--){
            for(int next = n-1;next > i;next--){
                if(nums[next] < nums[i] && 1+dp2[next] > dp2[i]){
                    dp2[i] = 1 + dp2[next];
                }
            }
        }
        
        int maxi = 0; // Longest Bitonic Subseq
        for(int i = 0;i < n;i++){
            // only consider idx 'i' as a valid peek if it has both an increasing part and a decreasing part after it
            if(dp1[i] > 1 && dp2[i] > 1)
                maxi = Math.max(maxi, dp1[i] + dp2[i] - 1);// because 1 common element was counted twice
        }
        
        return maxi;
    }
}
