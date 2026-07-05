class Solution {
    public int minInsertions(String s) {
        // using stack
        // TC : O(n)
        // SC : O(n)

        Stack<Character> stack = new Stack<>(); // store only '('
        int insertionCnt = 0;
        int n = s.length();
        int i = 0;
        while (i < n) {
            char ch = s.charAt(i);
            if (ch == '(') {
                stack.push('(');
            } else {
                // ch == ')'
                if (!stack.isEmpty()) {
                    stack.pop();
                } else {
                    insertionCnt++; // add '('
                }
                i++;
                if(i == n || s.charAt(i) != ')'){
                    insertionCnt++;
                    i--; // as this character is invalid, therefore we need to check it again
                }
            }
            i++;
        }

        return 2 * stack.size() + insertionCnt;
    }
}