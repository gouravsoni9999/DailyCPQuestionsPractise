class Solution {
    List<String> res;
    int n;

    private char toggleCase(char ch) {
        return Character.isUpperCase(ch) ? Character.toLowerCase(ch) : Character.toUpperCase(ch);
    }

    private void solve(int i, String s) {
        if (i == n) {
            res.add(s);
            return;
        }
        if (Character.isDigit(s.charAt(i))) {
            solve(i + 1, s);
            return;
        }
        // donot toggle the case
        solve(i + 1, s);
        // make ith char toggle the case
        StringBuilder str = new StringBuilder();
        for (int j = 0; j < n; j++) {

            str.append((j == i) ? toggleCase(s.charAt(j)) : s.charAt(j));

        }
        solve(i + 1, str.toString());
    }

    public List<String> letterCasePermutation(String s) {
        // TC : O(2^k).n (n for string builder loop)
        // SC : O(2^k).n
        // where k is actual no. of letters present in s 
        res = new ArrayList<>();
        n = s.length();
        solve(0, s);
        return res;
    }
}
