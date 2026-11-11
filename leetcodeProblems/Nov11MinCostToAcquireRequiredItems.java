class Solution {
    public long minimumCost(int cost1, int cost2, int costBoth, int need1, int need2) {
        long ans = 0;
        int commonItems = Math.min(need1, need2);

        need1 -= commonItems;
        need2 -= commonItems;

        ans += 1L * Math.min(cost1 + cost2, costBoth) * commonItems; // min. cost of buying commonItems
        if (need1 > 0) {
            ans += 1L * Math.min(costBoth, cost1) * need1; // add remaining need through min. of cost1 & costBoth
        } else {
            // if need2 > 0
            ans += 1L * Math.min(costBoth, cost2) * need2; // add remaining need through min. of cost2 & costBoth
        }

        return ans;
    }
}