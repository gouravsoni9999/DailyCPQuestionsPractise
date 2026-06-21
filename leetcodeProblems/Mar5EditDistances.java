class Solution {
    int m;
    int n;

    private int solve(int i, int j, String word1, String word2) {
        if (j == n) {
            // all characters were found
            // delete all other characters
            int no_of_deletions = m - i;
            return no_of_deletions;
        }
        if (i == m) {
            // all characters were not found
            // insert all left characters
            int no_of_insertions = n - j;
            return no_of_insertions;
        }

        if (word1.charAt(i) == word2.charAt(j)) {
            return solve(i + 1, j + 1, word1, word2); // no operation needed here, as both chars are same
        }
        int insert = solve(i, j + 1, word1, word2);
        int delete = solve(i + 1, j, word1, word2);
        int replace = solve(i + 1, j + 1, word1, word2);

        return 1 + Math.min(insert, Math.min(delete, replace)); // will take min of all operations
    }

    public int minDistance(String word1, String word2) {
        /*
         * using recursion
         * TC : O(3^(m+n)) exponential
         * SC : O(m+n)
         */
        m = word1.length();
        n = word2.length();
        return solve(0, 0, word1, word2);
    }
}

class Solution {
    int m;
    int n;
    int[][] dp;

    private int solve(String word1, String word2, int i, int j) {
        if (j == n)
            return m - i;// delete m-i chars
        if (i == m)
            return n - j;// insert n-j chars
        if (dp[i][j] != -1)
            return dp[i][j];
        if (word1.charAt(i) == word2.charAt(j))
            return dp[i][j] = solve(word1, word2, i + 1, j + 1);
        int insert = 1 + solve(word1, word2, i, j + 1);
        int delete = 1 + solve(word1, word2, i + 1, j);
        int replace = 1 + solve(word1, word2, i + 1, j + 1);
        return dp[i][j] = Math.min(insert, Math.min(delete, replace));
    }

    public int minDistance(String word1, String word2) {
        // recursion + memoization
        // TC : O(m*n)
        // SC : O(m*n) + O(m+n)
        m = word1.length();
        n = word2.length();
        dp = new int[m][n];
        for (int[] row : dp)
            Arrays.fill(row, -1);
        return solve(word1, word2, 0, 0);
    }
}

class Solution {
    int m;
    int n;
    int[][] dp;

    public int minDistance(String word1, String word2) {
        /* 
            tabulation
            TC : O(m*n)
            SC : O(m*n)
        */
        m = word1.length();
        n = word2.length();
        dp = new int[m + 1][n + 1];
        // dp[i][j] till lens i,j in words, min. operations required to get
        // them same
        for (int row = 0; row <= m; row++) {
            dp[row][0] = row;
        }
        for (int col = 0; col <= n; col++) {
            dp[0][col] = col;
        }
        for (int row = 1; row <= m; row++) {
            for (int col = 1; col <= n; col++) {
                if (word1.charAt(row - 1) == word2.charAt(col - 1)) {
                    dp[row][col] = dp[row - 1][col - 1];
                } else {
                    int insert = 1 + dp[row][col - 1];
                    int replace = 1 + dp[row - 1][col - 1];
                    int delete = 1 + dp[row - 1][col];
                    dp[row][col] = Math.min(insert, Math.min(replace, delete));
                }
            }
        }
        return dp[m][n];
    }
}


class Solution {
    public int minDistance(String word1, String word2) {
        /*
            using tabulation + space-optimization
            TC : O(m*n)
            SC : O(n)
        */
        int m = word1.length();
        int n = word2.length();

        int[] next = new int[n+1];

        for(int i = m;i >= 0; i--){
            int[] curr = new int[n+1];
            for(int j = n;j >= 0;j--){
                if(j == n){
                    curr[j] = m - i;
                }else if(i == m){
                    curr[j] = n - j;
                }else if (word1.charAt(i) == word2.charAt(j)){
                    curr[j] = next[j+1];
                }else{
                    int insert = curr[j+1];
                    int delete = next[j];
                    int replace = next[j+1];
                    curr[j] = 1 + Math.min(insert, Math.min(delete, replace));
                }
            }
            next = curr;
        }

        return next[0];
    }
}