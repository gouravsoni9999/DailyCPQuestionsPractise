class MemoizedSolution {
    int n;
    Integer[] dp;

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

    private int solve(int i, String s) {
        if (i == n) {
            return 0;
        }
        if (dp[i] != null)
            return dp[i];

        int minCost = Integer.MAX_VALUE;
        for (int j = i; j < n; j++) {
            if (isPalindrome(s, i, j)) {
                // 1 valid partition found
                int cost = 1 + solve(j + 1, s); // string [i..j] is already a palindrome, and then add next partitions in string
                minCost = Math.min(minCost, cost);
            }
        }

        return dp[i] = minCost;
    }

    public int minCut(String s) {
        // TC: O(n^2)
        // SC : O(n)
        n = s.length();
        dp = new Integer[n];
        return solve(0, s) - 1; // as partition is also done at end(which should not be as per the question)
    }
}

class TabulatedSolution {
    int n;
    Integer[] dp;

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

    public int minCut(String s) {
        // TC : O(n^2)
        // SC : O(n)
        int n = s.length();
        int[] dp = new int[n + 1];

        for (int i = n; i >= 0; i--) {
            int val = 0;
            if (i == n) {
                val = 0;
            } else {
                int minCost = Integer.MAX_VALUE;
                for (int j = i; j < n; j++) {
                    if (isPalindrome(s, i, j)) {
                        // 1 valid partition found
                        int cost = 1 + dp[j + 1]; // string [i..j] is already a palindrome, and then add next partitions in string
                        minCost = Math.min(minCost, cost);
                    }
                }
                val = minCost;
            }
            dp[i] = val;
        }

        return dp[0] - 1; // as partition is also done at end(which should not be as per the question)
    }
}