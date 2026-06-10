class Solution {
    public int findContentChildren(int[] g, int[] s) {
        // TC : O(m*log(m))+O(n*log(n))+O(max(m,n))
        // SC : O(1)
        int ans = 0; // no. of content children
        // sort both the arrays
        Arrays.sort(g);
        Arrays.sort(s);
        int m = g.length; // m children
        int n = s.length; // n cookies
        int i = 0; // child pointer (g[] pointer)
        int j = 0; // cookie pointer (s[] pointer)
        // greedily assign the smallest size of cookie, which will content that ith child
        while (i < m && j < n) {
            if (g[i] <= s[j]) {
                // one more content child
                ans++;
                i++;
                j++;
            } else {
                // check for other cookies, if any other cookie satisfies the child
                j++;
            }
        }
        return ans;
    }
}

class Solution {
    public int findMaximumCookieStudents(int[] g, int[] s) {
        // TC : O(m*log(m))+O(n*log(n))+O(max(m,n))
        // SC : O(1)
        int ans = 0; // no. of content children
        // sort both the arrays
        Arrays.sort(g);
        Arrays.sort(s);
        int m = g.length; // m children
        int n = s.length; // n cookies
        int i = 0; // child pointer (g[] pointer)
        int j = 0; // cookie pointer (s[] pointer)
        // greedily assign the smallest size of cookie, which will content that ith child
        while (i < m && j < n) {
            if (g[i] <= s[j]) {
                // one more content child
                ans++;
                i++;
                j++;
            } else {
                // check for other cookies, if any other cookie satisfies the child
                j++;
            }
        }
        return ans;
    }
}
