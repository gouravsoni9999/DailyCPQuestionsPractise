class Solution {
    int n;

    private boolean solve(String s, int i, int bal) {
        if (bal < 0) {
            return false; // bal can't have more "(" than ")"
        }
        if (i == n) {
            return (bal == 0);
        }
        if (s.charAt(i) == '(') {
            return solve(s, i + 1, bal + 1);
        }
        if (s.charAt(i) == ')') {
            return solve(s, i + 1, bal - 1);
        }
        // else s.charAt(i) == '*'
        // 3 options
        // ignore it
        boolean ignore = solve(s, i + 1, bal);
        // treat it '('
        boolean left = solve(s, i + 1, bal + 1);
        // treat it ')'
        boolean right = solve(s, i + 1, bal - 1);
        return (ignore | left | right);
    }

    public boolean checkValidString(String s) {
        /*
            using recursion
            TC : O(3^k) where k is no. of '*'
            SC : O(n) stack space
        */
        n = s.length();
        return solve(s, 0, 0);
    }
}

class Solution {
    int n;
    Boolean[][] dp;
    private boolean solve(String s, int i, int bal) {
        if (bal < 0) {
            return false; // bal can't have more "(" than ")"
        }
        if (i == n) {
            return (bal == 0);
        }

        if(dp[i][bal] != null)
            return dp[i][bal];
        if (s.charAt(i) == '(') {
            return dp[i][bal] = solve(s, i + 1, bal + 1);
        }
        if (s.charAt(i) == ')') {
            return dp[i][bal] = solve(s, i + 1, bal - 1);
        }
        // else s.charAt(i) == '*'
        // 3 options
        // ignore it
        boolean ignore = solve(s, i + 1, bal);
        // treat it '('
        boolean left = solve(s, i + 1, bal + 1);
        // treat it ')'
        boolean right = solve(s, i + 1, bal - 1);
        return dp[i][bal] = (ignore | left | right);
    }

    public boolean checkValidString(String s) {
        /*
            using recursion + memoization
            TC : O(n^2) 
            SC : O(n^2) + O(n) stack space
        */
        n = s.length();
        dp = new Boolean[n][n];
        return solve(s, 0, 0);
    }
}

class Solution {
    public boolean checkValidString(String s) {
        /*
            using tabulation
            TC : O(n^2)
            SC : O(n^2)
        */
        int n = s.length();
        boolean[][] dp = new boolean[n + 1][n + 1];
        for (int i = n; i >= 0; i--) {
            for (int bal = n; bal >= 0; bal--) {
                if (i == n || bal == n) {
                    dp[i][bal] = (bal == 0) ? true : false;
                } else if (s.charAt(i) == '(') {
                    dp[i][bal] = dp[i + 1][bal + 1];
                } else if (s.charAt(i) == ')') {
                    dp[i][bal] = (bal-1 < 0) ? false : dp[i + 1][bal - 1];
                } else {
                    boolean ignore = dp[i+1][bal];
                    // treat it '('
                    boolean left = dp[i+1][bal+1];
                    // treat it ')'
                    boolean right = (bal-1 < 0) ? false : dp[i+1][bal-1];
                    dp[i][bal] = (ignore | left | right);
                }
            }
        }
        return dp[0][0];
    }
}

class Solution {
    public boolean checkValidString(String s) {
        /*
            using tabulation + space-optimization
            TC : O(n^2)
            SC : O(n)
        */
        int n = s.length();
        boolean[] next = new boolean[n + 1];
        for (int i = n; i >= 0; i--) {
            boolean[] curr = new boolean[n + 1];
            for (int bal = n; bal >= 0; bal--) {
                if (i == n || bal == n) {
                    curr[bal] = (bal == 0) ? true : false;
                } else if (s.charAt(i) == '(') {
                    curr[bal] = next[bal + 1];
                } else if (s.charAt(i) == ')') {
                    curr[bal] = (bal-1 < 0) ? false : next[bal - 1];
                } else {
                    boolean ignore = next[bal];
                    // treat it '('
                    boolean left = next[bal+1];
                    // treat it ')'
                    boolean right = (bal-1 < 0) ? false : next[bal-1];
                    curr[bal] = (ignore | left | right);
                }
            }
            next = curr;
        }
        return next[0];
    }
}

class Solution {
    public boolean checkValidString(String s) {
        // using stack
        // TC : O(n)
        // SC : O(n)

        Stack<Integer> stack1 = new Stack<>(); // stores index where '(' are present
        Stack<Integer> stack2 = new Stack<>(); // stores index where '*' are present

        int n = s.length();
        for(int i = 0;i < n;i++){
            char ch = s.charAt(i);
            if(ch == '('){
                stack1.push(i);
            }else if(ch == ')'){
                if(!stack1.isEmpty()){
                    stack1.pop();
                }else{
                    // no '(' present
                    if(!stack2.isEmpty()){
                        // but '*' are present
                        stack2.pop();
                    }else{
                        // niether '(' nor '*' 
                        return false;
                    }
                }
            }else{
                // if '*'
                stack2.push(i);
            }
        }

        // check if any remaining '(' or '*' is left or not
        while(!stack1.isEmpty() && !stack2.isEmpty()){
            if(stack1.peek() > stack2.peek()){
                // '* ... ('
                return false; // not possible as they are not balanced
            }else{
                // pop from both becoz they can balance each other
                // '(...*'
                stack1.pop();
                stack2.pop();
            }
        }

        return stack1.isEmpty(); // only check if all '(' were balanced 
    }
}

class Solution {
    public boolean checkValidString(String s) {
        // using 2-pointer 
        // TC : O(n)
        // SC : O(1)
        int n = s.length();

        int openCnt = 0;
        int closedCnt = 0;

        for(int i = 0;i < n;i++){
            // count '(' and '*' from start to end
            openCnt += (s.charAt(i) == '(' || s.charAt(i) == '*') ? 1 : -1;
            // count ')' and '*' from end to start
            closedCnt += (s.charAt(n-i-1) == ')' || s.charAt(n-i-1) == '*') ? 1 : -1;

            // if at any point, any cnts become negative, then string is not valid
            if(openCnt < 0 || closedCnt < 0){
                return false;
            }
        }

        return true;
    }
}