class Solution {
    int n;

    private int solve(int i, int currSum, int[] nums, int target) {
        if (i == n) {
            return (currSum == target) ? 1 : 0;
        }
        int positiveWays = solve(i + 1, currSum + nums[i], nums, target);
        int negativeWays = solve(i + 1, currSum - nums[i], nums, target);

        return positiveWays + negativeWays;

    }

    public int findTargetSumWays(int[] nums, int target) {
        /*
            using recursion
            TC : O(2^n)
            SC : O(n)
        */
        n = nums.length;
        return solve(0, 0, nums, target);
    }
}

class Solution {
    
    // Function to calculate the number of subsets with a given sum
	public int perfectSum(int[] nums, int target) {
		// code here
		int n = nums.length;
		int[] next = new int[target + 1];
		// using tabulation + space-optimization
		// TC : O(n*target)
		// SC : O(n*target)
		for (int i = n; i >= 0; i--) {
			int[] curr = new int[target + 1];
			for (int k = target; k >= 0; k--) {
				if (k < 0) {
					// base case where target < 0
					curr[k] = 0;
				} else if (i == n) {
					// base case where i is out-of-bounds
					curr[0] = 1;
					// rest all is 0, which is handled automatically
				} else {
					int take = 0;
					if (k - nums[i] >= 0) {
						take = next[k - nums[i]];
					}
					int not_take = next[k];
					curr[k] = take + not_take;
				}
			}
			next = curr;
		}
		return next[target];
	}
	
	public int countPartitions(int[] arr, int diff) {
		/*
		    S1 - S2 = diff -- 1
		    S1 + S2 = totalSum => S1 = totalSum - S2 -- 2
		    substitue 2 in 1
		    => totalSum-S2-S2 = diff
		    => totalSum - 2*S2 = diff
		    => totalSum - diff = 2 * S2
		    => S2 = (totalSum - diff) / 2
		    => if totalSum - diff is negative, that means 2 * S2 is -ve which can't happen due to arr[] containing only +ve ints
		    and also totalSum - diff is if odd, that means totalSum - diff / 2 is a decimal number, which again can't happen becoz S2 has all ints 
		*/
		// TC : O(n) + perfectSum's TC
		// SC : O(1) + perfectSum's SC 
		int n = arr.length;
		int totalSum = 0;
		for(int num: arr){
		    totalSum += num;
		}
		if(((totalSum - diff) < 0) || (((totalSum - diff) % 2) == 1)){
		    // if totalSum - diff is negative or odd, no partitions can be formed
		    return 0;
		}
		// find number of subsets who can give me sum as (totalSum-diff)/2
		return perfectSum(arr, (totalSum-diff)/2);
	}

    public int findTargetSumWays(int[] nums, int target) {
        // we need to find out which nums[i] should be given +ve sign and which ones -ve to get target (and find that no. of ways)
        // the same question as countPartitions
        return countPartitions(nums, target);
    }
}
