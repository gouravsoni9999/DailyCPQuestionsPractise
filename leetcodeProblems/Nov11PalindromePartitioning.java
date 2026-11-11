import java.util.*;

class Solution {
    int n;
    List<List<String>> res;

    private boolean isPalindrome(String s, int i, int j) {
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    private void solve(int i, String s, List<String> list) {
        if (i == n) {
            res.add(new ArrayList<>(list));
            return;
        }
        for (int j = i; j < n; j++) {
            if (isPalindrome(s, i, j)) {
                list.add(s.substring(i, j + 1)); // add
                solve(j + 1, s, list); // do
                // remove: backtrack
                list.remove(list.size() - 1);
            }
        }
    }

    public List<List<String>> partition(String s) {
        // TC : exponential
        n = s.length();
        res = new ArrayList<>();
        solve(0, s, new ArrayList<>());
        return res;
    }
}