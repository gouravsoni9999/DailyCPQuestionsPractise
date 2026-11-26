import java.util.*;

class MemoizedSolution {
    int n;
    Integer[] dp;

    private int solve(int idx, int[] arr, int diff) {
        if (idx == n)
            return 0; // base case

        if (dp[idx] != null)
            return dp[idx];

        int res = 0;
        int prev = arr[idx];
        for (int j = idx + 1; j < n; j++) {
            int curr = arr[j];
            if (curr - prev == diff) {
                res = Math.max(res, 1 + solve(j, arr, diff));
            }
        }

        return dp[idx] = res;
    }

    public int longestSubsequence(int[] arr, int difference) {
        // TLE
        // TC : O(n^2)
        // SC : O(n)
        int res = 0;
        n = arr.length;
        dp = new Integer[n];
        for (int i = 0; i < n; i++) {
            res = Math.max(res, 1 + solve(i, arr, difference));
        }
        return res;
    }
}

class DPAndHashingSolution {
    public int longestSubsequence(int[] arr, int difference) {
        // using DP + Hashing
        // TC & SC : O(n)
        Map<Integer, Integer> dp = new HashMap<>(); // {element -> subsequence length}
        int longestSubseqLength = 0;
        for (int ele : arr) {
            dp.put(ele, dp.getOrDefault(ele - difference, 0) + 1);
            longestSubseqLength = Math.max(longestSubseqLength, dp.get(ele));
        }
        return longestSubseqLength;
    }
}