class Solution {
    int m;
    int n;

    private int solve(int i, int j, String s, String t) {
        if (j == n) {
            return 1; // one subsequence found
        }
        if (i == m) {
            return 0; // the whole subsequence was not found as j < n
        }

        if (s.charAt(i) == t.charAt(j)) {
            int take = solve(i + 1, j + 1, s, t);
            int notTake = solve(i + 1, j, s, t);
            return take + notTake;
        } else {
            // if characters not match, shrink s and try to find the full t
            return solve(i + 1, j, s, t);
        }
    }

    public int numDistinct(String s, String t) {
        /*
         * using recursion
         * TC : exponential > O(2^(m+n))
         * SC : O(m+n)
         */
        m = s.length();
        n = t.length();
        return solve(0, 0, s, t); // count no. of distinct subsequences t in s
    }
}

class Solution {
    int m;
    int n;
    int[][] dp;

    private int solve(int i, int j, String s, String t) {
        if (j == n) {
            return 1; // one subsequence found
        }
        if (i == m) {
            return 0; // the whole subsequence was not found as j < n
        }

        if (dp[i][j] != -1)
            return dp[i][j];

        int ans = 0;

        if (s.charAt(i) == t.charAt(j)) {
            int take = solve(i + 1, j + 1, s, t);
            int notTake = solve(i + 1, j, s, t);
            ans = take + notTake;
        } else {
            // if characters not match, shrink s and try to find the full t
            ans = solve(i + 1, j, s, t);
        }

        return dp[i][j] = ans;
    }

    public int numDistinct(String s, String t) {
        /*
         * using recursion + memoization
         * TC : O(m*n)
         * SC : O(m*n)+O(m+n) auxilary stack space
         */
        m = s.length();
        n = t.length();
        dp = new int[m][n];
        for (int[] arr : dp) {
            Arrays.fill(arr, -1);
        }
        return solve(0, 0, s, t); // count no. of distinct subsequences t in s
    }
}

class Solution {
    public int numDistinct(String s, String t) {
        int m = s.length();
        int n = t.length();

        /*
         * using tabulation
         * TC : O(m*n)
         * SC : O(m*n)
         */

        int dp[][] = new int[m + 1][n + 1];

        for (int i = m; i >= 0; i--) {
            for (int j = n; j >= 0; j--) {
                int ans = 0;

                if (j == n) {
                    ans = 1;
                } else if (i == m) {
                    ans = 0;
                } else if (s.charAt(i) == t.charAt(j)) {
                    int take = dp[i + 1][j + 1];
                    int notTake = dp[i + 1][j];
                    ans = take + notTake;
                } else {
                    // not matching
                    ans = dp[i + 1][j];
                }

                dp[i][j] = ans;
            }
        }
        return dp[0][0];
    }
}

class Solution {
    public int numDistinct(String s, String t) {
        int m = s.length();
        int n = t.length();

        /*
         * using tabulation + space-optimization
         * TC : O(m*n)
         * SC : O(n)
         */

        int[] next = new int[n + 1];

        for (int i = m; i >= 0; i--) {
            int[] curr = new int[n + 1];
            for (int j = n; j >= 0; j--) {
                int ans = 0;

                if (j == n) {
                    ans = 1;
                } else if (i == m) {
                    ans = 0;
                } else if (s.charAt(i) == t.charAt(j)) {
                    int take = next[j + 1];
                    int notTake = next[j];
                    ans = take + notTake;
                } else {
                    // not matching
                    ans = next[j];
                }

                curr[j] = ans;
            }
            next = curr;
        }
        return next[0];
    }
}

class Solution {
    public int numDistinct(String s, String t) {
        int m = s.length();
        int n = t.length();

        /*
            using tabulation + space-optimization
            TC : O(m*n)
            SC : O(n)
        */

        int[] next = new int[n + 1];

        for (int i = m; i >= 0; i--) {
            // int[] curr = new int[n + 1];
            for (int j = 0; j <= n; j++) {
                int ans = 0;

                if (j == n) {
                    ans = 1;
                } else if (i == m) {
                    ans = 0;
                } else if (s.charAt(i) == t.charAt(j)) {
                    int take = next[j + 1];
                    int notTake = next[j];
                    ans = take + notTake;
                } else {
                    // not matching
                    ans = next[j];
                }

                next[j] = ans;
            }
            // next = curr;
        }
        return next[0];
    }
}