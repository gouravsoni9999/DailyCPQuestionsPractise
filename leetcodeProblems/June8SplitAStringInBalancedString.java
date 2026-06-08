class Solution {
    public int balancedStringSplit(String s) {
        // TC : O(n)
        // SC : O(1)
        int n = s.length();
        int cnt = 0;
        int balance = 0;
        for (int j = 0; j < n; j++) {
            if (s.charAt(j) == 'L') {
                balance--;
            } else {
                balance++;
            }
            if (balance == 0) {
                // one substring found
                cnt++;
            }
        }
        return cnt;
    }
}
