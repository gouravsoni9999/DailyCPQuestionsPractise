class Solution {
    public int minMoves(int[] nums) {
        // TC : O(n)
        // SC : O(1)
        int n = nums.length;
        int mini = nums[0];
        for (int i = 1; i < n; i++) {
            mini = Math.min(mini, nums[i]);
        }
        int moves = 0;
        // increamenting n-1 elements by 1 == decrementing only 1 element by 1
        for (int val : nums) {
            moves += (val - mini);
        }
        return moves;
    }
}