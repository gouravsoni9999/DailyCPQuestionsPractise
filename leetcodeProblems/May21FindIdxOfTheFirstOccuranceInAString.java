import java.util.regex.Pattern;
import java.util.regex.Matcher;
class Solution {
    public int strStr(String haystack, String needle) {
        // using regex
        // if n = length of haystack and m = length of needle
        // TC : O(n.m) // in worst case
        // SC : O(m)   // auxilary space taken by pattern object and matcher object only takes O(1) space
        Pattern pattern = Pattern.compile(needle); // O(m)
        Matcher matcher = pattern.matcher(haystack);
        if(matcher.find()){ // O(n.m)
            return matcher.start();
        }else{
            return -1;
        }
    }
}

class Solution {
    public int strStr(String haystack, String needle) {
        // n is length of haystack and m is length of needle
        // TC : O(n.m) 
        // SC : O(1)
        return haystack.indexOf(needle); // returns first occur. of needle in haystack
    }
}

    class Solution {
        public int strStr(String haystack, String needle) {
            // TC : O(m.n)
            // SC : O(1)
            int n = needle.length();
            int m = haystack.length();
            // empty need always matches at idx 0
            if(n == 0) return 0;
            for(int j = 0;j < m;j++){ // haystack iterator
                if(needle.charAt(0) == haystack.charAt(j)){
                    // if needle is only 1 char long, we found our match!
                    if(n == 1) return j; 
                    int start = j;
                    int i = 1; // needle iterator
                    int k = j+1;
                    while(k < m && needle.charAt(i) == haystack.charAt(k)){
                        k++;i++;
                        if(i == n){
                            return start;
                        }
                    }
                }
            }
            return -1;
        }
    }

class Solution {
    public int strStr(String haystack, String needle) {
        // TC : O(m.n)
        // SC : O(1)
        int m = haystack.length();
        int n = needle.length();
        for(int i = 0;i <= m-n;i++){// a small optimization, only look for i from 0 to m-n
            for(int j = 0;j < n;j++){
                if(haystack.charAt(i+j) != needle.charAt(j)){
                    break;
                }
                if(j == n-1) return i;
            }
        }
        return -1;
    }
}
