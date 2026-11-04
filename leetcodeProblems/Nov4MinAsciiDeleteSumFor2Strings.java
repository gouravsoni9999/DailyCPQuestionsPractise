class MemoizedSolution {
    int m, n;
    Integer[][] dp;

    private int solve(int i, int j, String s1, String s2) {
        if (i == m && j == n) {
            // both strings are now equal
            return 0;
        }

        if (dp[i][j] != null)
            return dp[i][j];

        if (i == m) {
            // s2 string is left, delete all rem. s2 chars
            return dp[i][j] = s2.charAt(j) + solve(i, j + 1, s1, s2);
        }

        if (j == n) {
            // s1 string is left, delete all rem. s1 chars
            return dp[i][j] = s1.charAt(i) + solve(i + 1, j, s1, s2);
        }

        if (s1.charAt(i) == s2.charAt(j))
            // when both characters are equal
            return dp[i][j] = solve(i + 1, j + 1, s1, s2);

        // now both chars are not equal
        int val1 = s1.charAt(i) + solve(i + 1, j, s1, s2); // deleted ith character
        int val2 = s2.charAt(j) + solve(i, j + 1, s1, s2); // deleted jth character

        return dp[i][j] = Math.min(val1, val2);
    }

    public int minimumDeleteSum(String s1, String s2) {
        // TC and SC : O(m*n)
        m = s1.length();
        n = s2.length();
        dp = new Integer[m + 1][n + 1];
        return solve(0, 0, s1, s2);
    }
}

class TabulatedSolution {
    int m, n;
    int[][] dp;

    public int minimumDeleteSum(String s1, String s2) {
        // TC & SC : O(m*n)
        m = s1.length();
        n = s2.length();
        dp = new int[m + 1][n + 1];

        for (int i = m; i >= 0; i--) {
            for (int j = n; j >= 0; j--) {
                int ans = 0;
                if (i == m && j == n)
                    ans = 0;

                else if (i == m)
                    ans = s2.charAt(j) + dp[i][j + 1];

                else if (j == n)
                    ans = s1.charAt(i) + dp[i + 1][j];

                else if (s1.charAt(i) == s2.charAt(j))
                    ans = dp[i + 1][j + 1];

                else {
                    int val1 = s1.charAt(i) + dp[i + 1][j];
                    int val2 = s2.charAt(j) + dp[i][j + 1];
                    ans = Math.min(val1, val2);
                }
                dp[i][j] = ans;
            }
        }

        return dp[0][0];
    }
}

class SpaceOptimizedTabulatedSolution {
    int m, n;
    int[] next;

    public int minimumDeleteSum(String s1, String s2) {
        // TC : O(m*n)
        // SC : O(n)
        m = s1.length();
        n = s2.length();
        next = new int[n + 1];

        for (int i = m; i >= 0; i--) {
            int[] curr = new int[n + 1];
            for (int j = n; j >= 0; j--) {
                int ans = 0;
                if (i == m && j == n)
                    ans = 0;

                else if (i == m)
                    ans = s2.charAt(j) + curr[j + 1];

                else if (j == n)
                    ans = s1.charAt(i) + next[j];

                else if (s1.charAt(i) == s2.charAt(j))
                    ans = next[j + 1];

                else {
                    int val1 = s1.charAt(i) + next[j];
                    int val2 = s2.charAt(j) + curr[j + 1];
                    ans = Math.min(val1, val2);
                }
                curr[j] = ans;
            }
            next = curr;
        }

        return next[0];
    }
}