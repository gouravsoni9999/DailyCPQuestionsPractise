/*You are required to complete below method */
// TC : O(n.n)
// SC : O(n.n)

class MemoizedSolution {
	Integer[][] dp;
	int solve(int i, int j, String s) {
		if (i > j) {
			return 0; // invalid subsequence
		}
		
		if (i == j) {
			return 1; // one char. subsequence: always palindrome
		}
		
		if (dp[i][j] != null)
			return dp[i][j];
		
		if (s.charAt(i) == s.charAt(j)) {
			// same character x ... x
			return dp[i][j] = 1 + solve(i + 1, j, s) + solve(i, j - 1, s);
		}
		
		else {
			// not same character x ... y
			return dp[i][j] = solve(i + 1, j, s) + solve(i, j - 1, s) - solve(i + 1, j - 1, s); // subtract overlapping count
		}
	}
	int countPS(String s) {
		int n = s.length();
		dp = new Integer[n][n];
		return solve(0, n - 1, s);
	}
}

/*You are required to complete below method */

class TabulatedSolution {
	int countPS(String s) {
		int n = s.length();
		int[][] dp = new int[n][n];
		for (int i = n - 1; i >= 0; i--) {
			for (int j = 0; j < n; j++) {
				int val = 0;
				if (i > j)
					val = 0;
				else if (i == j)
					val = 1;
				else if (s.charAt(i) == s.charAt(j))
					val = 1 + dp[i + 1][j] + dp[i][j - 1];
				else
					val = dp[i + 1][j] + dp[i][j - 1] - dp[i + 1][j - 1];
				
				dp[i][j] = val;
			}
		}
		return dp[0][n - 1];
	}
}

/*You are required to complete below method */

class TabulatedAndSpaceOptimizedSolution {
	int countPS(String s) {
        // TC : O(n.n)
        // SC : O(n)
		int n = s.length();
		int[] next = new int[n];
		for (int i = n - 1; i >= 0; i--) {
		    int[] curr = new int[n];
			for (int j = 0; j < n; j++) {
				int val = 0;
				if (i > j)
					val = 0;
				else if (i == j)
					val = 1;
				else if (s.charAt(i) == s.charAt(j))
					val = 1 + next[j] + curr[j - 1];
				else
					val = next[j] + curr[j - 1] - next[j - 1];
				
				curr[j] = val;
			}
			next = curr;
		}
		return next[n - 1];
	}
}