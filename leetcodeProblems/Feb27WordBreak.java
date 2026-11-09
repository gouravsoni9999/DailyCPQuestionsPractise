import java.util.*;
class MemoizedSolution {
    int n;
    Set<String> st;
    Boolean[] dp;
    private boolean solve(int idx, String s){
        if (idx >= n) // word completed
            return true;
        if (st.contains(s)){
            // if already s is present in wordDict
            return true;
        }
        if (dp[idx] != null) return dp[idx];
        for(int endIdx = idx+1;endIdx <= n;endIdx++){
            // l length broken
            String temp = s.substring(idx, endIdx);//break word
            if (st.contains(temp) && solve(endIdx, s))
                return dp[idx] = true;
        }
        return dp[idx] = false;
    }
    public boolean wordBreak(String s, List<String> wordDict) {
        // recursion + memoization
        n = s.length();
        st = new HashSet<>();
        dp = new Boolean[n];
        for(String word: wordDict) st.add(word);
        return solve(0, s);
    }
}
class Tabulatedsolution {
    int n;
    Set<String> st;
    Boolean[] dp;
    public boolean wordBreak(String s, List<String> wordDict) {
        // tabulation
        st = new HashSet<>();
        for(String word: wordDict) st.add(word);
        n = s.length();
        dp = new Boolean[n+1];
        Arrays.fill(dp, false);
        dp[0] = true;
        for(int len = 1;len <= n;len++){
            String temp = s.substring(0, len);
            if (st.contains(temp)) dp[len] = true;
            for(int j = 0;j <= len;j++){
                String str = s.substring(j, len);
                if (st.contains(str) && dp[j] == true){
                    dp[len] = true;
                    break;
                } 
            }
        }
        return dp[n];
    }
}
