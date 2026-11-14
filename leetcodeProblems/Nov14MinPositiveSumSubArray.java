import java.util.*;

class Solution {
    public int minimumSumSubarray(List<Integer> nums, int l, int r) {
        // TC : O(n^3)
        // SC : O(1)
        int n = nums.size();
        int minSum = Integer.MAX_VALUE;
        for (int start = 0; start < n; start++) {
            for (int size = l; size <= r; size++) {
                int end = start + size - 1;
                if (end >= n) {
                    continue;
                }
                int sum = 0;
                for (int i = start; i <= end; i++) {
                    sum += nums.get(i);
                }
                if (sum <= 0) {
                    // only +ve sums allowed
                    continue;
                }
                minSum = Math.min(minSum, sum);
            }
        }

        return (minSum == Integer.MAX_VALUE) ? -1 : minSum;

    }
}

class OptimizedSolution {
    public int minimumSumSubarray(List<Integer> nums, int l, int r) {
        // TC : O(n.n)
        // SC : O(1)
        int n = nums.size();
        int minSum = Integer.MAX_VALUE;
        // try every starting index
        for (int start = 0; start < n; start++) {
            int sum = 0;
            // extend the subarray
            for (int end = start; end < n; end++) {
                sum += nums.get(end);
                int length = end - start + 1;
                // check valid subarray length && positive sums
                if (l <= length && length <= r && sum > 0) {
                    minSum = Math.min(minSum, sum);
                }

                if (length >= r) {
                    break; // no need to consider length > r
                }
            }
        }

        return (minSum == Integer.MAX_VALUE) ? -1 : minSum;
    }
}