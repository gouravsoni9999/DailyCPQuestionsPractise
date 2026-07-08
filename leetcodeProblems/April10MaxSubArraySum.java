class Solution {
    public int maxSubArray(int[] nums) {
        // TC : O(n), SC : O(1)
        int n = nums.length;
        int maxSum = nums[0];
        int sum = 0;
        for(int i = 0;i < n;i++){
            sum += nums[i];
            maxSum = Math.max(sum, maxSum);
            if(sum < 0){
                sum = 0;// reset, faaltu hai -ve value lena
            }
        }
        return maxSum;
    }
}

class Solution {
    public int maxSubArray(int[] nums) {
        // using kadane's Algo
        // TC : O(n)
        // SC : O(1)
        int maxSum = nums[0];
        int currSum = nums[0];
        int n = nums.length;

        for(int i = 1;i < n;i++){
            int num = nums[i];
            currSum += num;
             
            currSum = Math.max(currSum, num); // start from this, or continue with existing subarray

            maxSum = Math.max(currSum, maxSum);
        }

        return maxSum;
    }
}


class Solution {
    int n;
    private int solve(int[] nums,int l,int r){
        if(l > r){
            return -1;
        }
        if(l == r){
            return nums[l];
        }
        int mid = l + (r-l)/2;
        int lss = solve(nums, l, mid);
        int rss = solve(nums, mid+1, r);
        int leftMax = Integer.MIN_VALUE;
        int rightMax = Integer.MIN_VALUE;
        int left = 0;
        int right = 0;
        for(int i = mid;i >= l;i--){
            left += nums[i];
            leftMax = Math.max(left, leftMax);
        }
        for(int j = mid+1;j <= r;j++){
            right += nums[j];
            rightMax = Math.max(right, rightMax);
        }
        int css = leftMax + rightMax;
        return Math.max(lss, Math.max(rss, css));
    }
    public int maxSubArray(int[] nums) {
        // TC : O(nlogn) && SC : stack space : O(log n)
        n = nums.length;
        // using D&C 
        return solve(nums, 0, n-1);
    }
}
