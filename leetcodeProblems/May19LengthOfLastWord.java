class Solution {
        public int lengthOfLastWord(String s) {
            // TC : O(n)
            // SC : O(n) 
            // first trimming leading and trailing spaces
            // then splitting acc. to space characters(regex)
            String[] arr = s.trim().split("\\s+");
            int n = arr.length;
            return arr[n-1].length();
	}
}

class Solution {
    public int lengthOfLastWord(String s) {
        // TC : O(n) in worst case, every char. needs to be traversed
        // SC : O(1)
        int n = s.length();
        int i = n-1;
        // go from right to left and find the first non-space char
        while(i >= 0){
            char ch = s.charAt(i);
            if(ch == ' '){
                i--;
            }else{
                break;
            }
        }
        int length = 0;
        // iterate until a space is found or i < 0
        while(i >= 0 && s.charAt(i) != ' '){
            i--;
            length++;
        }
        return length;
    }
}

class Solution {
    public int lengthOfLastWord(String s) {
        // TC : O(n)
        // SC : O(n) // as s is immutable in java and we are making new string
        // using library functions
        // using regex to replace all whitespace characters represented as 
        // \\s and + represents more than 1 whitespace char. 
        // $ represents replacing only at the end of the string
        s = s.replaceAll("\\s+$",""); // trims the trailing spaces

        // now find the last space characters in s
        int lastSpace = s.lastIndexOf(' ');

        // calculate the length of the last word
        if(lastSpace == -1){
            // no last space present
            // eg: "abcd" doesnot have any space now
            return s.length();
        }else{
            // last space present
            // eg "ab  cd"
            return s.length()-lastSpace-1;
        }

    }
}
