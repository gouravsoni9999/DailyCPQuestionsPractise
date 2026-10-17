import java.util.*;
class Solution {
    public int sumCounts(List<Integer> nums) {
        // TC : O(n^2)
        // SC : O(n)
        int n = nums.size();
        int ans = 0;
        for (int i = 0; i < n; i++) {
            Set<Integer> distinctElements = new HashSet<>();
            for (int j = i; j < n; j++) {
                distinctElements.add(nums.get(j));
                ans += Math.pow(distinctElements.size(), 2);
            }
        }

        return ans;
    }
}