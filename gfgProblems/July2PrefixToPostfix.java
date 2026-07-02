class Solution {
    static String preToPost(String s) {
        // TC : O(n)
        // SC : O(n)
        int n = s.length();
        Stack<String> stack = new Stack<>();
        for(int i = n-1;i >= 0;i--){
            char ch = s.charAt(i);
            
            if(Character.isLetterOrDigit(ch)){
                stack.push("" + ch);
            }
            
            else{
                // ch is operator
                String s1 = stack.pop();
                String s2 = stack.pop();
                
                stack.push(s1 + s2 + ch);
            }
        }
        
        return stack.pop();
    }
}