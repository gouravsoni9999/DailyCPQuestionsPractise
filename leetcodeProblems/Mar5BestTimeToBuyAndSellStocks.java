class Solution {
    public int maxProfit(int[] prices) {
        // TC : O(n)
        // SC : O(1)
        int n = prices.length;
        int currMinBuyingPrice = prices[0];
        int maxiProfit = 0;
        for(int i = 1;i < n;i++){
            int profit = prices[i] - currMinBuyingPrice;//selling-buying
            maxiProfit = Math.max(maxiProfit, profit);
            // there is a possiblity that this ith day has min buying price
            currMinBuyingPrice = Math.min(currMinBuyingPrice, prices[i]);
        }
        return maxiProfit;
    }
}

class Solution {
    public int maxProfit(int[] prices) {
        // TC : O(n^2)
        // SC : O(1)
        int n = prices.length;
        int profit = Integer.MIN_VALUE;
        for(int i = 0;i < n;i++){
            int currProfit = -prices[i];//buyed that stock on ith day
            for(int j = i+1;j < n;j++){
                // sold stock on jth day
                profit = Math.max(profit, currProfit+prices[j]);
            }
        }
        return (profit < 0) ? 0 : profit;
    }
}
