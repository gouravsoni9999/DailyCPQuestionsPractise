class Solution {
    public String minWindow(String s, String t) {
        // TC : O(m+n)
        // SC : O(1)
        int m = s.length();
        int n = t.length();
        int[] hash = new int[256];
        for(char ch: t.toCharArray()){
            int idx = ch;
            hash[idx]++;
        }
        // sliding window technique
        int i = 0;
        int startIdx = -1;
        int minWinSize = Integer.MAX_VALUE;
        int cnt = 0;
        for(int j = 0;j < m;j++){
            char ch = s.charAt(j);
            int idx = ch;
            if(hash[idx] > 0){
                cnt++; // one more character of t found
            }
            hash[idx]--; // element found, decrement its freq
            while(cnt == n){
                // all characters of t are found
                int winSize = j-i+1;
                if(winSize < minWinSize){
                    minWinSize = winSize;
                    startIdx = i;
                }
                // start shrinking window
                char c = s.charAt(i);
                int index = c;
                hash[index]++;
                if(hash[index] > 0){
                    cnt--;
                }
                i++;
            }
        }
        return minWinSize == Integer.MAX_VALUE ? "" : s.substring(startIdx,startIdx+minWinSize);
    }
}
class Solution {
    public String minWindow(String s, String t) {
        // TC : O(n+m)
        // SC : O(k + n) where k is the total no. of unique chars in t and n is the max. length substring
        int n = s.length();
        int cntReq = t.length();//also known m
        if (cntReq > n)
            return "";
        Map<Character, Integer> freqMap = new HashMap<>();
        for (char ch : t.toCharArray()) { // O(m)
            freqMap.put(ch, freqMap.getOrDefault(ch, 0) + 1);
        }
        // iterate over string s
        // sliding window
        int i = 0;
        int start_i = 0;
        int winSize = Integer.MAX_VALUE;
        for (int j = 0; j < n; j++) { // O(n)
            char ch = s.charAt(j);
            if (freqMap.containsKey(ch)) {
                if (freqMap.get(ch) > 0) {

                    cntReq--;
                }
                freqMap.put(ch, freqMap.get(ch) - 1);
                while (cntReq == 0) {
                    // start shrinking the window 
                    int currWinSize = j - i + 1;
                    if (winSize > currWinSize) {
                        winSize = currWinSize;
                        start_i = i;
                    }
                    char c = s.charAt(i);
                    if (freqMap.containsKey(c)) {
                        freqMap.put(c, freqMap.get(c) + 1);
                        if (freqMap.get(c) > 0) {
                            cntReq++;
                        }
                    }
                    i++;
                }
            }
        }
        return winSize == Integer.MAX_VALUE ? "" : s.substring(start_i, start_i + winSize);//O(n)
    }
}

class Solution {
    public String minWindow(String s, String t) {
        // TC : O(n.(n+m))
        // SC : O(256) -> O(1)
        int m = t.length();
        int n = s.length();
        // brute solution: generate all subsets
        int minLen = Integer.MAX_VALUE;
        int startIdx = -1;
        for(int i = 0;i < n;i++){
            int[] hash = new int[256];
            int cnt = 0;
            for(int j = 0;j < m;j++){
                char ch = t.charAt(j);
                int idx = ch;
                hash[idx]++;
            }
            for(int j = i;j < n;j++){
                char ch = s.charAt(j);
                int idx = ch;
                if(hash[idx] > 0) cnt++;
                hash[idx]--;
                if(cnt == m){
                    if(j-i+1 < minLen){
                        minLen = j-i+1;
                        startIdx = i;
                        break;
                    }
                }
            }
        }
        return startIdx == -1? "" : s.substring(startIdx, startIdx+minLen);
    }
}
