class Solution {
    static int n;
    static Boolean solve(int[] arr,int target,int i,int currSum){
        if(currSum > target){
            return false;
        }
        if(i >= n){
            if(currSum == target){
                return true;
            }else{
                return false;
            }
        }
        if(currSum == target){
            return true;
        }
        boolean take = solve(arr, target, i+1, currSum+arr[i]);
        if(take == true)
            return true;
        boolean not_take = solve(arr, target, i+1, currSum);
        if(not_take == true) 
            return true;
        return false;
    }
    static Boolean isSubsetSum(int arr[], int sum) {
        n = arr.length;
        /*
            using simple recursion
            TC : O(2^n)
            SC : O(n)
        */
        return solve(arr, sum, 0, 0);
    }
}

class Solution {
	static int n;
	static Boolean[][] dp;
	static Boolean solve(int[] arr, int i, int target) {
		if (target == 0) {
			return true;
		}
		if(i == n-1){
		    return (arr[i] == target) ? true : false;
		}
		if(dp[i][target] != null){
		    return dp[i][target];
		}
		boolean take = (target-arr[i] >= 0)?solve(arr, i + 1, target - arr[i]) : false;
		boolean not_take = solve(arr, i + 1, target);
		// if any of them is true, return true
		return dp[i][target] = take || not_take;
	}
	static Boolean isSubsetSum(int arr[], int sum) {
		n = arr.length;
		/*
		using recursion + memoization
		TC : O(n*sum)
		SC : O(n*sum) + O(n)
		*/
		dp = new Boolean[n][sum+1];
		return solve(arr,0,sum);
	}
}


class Solution {
    
        static Boolean isSubsetSum(int arr[], int sum) {
            /*
                using tabulation
                TC : O(n*sum)
                SC : O(n*sum)
            */
            int n = arr.length;
            boolean[][] dp = new boolean[n][sum+1];
            for(int i = n-1;i >= 0;i--){
                for(int target = sum;target >= 0;target--){
                    if(target == 0){
                        dp[i][target] = true;
                        continue;
                    }
                    if(i == n-1){
                        if(arr[i] == target){
                            dp[i][target] = true;
                        }else{
                            dp[i][target] = false;
                        }
                        continue;
                    }
                    boolean take = false;
                    if(target - arr[i] >= 0){
                        take = dp[i+1][target-arr[i]];
                    }
                    boolean not_take = dp[i+1][target];
                    dp[i][target] = (take || not_take);
                }
            }
            return dp[0][sum];
        }
}

class Solution {
	
	static Boolean isSubsetSum(int arr[], int sum) {
		/*
		using tabulation + space - optimization
		TC : O(n*sum)
		SC : O(sum)
		*/
		int n = arr.length;
		boolean[] next = new boolean[sum + 1];
		for (int i = n - 1; i >= 0; i--) {
			boolean[] curr = new boolean[sum + 1];
			for (int target = sum; target >= 0; target--) {
				if (target == 0) {
					curr[target] = true;
					continue;
				}
				if (i == n - 1) {
					curr[target] = (arr[i] == target);
					continue;
				}
				boolean take = false;
				if (target - arr[i] >= 0) {
					take = next[target - arr[i]];
				}
				boolean not_take = next[target];
				curr[target] = (take | not_take);
			}
			next = curr;
		}
		return next[sum];
	}
}

