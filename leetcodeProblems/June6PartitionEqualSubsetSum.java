class Solution {
    boolean isSubsetSum(int arr[], int sum) {
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

    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 == 1) {
            // if sum of entire array is odd, 
            // nobody can't find 2 partitions which give 
            // equal sum
            return false;
        }
        // else if entire array's sum is even, we can find 2 subsets
        int target = sum / 2;
        return isSubsetSum(nums, target); // if one subset has sum as n/2, then definitly other subset would also have same sum
    }
}
