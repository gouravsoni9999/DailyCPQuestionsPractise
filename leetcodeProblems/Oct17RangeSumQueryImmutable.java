class NumArray {
    int[] prefixSum;
    int n;

    public NumArray(int[] nums) {
        // TC : O(n)
        // SC : O(n)
        this.n = nums.length;
        prefixSum = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefixSum[i + 1] = prefixSum[i] + nums[i];
        }
    }

    public int sumRange(int left, int right) {
        // TC : O(1)
        // SC : O(1)
        return prefixSum[right + 1] - prefixSum[left];
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * int param_1 = obj.sumRange(left,right);
 */