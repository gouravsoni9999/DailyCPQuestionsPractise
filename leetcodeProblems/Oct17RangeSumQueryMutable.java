class NumArray {
    int[] segmentTree;
    int n;

    // TC : O(logn) -> for every function build(), update(), query()
    // SC : O(n + logn) -> again for every fn.

    public NumArray(int[] nums) {
        n = nums.length;
        // build segment tree here
        segmentTree = new int[4 * n];

        buildSegmentTree(nums, 0, 0, n - 1);
    }

    private void buildSegmentTree(int[] nums, int i, int l, int r) {
        if (l == r) {
            // base case: leaf node
            segmentTree[i] = nums[l];
            return;
        }

        int mid = l + (r - l) / 2;
        buildSegmentTree(nums, 2 * i + 1, l, mid);//build left subtree
        buildSegmentTree(nums, 2 * i + 2, mid + 1, r);//build right subtree

        segmentTree[i] = segmentTree[2 * i + 1] + segmentTree[2 * i + 2]; // add both left node and right node values
    }

    public void update(int index, int val) {
        updateSegmentTree(0, val, index, 0, n - 1);
    }

    private void updateSegmentTree(int i, int val, int idx, int l, int r) {
        if (l == r) {
            // base case: update the leaf node
            segmentTree[i] = val;
            return;
        }

        int mid = l + (r - l) / 2;
        if (idx <= mid) // left child
            updateSegmentTree(2 * i + 1, val, idx, l, mid);//update left subtree
        else
            updateSegmentTree(2 * i + 2, val, idx, mid + 1, r);// update right subtree

        segmentTree[i] = segmentTree[2 * i + 1] + segmentTree[2 * i + 2];
    }

    public int sumRange(int left, int right) {
        return querySegmentTree(left, right, 0, 0, n - 1);
    }

    private int querySegmentTree(int start, int end, int i, int l, int r) {
        if (l > end || r < start) {
            // range out-of-bounds
            return 0;
        }

        if (l >= start && r <= end) {
            // within range
            return segmentTree[i];
        }

        else {
            // overlapping case
            int mid = l + (r - l) / 2;
            int left = querySegmentTree(start, end, 2 * i + 1, l, mid); // contribution from left subtree
            int right = querySegmentTree(start, end, 2 * i + 2, mid + 1, r); // contribution form right subtree

            return left + right;
        }
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(index,val);
 * int param_2 = obj.sumRange(left,right);
 */