class Solution {
    public String removeKdigits(String num, int k) {
        // TC : O(n)
        // SC : O(n)
        // using stringbuilder as stack
        StringBuilder res = new StringBuilder();

        for (char ch : num.toCharArray()) {
            while (!res.isEmpty() && k > 0 && (res.charAt(res.length() - 1) - '0') > (ch - '0')) { // find peek > digit
                res.deleteCharAt(res.length() - 1); // pop out -> O(1)
                k--; // 1 char deleted
            }
            res.append(ch);
        }

        while (!res.isEmpty() && k > 0) {
            // k is still left
            res.deleteCharAt(res.length() - 1); // pop out -> O(1)
            k--;
        }

        int len = res.length();
        int start = 0; // tracks start of string(without leading zeroes)

        while(start < len && res.charAt(start) == '0'){ // O(n)
            start++; // -> O(1)
        }

        return (start == len) ? "0" : res.substring(start);

    }
}

class Solution {
    public String removeKdigits(String num, int k) {
        // using stringbuilder as stack
        // TC : O(n^2)
        // SC : O(n)
        StringBuilder res = new StringBuilder();

        for (char ch : num.toCharArray()) {
            while (!res.isEmpty() && k > 0 && (res.charAt(res.length() - 1) - '0') > (ch - '0')) { // find peek > digit
                res.deleteCharAt(res.length() - 1); // pop out
                k--; // 1 char deleted
            }
            res.append(ch);
        }
        while (!res.isEmpty() && k > 0) {
            // k is still left
            res.deleteCharAt(res.length() - 1); // pop out
            k--;
        }

        while (!res.isEmpty() && res.charAt(0) == '0') { // O(n) at worst case
            res.deleteCharAt(0); // delete first character as they are leading zeroes // O(n) at worst case
        }

        return res.isEmpty() ? "0" : res.toString();
    }
}