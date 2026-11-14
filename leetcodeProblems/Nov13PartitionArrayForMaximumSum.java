class MemoizedSolution {
    int n;
    Integer[] dp;

    private int solve(int i, int[] arr, int k) {
        if (i == n) {
            // reached end, no partition sum now
            return 0;
        }

        if (dp[i] != null)
            return dp[i];

        int len = 0; // stores length of particular partition
        int maxAns = 0; // the actual ans
        int maxi = -1; // the max. element in that partition
        for (int j = i; j < Math.min(n, i + k); j++) {
            len++; // partition length has increased
            maxi = Math.max(maxi, arr[j]);
            int sum = len * maxi + solve(j + 1, arr, k);
            maxAns = Math.max(maxAns, sum);
        }
        return dp[i] = maxAns;
    }

    public int maxSumAfterPartitioning(int[] arr, int k) {
        // TC : O(n.k)
        // SC : O(n) + O(n)(auxilary stack space)
        n = arr.length;
        dp = new Integer[n];
        return solve(0, arr, k);
    }
}

class TabulatedSolution {

    public int maxSumAfterPartitioning(int[] arr, int k) {
        // TC : O(n.k)
        // SC : O(n)
        int n = arr.length;
        int[] dp = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            int val = 0;
            if (i == n) {
                // reached end, no partition sum now
                val = 0;
            } else {

                int len = 0; // stores length of particular partition
                int maxAns = 0; // the actual ans
                int maxi = -1; // the max. element in that partition
                for (int j = i; j < Math.min(n, i + k); j++) {
                    len++; // partition length has increased
                    maxi = Math.max(maxi, arr[j]);
                    int sum = len * maxi + (j + 1 < n ? dp[j + 1] : 0);
                    maxAns = Math.max(maxAns, sum);
                }
                val = maxAns;
            }
            dp[i] = val;
        }
        return dp[0];
    }
}