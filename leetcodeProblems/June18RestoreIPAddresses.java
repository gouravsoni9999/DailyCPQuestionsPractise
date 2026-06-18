class Solution {
    List<String> res;
    int n;

    private boolean isValid(String str) {
        if (str.charAt(0) == '0') {
            return false; // not valid
        }
        int val = Integer.parseInt(str);
        if (val >= 0 && val <= 255) {
            // valid range
            return true;
        }
        return false;
    }

    private void solve(int i, String curr, int parts, String s) {
        if (i == n) {
            if (parts == 4) {
                // successfully put all 4 parts
                // remove last dot
                curr = curr.substring(0, curr.length() - 1);
                res.add(curr);
            }
            return;
        }

        // make a part
        if (i + 1 <= n)
            // no need to check validity (can be 0 also)
            solve(i + 1, curr + s.charAt(i) + '.', parts + 1, s); // take only 1 char
        if (i + 2 <= n && isValid(s.substring(i, i + 2)))
            solve(i + 2, curr + s.substring(i, i + 2) + '.', parts + 1, s); // take only 2 char
        if (i + 3 <= n && isValid(s.substring(i, i + 3)))
            solve(i + 3, curr + s.substring(i, i + 3) + '.', parts + 1, s); // take only 3 char
    }

    public List<String> restoreIpAddresses(String s) {
        /*
            using recursion + backtracking

            Time Complexity: O(1). The recursion tree has a maximum depth of 4 levels, and at each level, there are at most 3 choices. The maximum number of states looked at is finite and small (3^4=81 configurations).

            Space Complexity: O(1) auxiliary space, as the recursion stack maxes out at a depth of 4.
        */
        res = new ArrayList<>();
        n = s.length();
        if (n > 12)
            // need to divide only in 4 parts, therefore at max only 12 digits are required
            return res;
        solve(0, "", 0, s);
        return res;
    }
}
