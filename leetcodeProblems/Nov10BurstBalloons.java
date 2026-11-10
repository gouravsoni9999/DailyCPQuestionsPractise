class RecursiveSolution {
    int n;
    int[] arr;
    Integer[][] dp;

    private int solve(int i, int j) {
        if (i > j)
            return 0; // invalid

        if (dp[i][j] != null) {
            return dp[i][j];
        }

        int maxi = Integer.MIN_VALUE;
        for (int ind = i; ind <= j; ind++) {
            int cost = arr[i - 1] * arr[ind] * arr[j + 1] + solve(i, ind - 1) + solve(ind + 1, j);
            maxi = Math.max(maxi, cost);
        }

        return dp[i][j] = maxi;
    }

    public int maxCoins(int[] nums) {
        // TC: O(n^3)
        // SC : O(n^2)
        n = nums.length;
        arr = new int[n + 2];
        arr[0] = 1;
        arr[n + 1] = 1;
        for (int i = 0; i < n; i++) {
            arr[i + 1] = nums[i];
        }
        dp = new Integer[n + 1][n + 1];
        return solve(1, n);
    }
}

class TabulatedSolution {
    int n;
    int[] arr;
    int[][] dp;

    public int maxCoins(int[] nums) {
        // TC : O(n^3)
        // SC : O(n^2)
        n = nums.length;
        arr = new int[n + 2];
        arr[0] = 1;
        arr[n + 1] = 1;
        for (int i = 0; i < n; i++)
            arr[i + 1] = nums[i];

        dp = new int[n + 2][n + 2];

        for (int i = n; i >= 1; i--) {
            for (int j = 1; j <= n; j++) {
                if (i > j)
                    dp[i][j] = 0;
                else {
                    int maxi = Integer.MIN_VALUE;
                    for (int ind = i; ind <= j; ind++) {
                        int cost = arr[i - 1] * arr[ind] * arr[j + 1] + dp[i][ind - 1] + dp[ind + 1][j];
                        maxi = Math.max(maxi, cost);
                    }
                    dp[i][j] = maxi;
                }
            }
        }
        return dp[1][n];
    }
}