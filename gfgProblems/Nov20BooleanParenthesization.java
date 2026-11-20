class MemoizedSolution {
	static int n;
	static Integer[][][] dp;
	static int solve(int i, int j, String s, int isTrue) {
		if (i > j)
			// base case
		return 0;
		
		if (i == j) {
			// only one element in range
			if (isTrue == 1) {
				return dp[i][j][isTrue] = s.charAt(i) == 'T' ? 1 : 0;
			} else {
				return dp[i][j][isTrue] = s.charAt(j) == 'F' ? 1 : 0;
			}
		}
		
		if (dp[i][j][isTrue] != null)
			return dp[i][j][isTrue];
		
		int ways = 0; // initial no ways to achieve true
		
		for (int ind = i + 1; ind < j; ind += 2) {
			int lT = solve(i, ind - 1, s, 1);
			int lF = solve(i, ind - 1, s, 0);
			int rT = solve(ind + 1, j, s, 1);
			int rF = solve(ind + 1, j, s, 0);
			
			if (s.charAt(ind) == '&') {
				
				if (isTrue == 1) {
					ways += lT * rT;
				} else {
					ways += lF * rF + lT * rF + lF * rT;
				}
			} else if (s.charAt(ind) == '|') {
				
				if (isTrue == 1) {
					ways += lT * rT + lT * rF + lF * rT;
				} else {
					ways += lF * rF;
				}
				
			} else {
				// s.charAt(ind) == '^'
				// T^T or F^F = F
				// T^F or F^T = T
				
				if (isTrue == 1) {
					ways += lT*rF + lF * rT;
				} else {
					ways += lT * rT + lF * rF;
				}
			}
		}
		
		return dp[i][j][isTrue] = ways;
		
	}
	static int countWays(String s) {
        // TC : O(n^3) (due to a for loop)
        // SC : O(n^2)
		n = s.length();
		dp = new Integer[n][n][2];
		return solve(0, n - 1, s, 1);
	}
}

class Solution {
	static int countWays(String s) {
	    // TC : O(n^3)
	    // SC : O(n^2)
		int n = s.length();
		int[][][] dp = new int[n][n][2];
		
		for (int i = n - 1; i >= 0; i--) {
			for (int j = i; j < n; j++) {
				
				if (i == j) {
					// only one element in range
					
					dp[i][j][1] = s.charAt(i) == 'T' ? 1 : 0;
					
					dp[i][j][0] = s.charAt(j) == 'F' ? 1 : 0;
					
				}
				else {
					
					int trueWays = 0; // initial no ways to achieve true
					int falseWays = 0; // initially no ways to achieve false
					
					for (int ind = i + 1; ind < j; ind += 2) {
						int lT = dp[i][ind - 1][1];
						int lF = dp[i][ind - 1][0];
						int rT = dp[ind + 1][j][1];
						int rF = dp[ind + 1][j][0];
						
						if (s.charAt(ind) == '&') {
							
							trueWays += lT * rT;
							
							falseWays += lF * rF + lT * rF + lF * rT;
							
						} else if (s.charAt(ind) == '|') {
							
							trueWays += lT * rT + lT * rF + lF * rT;
							
							falseWays += lF * rF;
							
						} else {
							// s.charAt(ind) == '^'
							// T^T or F^F = F
							// T^F or F^T = T
							
							trueWays += lT*rF + lF * rT;
							
							falseWays += lT * rT + lF * rF;
							
						}
					}
					
					dp[i][j][1] = trueWays;
					dp[i][j][0] = falseWays;
				}
			}
		}
		return dp[0][n - 1][1];
	}
}