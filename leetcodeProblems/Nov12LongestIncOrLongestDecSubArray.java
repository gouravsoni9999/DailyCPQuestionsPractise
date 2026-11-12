class Solution {
    public int longestMonotonicSubarray(int[] arr) {
        // TC : O(n^2)
        // SC : O(1)
        int maxCnt = 1;
        int n = arr.length;

        // generate all subarrays and find increasing and decreasing subarrays
        for (int i = 0; i < n; i++) {
            int cnt = 1;
            for (int j = i + 1; j < n; j++) {
                if (arr[j - 1] > arr[j]) {
                    cnt++; // descending order
                } else {
                    break;
                }
            }
            maxCnt = Math.max(cnt, maxCnt);

            cnt = 1;
            for (int j = i + 1; j < n; j++) {
                if (arr[j - 1] < arr[j]) {
                    cnt++; // ascending order
                } else {
                    break;
                }
            }
            maxCnt = Math.max(cnt, maxCnt);
        }

        return maxCnt;
    }
}

class OptimizedSolution {
    public int longestMonotonicSubarray(int[] nums) {
        // TC : O(n)
        // SC : O(1)
        int n = nums.length;
        int incLength = 1;
        int decLength = 1;
        int maxLength = 1;
        for (int i = 1; i < n; i++) {
            if (nums[i - 1] > nums[i]) {
                // decreasing
                decLength++;
                incLength = 1; // reset
                maxLength = Math.max(decLength, maxLength);
            } else if (nums[i - 1] < nums[i]) {
                // increasing
                incLength++;
                decLength = 1; // reset
                maxLength = Math.max(incLength, maxLength);
            } else {
                // both are same
                incLength = 1; // reset
                decLength = 1; // reset
            }
        }
        return maxLength;
    }
}