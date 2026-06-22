class Solution {
    int m;
    int n;
    private boolean solve(int i,int j,String s,String p){
        // base cases
        if(i == m && j == n){
            // both get exhausted, strings matched!
            return true;
        }
        if(i == m && j < n){
            // s has exhausted but p has not 
            for(;j < n;j++){
                // find remaining characters should only be '*'
                char ch = p.charAt(j);
                if(ch != '*'){
                    return false;
                }
            }
            return true; // only '****...', therefore matches
        }
        if(i < m && j == n){
            // s has not exhausted but p has
            return false;
        }

        if(s.charAt(i) == p.charAt(j) || p.charAt(j) == '?'){
            // matches
            return solve(i+1, j+1, s, p);
        }

        if(p.charAt(j) == '*'){
            return (solve(i+1, j, s, p) | solve(i, j+1, s, p));
        }
        return false; // both characters are not matching nor a * character present 
    }
    public boolean isMatch(String s, String p) {
        /*
            using recursion
            TC : exponential
            SC : O(m+n) auxilary stack space
        */
        m = s.length();
        n = p.length();
        return solve(0, 0, s, p);
    }
}

class Solution {
    int m;
    int n;
    Boolean[][] dp;
    private boolean solve(int i,int j,String s,String p){
        // base cases
        if(i == m && j == n){
            // both get exhausted, strings matched!
            return true;
        }
        if(i == m && j < n){
            // s has exhausted but p has not 
            for(;j < n;j++){
                // find remaining characters should only be '*'
                char ch = p.charAt(j);
                if(ch != '*'){
                    return false;
                }
            }
            return true; // only '****...', therefore matches
        }
        if(i < m && j == n){
            // s has not exhausted but p has
            return false;
        }

        if(dp[i][j] != null){
            return dp[i][j];
        }

        if(s.charAt(i) == p.charAt(j) || p.charAt(j) == '?'){
            // matches
            return dp[i][j] = solve(i+1, j+1, s, p);
        }

        if(p.charAt(j) == '*'){
            return dp[i][j] = (solve(i+1, j, s, p) | solve(i, j+1, s, p));
        }
        return dp[i][j] = false; // both characters are not matching nor a * character present 
    }
    public boolean isMatch(String s, String p) {
        /*
            using recursion + memoization
            TC : O(m*n)
            SC : O(m*n) + O(m+n) auxilary stack space
        */
        m = s.length();
        n = p.length();
        dp = new Boolean[m][n];
        return solve(0, 0, s, p);
    }
}

class Solution {
    public boolean isMatch(String s, String p) {
        /*
            using tabulation
            TC : O(m*n)
            SC : O(m*n) 
        */
        int m = s.length();
        int n = p.length();

        Boolean[][] dp = new Boolean[m + 1][n + 1];

        for (int i = m; i >= 0; i--) {
            for (int j = n; j >= 0; j--) {
                if (i == m && j == n) {
                    dp[i][j] = true;
                } else if (i == m && j < n) {

                    for (int k = j; k < n; k++) {
                        char ch = p.charAt(k);
                        if (ch != '*') {
                            dp[i][j] = false;
                            break;
                        }
                    }

                    if (dp[i][j] == null) {
                        dp[i][j] = true;
                    }
                } else if (i < m && j == n) {
                    dp[i][j] = false;
                } else if (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?') {
                    dp[i][j] = dp[i + 1][j + 1];
                } else if (p.charAt(j) == '*') {
                    dp[i][j] = (dp[i + 1][j] | dp[i][j + 1]);
                } else {
                    dp[i][j] = false;
                }
            }
        }

        return dp[0][0];
    }
}

class Solution {
    public boolean isMatch(String s, String p) {
        /*
            using tabulation + space-optimization
            TC : O(m*n)
            SC : O(n) 
        */
        int m = s.length();
        int n = p.length();

        Boolean[] next = new Boolean[n + 1];

        for (int i = m; i >= 0; i--) {
            Boolean[] curr = new Boolean[n+1];
            for (int j = n; j >= 0; j--) {
                if (i == m && j == n) {
                    curr[j] = true;
                } else if (i == m && j < n) {

                    for (int k = j; k < n; k++) {
                        char ch = p.charAt(k);
                        if (ch != '*') {
                            curr[j] = false;
                            break;
                        }
                    }

                    if (curr[j] == null) {
                        curr[j] = true;
                    }
                } else if (i < m && j == n) {
                    curr[j] = false;
                } else if (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?') {
                    curr[j] = next[j + 1];
                } else if (p.charAt(j) == '*') {
                    curr[j] = (next[j] | curr[j + 1]);
                } else {
                    curr[j] = false;
                }
            }
            next = curr;
        }

        return next[0];
    }
}

