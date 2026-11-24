import java.util.Arrays;
class GreedySolution {
    public int findLongestChain(int[][] pairs) {
        // TC : O(nlogn)
        // SC : O(1)
        // greedy
        Arrays.sort(pairs, (a,b)->{
            return Integer.compare(a[1], b[1]);
        }); // sort by 2nd element in pair

        int longestChain = 0;
        int curr = Integer.MIN_VALUE; // keeps track of last element of the current chain

        for(int[] p : pairs){
            if(curr < p[0]){
                curr = p[1];
                longestChain++;
            }
        }

        return longestChain;
    }
}

class MemoizedSolution {
    int n;
    Integer[][] dp;

    private int solve(int prevIdx, int currIdx, int[][] pairs) {
        if (currIdx == n)
            return 0; // no chain 

        if (dp[prevIdx + 1][currIdx] != null)
            return dp[prevIdx + 1][currIdx];

        int take = 0;
        int skip = solve(prevIdx, currIdx + 1, pairs);

        if (prevIdx == -1 || pairs[prevIdx][1] < pairs[currIdx][0]) {
            take = 1 + solve(currIdx, currIdx + 1, pairs);
        }

        return dp[prevIdx + 1][currIdx] = Math.max(take, skip);
    }

    public int findLongestChain(int[][] pairs) {
        // TC : O(n.n)
        // SC : O(n.n)
        // DP: similar to LIS
        n = pairs.length;

        dp = new Integer[n + 1][n + 1];
        Arrays.sort(pairs, (a, b) -> {
            return Integer.compare(a[0], b[0]);
        }); // sort by 1st element in pair

        return solve(-1, 0, pairs);
    }
}

class TabulatedSolution {
    int n;
    int[][] dp;

    public int findLongestChain(int[][] pairs) {
        // TC : O(n.n)
        // SC : O(n.n)
        // DP: similar to LIS
        n = pairs.length;

        dp = new int[n + 1][n + 1];
        Arrays.sort(pairs, (a, b) -> {
            return Integer.compare(a[0], b[0]);
        }); // sort by 1st element in pair

        for (int currIdx = n - 1; currIdx >= 0; currIdx--) {
            for (int prevIdx = currIdx - 1; prevIdx >= -1; prevIdx--) {
                if (currIdx == n)
                    dp[prevIdx + 1][currIdx] = 0; // no chain 

                else {
                    int take = 0;
                    int skip = dp[prevIdx + 1][currIdx + 1];

                    if (prevIdx == -1 || pairs[prevIdx][1] < pairs[currIdx][0]) {
                        take = 1 + dp[currIdx + 1][currIdx + 1];
                    }

                    dp[prevIdx + 1][currIdx] = Math.max(take, skip);
                }
            }
        }

        return dp[0][0];
    }
}

