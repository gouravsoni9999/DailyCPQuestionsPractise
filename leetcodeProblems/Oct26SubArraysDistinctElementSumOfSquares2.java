import java.util.*;

class Solution {
    int MOD = (int) (1e9 + 7);
    int n;
    long[] squareSumSegTree;
    long[] sumSegTree;
    long[] lazyTree;
    int MAX = (int) (1e5 + 1); // max. value of any nums element

    void lazyAddition(int i, int l, int r, long val) {
        int len = r - l + 1;
        squareSumSegTree[i] += 2 * val * sumSegTree[i] + len * val * val;
        sumSegTree[i] += (len * val);
    }

    void updateRange(int start, int end, int i, int l, int r, long val) {
        // query update range: {start, end}
        // node -> i -> range -> {l , r}
        // update with +val
        if (lazyTree[i] != 0) {
            lazyAddition(i, l, r, lazyTree[i]);
            // if not leaf node: tell other child nodes to update themselves later
            if (l != r) {
                lazyTree[2 * i + 1] += lazyTree[i];
                lazyTree[2 * i + 2] += lazyTree[i];
            }
            lazyTree[i] = 0;
        }

        // out-of-range
        if (l > end || r < start || l > r) {
            return;
        }

        // completetly within range
        if (l >= start && r <= end) {
            lazyAddition(i, l, r, val);

            if (l != r) {
                // tell the child nodes to update values later
                lazyTree[2 * i + 1] += val;
                lazyTree[2 * i + 2] += val;
            }

            return;
        }

        // overlapping
        int mid = l + (r - l) / 2;
        updateRange(start, end, 2 * i + 1, l, mid, val); // left tree update
        updateRange(start, end, 2 * i + 2, mid + 1, r, val); // right tree update

        // update the 2 segment trees
        sumSegTree[i] = sumSegTree[2 * i + 1] + sumSegTree[2 * i + 2];
        squareSumSegTree[i] = squareSumSegTree[2 * i + 1] + squareSumSegTree[2 * i + 2];
    }

    public int sumCounts(int[] nums) {
        // TC : O(nlogn) where logn due to updateRange()
        // SC : O(n)
        n = nums.length;

        squareSumSegTree = new long[4 * n];
        sumSegTree = new long[4 * n];
        lazyTree = new long[4 * n];

        int[] lastSeenIdx = new int[MAX];
        Arrays.fill(lastSeenIdx, -1); // stored -1 (not seen any nums[i] till now)

        long result = 0; // stores distinct square sum 

        for (int i = 0; i < n; i++) {
            // update range : {start, end}
            int start = lastSeenIdx[nums[i]] + 1;
            int end = i;

            updateRange(start, end, 0, 0, n - 1, 1);
            result = (result + squareSumSegTree[0]) % MOD; // root node gives square sum
            lastSeenIdx[nums[i]] = i;
        }

        return (int) result;
    }
}