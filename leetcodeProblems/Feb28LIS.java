class Solution {
    int n;
    List<List<Integer>> list;

    private boolean isIncreasing(List<Integer> currList) {
        for (int i = 1; i < currList.size(); i++) {
            if (currList.get(i) <= currList.get(i - 1)) {
                return false;
            }
        }
        return true;
    }

    private void solve(int i, int[] nums, List<Integer> currList) {
        if (i == n) {
            list.add(new ArrayList<>(currList));
            return;
        }
        // using recursion + backtracking
        // include and explore
        currList.add(nums[i]);
        solve(i + 1, nums, currList);
        // exclude and explore
        currList.remove(currList.size() - 1);
        solve(i + 1, nums, currList);
    }

    public int lengthOfLIS(int[] nums) {
        n = nums.length;
        // brute force: generate all subsequences and then check for increasing subseq
        // then find max. length LIS
        // TC : O(2^n . n)
        // SC : O(n + 2^n . n)
        list = new ArrayList<>();
        solve(0, nums, new ArrayList<>());
        System.out.println(list);
        int ans = 0;
        for (List<Integer> currList : list) {
            if (isIncreasing(currList)) {
                ans = Math.max(ans, currList.size());
            }
        }
        return ans;
    }
}

class Solution {
    int n;
    List<List<Integer>> list;

    private boolean isIncreasing(List<Integer> currList) {
        for (int i = 1; i < currList.size(); i++) {
            if (currList.get(i) <= currList.get(i - 1)) {
                return false;
            }
        }
        return true;
    }

    private void solve(int[] nums) {
        // using powerset
        for (int num = 0; num < (1 << n); num++) { // loop from 0 to 2^n-1
            List<Integer> subList = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                // i is the ith bit
                if ((num & (1 << i)) != 0) {
                    // ith bit is set, take that index value in substring
                    subList.add(nums[i]);
                }
            }
            list.add(new ArrayList<>(subList));
        }
    }

    public int lengthOfLIS(int[] nums) {
        n = nums.length;
        // brute force: generate all subsequences and then check for increasing subseq
        // then find max. length LIS
        // TC : O(2^n . n)
        // SC : O(n + 2^n . n)
        list = new ArrayList<>();
        solve(nums);
        System.out.println(list);
        int ans = 0;
        for (List<Integer> currList : list) {
            if (isIncreasing(currList)) {
                ans = Math.max(ans, currList.size());
            }
        }
        return ans;
    }
}

class Solution {
    int n;

    private int solve(int[] nums, int i, int prevIdx) {
        if (i == n) {
            return 0;
        }
        int not_take_len = 0 + solve(nums, i + 1, prevIdx);
        int take_len = 0;
        if (prevIdx == -1 || nums[prevIdx] < nums[i]) {
            take_len = 1 + solve(nums, i + 1, i); // prevIdx becomes i
        }
        return Math.max(not_take_len, take_len);
    }

    public int lengthOfLIS(int[] nums) {
        /*
         * using recursion
         * TC : O(2^n)
         * SC : O(n) // stack space
         */
        n = nums.length;
        return solve(nums, 0, -1);
    }
}

class Solution {
    int n;
    Integer[][] dp;
    private int solve(int[] nums, int i, int prevIdx){
        if(i == n){
            return 0;
        }
        if(dp[i][prevIdx+1] != null){
            return dp[i][prevIdx+1];
        }
        int not_take_len = 0 + solve(nums, i+1, prevIdx);
        int take_len = 0;
        if(prevIdx == -1 || nums[prevIdx] < nums[i]){
            take_len = 1 + solve(nums, i+1, i); // prevIdx becomes i
        }
        return dp[i][prevIdx+1] = Math.max(not_take_len, take_len);
    }
    public int lengthOfLIS(int[] nums) {
        /*
            using recursion + memoization
            TC : O(n*n)
            SC : O(n*n) O(n) // stack space
        */
        n = nums.length;
        // in dp array I made prevIdx -> prevIdx+1 for only array, coord. change
        dp = new Integer[n][n+1];
        return solve(nums, 0, -1);
    }
}

class Solution {
    public int lengthOfLIS(int[] nums) {// O(n^2)
        int n = nums.length;
        // tabulation
        // dp[i] repreents LIS till index i
        int[] dp = new int[n];
        Arrays.fill(dp, 1);// every element is itself LIS
        int maxLIS = 1;// atleast one of the element is present in LIS
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    // if prev is smaller
                    // add with its own LIS
                    dp[i] = Math.max(dp[i], 1 + dp[j]);// add that element
                    maxLIS = Math.max(maxLIS, dp[i]);

                } // else can't add to dp[i]
            }
        }
        return maxLIS;
    }
}

class Solution {
    private int binSrch(List<Integer> temp, int val) { // logn
        int ans = -1;
        int s = 0;
        int e = temp.size() - 1;
        while (s <= e) {
            int mid = s + (e - s) / 2;
            if (temp.get(mid) >= val) {
                e = mid - 1;
                ans = mid;
            } else {
                s = mid + 1;
            }
        }
        return ans;
    }

    public int lengthOfLIS(int[] nums) {
        // using patience sort
        // make ls
        // iterate over every element x(i),
        // if element x < any element in ls-> replace with x
        // else add the element in the list
        List<Integer> temp = new ArrayList<>();
        for (int val : nums) {// - n
            if (temp.size() == 0)
                temp.add(val);
            else {
                // implement binSrch for finding an element in temp >= val
                int idx = binSrch(temp, val);
                if (idx != -1)
                    temp.set(idx, val);
                else
                    temp.add(val);
            }
        }
        return temp.size();// O(nlogn)
    }
}
