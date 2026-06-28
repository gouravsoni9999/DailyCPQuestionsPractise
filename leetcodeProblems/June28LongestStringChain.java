import java.util.*;

class Solution {
    private boolean checkPossible(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        if (m != n + 1)
            return false;
        int i = 0;
        int j = 0;
        while (i < m) {
            // as m > n: iterate till i gets exhausted
            if (j < n && s1.charAt(i) == s2.charAt(j)) {
                i++;
                j++;
            } else {
                i++;
            }
        }
        if (i == m && j == n)
            return true;
        return false;
    }

    public int longestStrChain(String[] words) {
        // sorting based on length
        Arrays.sort(words, (a, b) -> {
            return Integer.compare(a.length(), b.length());
        });
        int n = words.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int maxi = 1;// for ["a"] test case to work, as its output should be 1
        for (int i = 1; i < n; i++) {
            for (int prevIdx = 0; prevIdx < i; prevIdx++) {
                if (checkPossible(words[i], words[prevIdx]) && (dp[prevIdx] + 1 > dp[i])) {
                    dp[i] = 1 + dp[prevIdx];
                }
            }
            if (dp[i] > maxi) {
                maxi = dp[i];
            }
        }
        return maxi;
    }
}