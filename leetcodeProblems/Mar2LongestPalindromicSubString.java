class Solution {
    private boolean isPalindrome(String s, int i, int j) {
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    public String longestPalindrome(String s) {
        // first approach: generating all substrings
        // using loops, generate all substrings
        // check if that substring is a palindrome
        // if it is a palindrome and if it is greater than maxPalindromeLen, I would
        // update maxPalindromeLen and also the starting ptr
        // TC : O(n^3)
        // SC : O(1)
        int n = s.length();
        int maxLen = 0;
        int start = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                // substring : i to j
                if (isPalindrome(s, i, j) && j - i + 1 > maxLen) {
                    // if the substring is palindrome && the length of substring is greater than the
                    // existing maxLen found
                    maxLen = j - i + 1;
                    start = i;
                }
            }
        }
        return s.substring(start, start + maxLen);// giving both start and end iterators
    }
}

class Solution {
    Boolean[][] dp;

    private Boolean isPalindrome(String s, int i, int j) {
        if (i >= j)
            return true;
        if (dp[i][j] != null) {
            return dp[i][j];
        }
        if (s.charAt(i) == s.charAt(j)) {
            return dp[i][j] = isPalindrome(s, i + 1, j - 1);
        }

        return dp[i][j] = false; // becoz both the characters in s[i] and s[j] are not same
    }

    public String longestPalindrome(String s) {
        // first approach: generating all substrings
        // using loops, generate all substrings
        // check if that substring is a palindrome
        // if it is a palindrome and if it is greater than maxPalindromeLen, I would
        // update maxPalindromeLen and also the starting ptr
        // TC : O(n^2) --> since each state is only computed once, so the complexity boils down to n^2 instead of n^3
        // SC : O(n^2)
        // using recursion + memoization
        int n = s.length();
        int maxLen = 0;
        int start = 0;
        dp = new Boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                // substring : i to j
                if (isPalindrome(s, i, j) && (j - i + 1) > maxLen) {
                    // if the substring is palindrome && the length of substring is greater than the
                    // existing maxLen found
                    maxLen = j - i + 1;
                    start = i;
                }
            }
        }
        return s.substring(start, start + maxLen);// giving both start and end iterators
    }
}

class Solution {
    public String longestPalindrome(String s) {
        // using tabulation approach
        // TC : O(n.n)
        // SC : O(n.n)
        int n = s.length();
        Boolean[][] dp = new Boolean[n][n];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                boolean ans = false;
                if (i >= j)
                    // both pointers are at same pos or have passed each other after checking: always true
                    ans = true;
                else if (s.charAt(i) == s.charAt(j))
                    // if already not precomputed, then the ans is always true
                    ans = (dp[i + 1][j - 1] == null) ? true : dp[i + 1][j - 1];
                else
                    ans = false;

                dp[i][j] = ans;
            }
        }

        int maxLen = 0; // max Palindromic len
        int start = -1; // starting index

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (dp[i][j] && (j - i + 1) > maxLen) {
                    maxLen = j - i + 1;
                    start = i;
                }
            }
        }

        return s.substring(start, start + maxLen);
    }
}

class Solution {
    public String longestPalindrome(String s) {// O(n^2)
        // using tabulation
        int n = s.length();
        Boolean[][] dp = new Boolean[n][n];
        int maxLen = 1;
        int sp = -1;
        // for length = 1; always true and palindrome
        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
            sp = i;
        }
        // for lengths >= 2;
        for (int L = 2; L <= n; L++) {
            for (int i = 0; i + L - 1 < n; i++) {
                int j = i + L - 1;// therefore in for loop condition j<n
                // if length == 2
                if (i + 1 == j && s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = true;
                    maxLen = 2;
                    sp = i;
                }
                // if length > 2
                else if (s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1]) {
                    dp[i][j] = true;
                    if (j - i + 1 > maxLen) {
                        maxLen = j - i + 1;
                        sp = i;
                    }
                } else {
                    // not a palindrome
                    dp[i][j] = false;
                }
            }
        }
        return s.substring(sp, sp + maxLen);
    }
}

class Solution {
    int n;
    int maxLen; // max length of palindromic substring
    int start;  // start idx of palindromic substring
    private void expand_around_center(String s, int left,int right){
        while(left >= 0 && right < n && s.charAt(left) == s.charAt(right)){
            // expand until we get our palindrome
            left--;right++;
        }

        if(maxLen < right-left-1){
            maxLen = right - left - 1;
            start = left+1;
        }
    }
    public String longestPalindrome(String s) {
        // using expand around center concept
        // TC : O(n^2)
        // SC : O(1)
        n = s.length();
        if(n <= 1)
            return s; // length is 0 or 1, already longest palindrome
        
        // initially assume 
        start = 0;
        maxLen = 1;

        for(int i = 0;i < n;i++){
            // expand from center
            // odd length substr
            expand_around_center(s,i,i);
            // even length subsstr
            expand_around_center(s, i, i+1);
        }

        return s.substring(start, start + maxLen);
    }
}

