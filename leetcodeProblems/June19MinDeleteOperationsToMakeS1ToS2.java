class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        /*
            using tabulation + space-optimization
            TC : O(m*n)
            SC : O(n)
        */
        int m = text1.length();
        int n = text2.length();
        int[] next = new int[n + 1];
        for (int i = m; i >= 0; i--) {
            int[] curr = new int[n + 1];
            for (int j = n; j >= 0; j--) {
                if (i == m || j == n) {
                    curr[j] = 0; // out-of-bounds
                } else if (text1.charAt(i) == text2.charAt(j)) {
                    curr[j] = 1 + next[j + 1];
                } else {
                    curr[j] = Math.max(next[j], curr[j + 1]);
                }
            }
            next = curr;
        }
        return next[0];
    }
    public int minDistance(String word1, String word2) {
        // find LCS of word1 and word2
        int lcs = longestCommonSubsequence(word1, word2);
        int m = word1.length();
        int n = word2.length();
        int deletionOperationsCnt = m + n - 2 * lcs;
        return deletionOperationsCnt;
    }
}
