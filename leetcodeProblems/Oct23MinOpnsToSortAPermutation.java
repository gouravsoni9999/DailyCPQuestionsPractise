class Solution {
    public int minOperations(int[] nums) {
        // TC : O(n)
        // SC : O(1)
        int n = nums.length;

        int asc_count = 0;
        int dsc_count = 0;

        for (int i = 1; i < n; i++) {
            if (nums[i] == (nums[i - 1] + 1) % n) {
                asc_count++;
            }
            if (nums[i - 1] == (nums[i] + 1) % n) {
                dsc_count++;
            }
        }

        if (asc_count == n - 1) {
            // increasing
            if (nums[0] == 0) {
                // already sorted
                return 0;
            }
            int leftRot = n - nums[0];
            int rightRot = (1 + (nums[n - 1] + 1) + 1); // 1 for reversing, then for left rot., then 1 for again reversing
            return Math.min(leftRot, rightRot);
        }

        if (dsc_count == n - 1) {
            // decreasing
            int leftRot = n - nums[n - 1];
            int rightRot = (nums[0] + 1) % n;
            // last rot. for reversing the whole array(to make desc to asc)
            return 1 + Math.min(leftRot, rightRot);
        }

        return -1; // neither inc. nor dec.
    }
}