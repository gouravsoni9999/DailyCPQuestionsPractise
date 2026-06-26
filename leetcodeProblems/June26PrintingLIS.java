import java.util.*;
class Solution {
	public ArrayList<Integer> getLIS(int arr[]) {
		/*
    		LIS code
    		using tabulation
    		TC : O(n^2)
    		SC : O(n)
		*/
		
		int n = arr.length;
		int[] dp = new int[n];
		int[] prevIdxOfSubSeq = new int[n];
		int lastIdx = 0;
		int lis = 0;
		
		for (int i = 0; i < n; i++) {
			prevIdxOfSubSeq[i] = i;
		}
		ArrayList<Integer> res = new ArrayList<>();
		
		for (int i = 0; i < n; i++) {
			for (int prevIdx = i - 1; prevIdx >= 0; prevIdx--) {
				if (arr[prevIdx] < arr[i] && 1 + dp[prevIdx] >= dp[i]) {
					// update prevIdxOfSubSeq
					prevIdxOfSubSeq[i] = prevIdx;
					dp[i] = 1 + dp[prevIdx];
				}
			}
			
			if (lis < dp[i]) {
				lis = dp[i];
				lastIdx = i;
			}
		}
		
		while (lastIdx != prevIdxOfSubSeq[lastIdx]) {
			res.add(arr[lastIdx]);
			lastIdx = prevIdxOfSubSeq[lastIdx];
		}
		res.add(arr[lastIdx]);
		
		Collections.reverse(res);
		
		return res;
	}
}
