import java.util.*;

class Solution {
    int wordsLen;
    Integer[][] dp;
    private boolean isPredecessor(String s1, String s2){
        int i = 0;
        int j = 0;
        int m = s1.length();
        int n = s2.length();
        if(n != m+1) return false;
        while(j < n){
            if(i < m && s1.charAt(i) == s2.charAt(j)){
                i++;j++;
            }else{
                j++;
            }
        }
        return (i == m && j == n);
    }
    private int solve(int i,int prev,String[] words,int wordsLen){
        if(i == wordsLen){
            return 0;
        }
        int taken = 0, not_taken = 0;
        if(dp[i][prev+1] != null)
            return dp[i][prev+1];
        if(prev == -1 || isPredecessor(words[prev], words[i])){
            taken = 1 + solve(i+1, i, words, wordsLen);
        }
        not_taken = 0 + solve(i+1, prev, words, wordsLen);
        return dp[i][prev+1] = Math.max(taken, not_taken);
    }
    public int longestStrChain(String[] words) {
        // using memoization
        // TC : O(wlogw.l) + O(n^2 . l)
        // SC : O(w^2 + w.l)
        // where l is max. length of a string word 
        wordsLen = words.length;
        Arrays.sort(words, (a,b)->{
            return Integer.compare(a.length(), b.length());
        });
        dp = new Integer[wordsLen][wordsLen];
        return solve(0, -1, words, wordsLen);
    }
}


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
        // using tabulation
        // TC : O(n^2 . l)
        // SC : O(n^2 + n.l)
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