class Solution {
    public int knapSack(int val[], int wt[], int capacity) {
		/*
    		tabulation + space-optimization (1D - Array)
    		TC : O(n * capacity)
    		SC : O(capacity)
		*/
		
		int n = val.length;
		
		int[] next = new int[capacity + 1];
		
		for (int i = n - 1; i >= 0; i--) {
			for (int remWt = 0; remWt <= capacity; remWt++) {
				if (remWt == 0 || i == n) {
					next[remWt] = 0;
				} else {
					int take = (remWt - wt[i] < 0)? Integer.MIN_VALUE : val[i] + next[remWt - wt[i]];
					int notTake = 0 + next[remWt];
					next[remWt] = Math.max(take, notTake);
				}
			}
		}
		
		return next[capacity];
		
	}
    public int cutRod(int[] price) {
        int n = price.length;
        int[] wt = new int[n];
        for(int i = 0;i < n;i++){
            wt[i] = i+1;
        }
        return knapSack(price, wt, n);
    }
}

class Solution {
    int N;
    private int solve(int i,int remRodLen,int[] price){
        if(remRodLen < 0){
            return Integer.MIN_VALUE;
        }
        if(i == N || remRodLen == 0){
            return 0;// don't go further, end
        }
        int rodLenToCut = i+1;
        // if we cut, we can again cut in further call
        int cutProfit = price[i] + solve(i, remRodLen - rodLenToCut, price);
        // if we not cut, we will move to next idx
        int notCutProfit =  0 + solve(i+1, remRodLen, price);
        return Math.max(cutProfit, notCutProfit);
    }
    public int cutRod(int[] price) {
        /*
            using recursion
            TC : O(2 ^ n)
            SC : O(n)
            where n is the length of rod
        */
        int n = price.length; // total rod length
        N = price.length;
        return solve(0, n, price);
    }
}

class Solution {
    int N;
    int[][] dp;
    private int solve(int i,int remRodLen,int[] price){
        if(remRodLen < 0){
            return Integer.MIN_VALUE;
        }
        if(i == N || remRodLen == 0){
            return 0;// don't go further, end
        }
        if(dp[i][remRodLen] != -1)
            return dp[i][remRodLen]; 
        
        int rodLenToCut = i+1;
        // if we cut, we can again cut in further call
        int cutProfit = price[i] + solve(i, remRodLen - rodLenToCut, price);
        // if we not cut, we will move to next idx
        int notCutProfit =  0 + solve(i+1, remRodLen, price);
        return dp[i][remRodLen] = Math.max(cutProfit, notCutProfit);
    }
    public int cutRod(int[] price) {
        /*
            using recursion + memoization
            TC : O(n*n)
            SC : O(n*n) + O(n) stack space
            where n is the length of rod
        */
        int n = price.length; // total rod length
        N = price.length;
        dp = new int[n+1][n+1];
        for(int[] arr: dp){
            Arrays.fill(arr, -1);
        }
        return solve(0, n, price);
    }
}

class Solution {
    public int cutRod(int[] price) {
        /*
            using tabulation
            TC : O(n * n)
            SC : O(n * n)
        */
        int N = price.length;
        
        int[][] dp = new int[N+1][N+1];
        
        for(int i = N;i >= 0;i--){
            for(int remRodLen = 0;remRodLen <= N;remRodLen++){
                if(i == N || remRodLen == 0){
                    dp[i][remRodLen] = 0;
                }else{
                    int rodLenToCut = i+1;
                    int cutProfit = (remRodLen-rodLenToCut < 0) ? Integer.MIN_VALUE : price[i] + dp[i][remRodLen-rodLenToCut];
                    int notCutProfit = 0 + dp[i+1][remRodLen];
                    dp[i][remRodLen] = Math.max(cutProfit, notCutProfit);
                }
            }
        }
        
        return dp[0][N];
    }
}

class Solution {
    public int cutRod(int[] price) {
        /*
            using tabulation + space-optimization
            TC : O(n * n)
            SC : O(n)
        */
        int N = price.length;
        
        int[] next = new int[N+1];
        
        for(int i = N;i >= 0;i--){
            int[] curr = new int[N+1];
            for(int remRodLen = 0;remRodLen <= N;remRodLen++){
                if(i == N || remRodLen == 0){
                    curr[remRodLen] = 0;
                }else{
                    int rodLenToCut = i+1;
                    int cutProfit = (remRodLen-rodLenToCut < 0) ? Integer.MIN_VALUE : price[i] + curr[remRodLen-rodLenToCut];
                    int notCutProfit = 0 + next[remRodLen];
                    curr[remRodLen] = Math.max(cutProfit, notCutProfit);
                }
            }
            next = curr;
        }
        
        return next[N];
    }
}
