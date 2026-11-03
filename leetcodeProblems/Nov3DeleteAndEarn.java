class Solution {
    int n;

    public int deleteAndEarn(int[] nums) {
        // TC : O(n) + O(maxi)
        // SC : O(maxi)
        n = nums.length;
        int maxi = nums[0];
        // find max. element in array
        for (int i = 1; i < n; i++) {
            maxi = Math.max(maxi, nums[i]);
        }
        // make a freq array, containing freq of every element
        int[] cnt = new int[maxi + 1];
        for (int i = 0; i < n; i++) {
            cnt[nums[i]]++;
        }

        int first = 0;
        int second = 0;
        // iterate in freq array
        for (int i = 0; i <= maxi; i++) {
            // if excluded, I can take previous value's exclude or include max.
            int exclude = second;
            int include = first + (i * cnt[i]);

            int maxProfit = Math.max(include, exclude);
            first = second;
            second = maxProfit;
        }
        return second;
    }
}