class Solution {
    int n;

    private boolean solve(String s, int start, int end) {
        if (start >= end) {
            return true;
        }
        char startChar = s.charAt(start);
        char endChar = s.charAt(end);
        if (!Character.isLetterOrDigit(startChar)) {
            // skip that character
            return solve(s, start + 1, end);
        }
        if (!Character.isLetterOrDigit(endChar)) {
            // skip that character
            return solve(s, start, end - 1);
        }
        if (Character.toLowerCase(startChar) != Character.toLowerCase(endChar))
            return false;
        return solve(s, start + 1, end - 1);
    }

    public boolean isPalindrome(String s) {
        // TC : O(n)
        // SC : O(1) but stack space : O(n)
        n = s.length();
        // find valid palindrome through recursion
        return solve(s, 0, n - 1); 
    }
}

class Solution {
    public boolean isPalindrome(String s) {
        // TC : O(n)
        // SC : O(1)
        int n = s.length();
        // iterative version
        int start = 0;
        int end = n-1;
        while(start < end){
            char startChar = s.charAt(start);
            char endChar = s.charAt(end);
            if(!Character.isLetterOrDigit(startChar)){
                start++;//skip that character
                continue;
            }
            if(!Character.isLetterOrDigit(endChar)){
                end--;//skip that character
                continue;
            }
            if(Character.toLowerCase(startChar) != Character.toLowerCase(endChar)){
                return false;
            }
            start++;
            end--;
        }
        return true;
    }
}

class Solution {
    public boolean isPalindrome(String s) {
        // TC : O(n)
        // SC : O(n)
        int n = s.length();
        StringBuilder str = new StringBuilder();
        for(char ch: s.toCharArray()){
            if(!Character.isLetterOrDigit(ch)){
                continue;
            }else{
                str.append(Character.toLowerCase(ch));
            }
        }
        String forward = str.toString();
        String backward = str.reverse().toString();
        if(forward.equals(backward))return true;
        return false;
    }
}
