class Solution {
    int n;
    Integer[] dp;

    private int countOpns(String s1,String s2){
        int len = s1.length();
        int[][] save = new int[26][26]; // stores no. of operations done char1 -> char2
        int opns = 0;

        for(int i = 0;i < len;i++){
            char ch1 = s1.charAt(i);
            char ch2 = s2.charAt(i);
            if(ch1 == ch2)
                continue;
            if(save[ch2 - 'a'][ch1 - 'a'] > 0){
                // operation was done
                // now since its reverse was found -> swap operation 
                // no need to add in opns(as replace made it increment already)
                save[ch2 - 'a'][ch1 - 'a']--; // reduce it, because one swap operation was done
            }else{
                save[ch1 - 'a'][ch2 - 'a']++; // stores this replace operation, so if swap can be done, it can be found easily
                opns++; // as one replcae operation was done
            }
        }

        return opns;
    }
    private int solve(int i,String word1,String word2){
        if(i == n){
            // base case
            return 0; // no operation required
        }

        if(dp[i] != null)
            return dp[i];

        int minOpns = Integer.MAX_VALUE;

        for(int j = i;j < n;j++){
            // we extract fixed length of strings
            String s1 = word1.substring(i,j+1);
            String s2 = word2.substring(i,j+1);

            // case 1: without reverse(only swap and replace operations)
            int op1Cnt = countOpns(s1, s2);
            
            // case 2: with reverse
            s1 = new StringBuilder(s1).reverse().toString();
            int op2Cnt = 1 + countOpns(s1, s2);

            // recurse for remaining part of string
            int remainingPart = solve(j+1, word1, word2);

            minOpns = Math.min(minOpns, Math.min(op1Cnt, op2Cnt) + remainingPart);
        }
        
        return dp[i] = minOpns;
    }
    public int minOperations(String word1, String word2) {
        n = word1.length();
        dp = new Integer[n];
        return solve(0, word1, word2); // using recursion 
    }
}