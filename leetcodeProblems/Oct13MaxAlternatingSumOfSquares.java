class Solution {
    public long maxAlternatingSum(int[] nums) {
        long ans = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (nums[i] < 0) {
                nums[i] = -nums[i];
            }
        }

        Arrays.sort(nums); // sort in ascending order

        int positiveSums = (int) Math.ceil(n / 2.0);

        int negativeSums = n - positiveSums;

        for (int i = 0; i < negativeSums; i++) {
            ans -= nums[i] * nums[i];
        }

        for (int i = negativeSums; i < n; i++) {
            ans += nums[i] * nums[i];
        }

        return ans;
    }
}