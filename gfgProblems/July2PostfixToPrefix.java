class Solution {
    static String postToPre(String s) {
        // TC : O(n)
        // SC : O(n)
        Stack<String> stack = new Stack<>();
        for(char ch : s.toCharArray()){
            if(Character.isLetterOrDigit(ch)){
                stack.push("" + ch);
            }else{
                // ch is operator
                String s1 = stack.pop();
                String s2 = stack.pop();
                stack.push(ch + s2 + s1);
            }
        }
        return stack.pop();
    }
}