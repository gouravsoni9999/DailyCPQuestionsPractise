import java.util.*;
class Solution {
    static String postToInfix(String exp) {
        // TC : O(n)
        // SC : O(n)
        Stack<String> stack = new Stack<>(); 
        
        for(char ch : exp.toCharArray()){
            if(Character.isLetterOrDigit(ch)){
                // operand : simply push in stack
                stack.push("" + ch);
            }
            else{
                // operator
                String s1 = stack.pop();
                String s2 = stack.pop();
                stack.push("(" + s2 + ch + s1 + ")");
            }
        }
        
        return stack.pop();
    }
}