class Solution {
    int cnt;
    int m;
    Map<Character, List<Integer>> map;
    private void solve(String s,String t) {
        int n = s.length();
        // then search for chars of t in map such that it should always be greater than prev idx
        int currTIdx = -1;
        int i = 0;
        for (; i < n; i++) {
            char ch = s.charAt(i);
            if (!map.containsKey(ch)) {
                return;//not present in t
            } else {
                List<Integer> indices = map.get(ch);
                // if ch present in t: find idx of ch > prev
                int idx = Collections.binarySearch(indices, currTIdx + 1);
                if (idx < 0) {
                    // prev not present in list
                    // find insertion point
                    idx = -idx - 1;//gives index where that prev should actually be present
                }
                // if insertion point is at the end, no index t is large enough
                if (idx == indices.size())
                    return;
                currTIdx = indices.get(idx);//update prev to actual index in string t
            }
        }
        if (i == n) {
            cnt++;
        }
    }

    public int numMatchingSubseq(String t, String[] words) {
        // TC : O(L+N.K.Log(L))
        // where L = len. of string t
        // N is no of word in words
        // K is avg. length of each word
        // SC : O(L) for hashmap
        m = t.length();
        // first create char to idx mappings
        map = new HashMap<>();
        for (int i = 0; i < m; i++) {
            char ch = t.charAt(i);
            if (!map.containsKey(ch)) {
                map.put(ch, new ArrayList<>());
            }
            map.get(ch).add(i);
        }
        cnt = 0;
        for (String s : words) {
            solve(s,t);
        }
        return cnt;
    }
}

class Solution {
    int cnt = 0;
    public void isSubsequence(String s, String t) {
        int m = s.length();
        int n = t.length();
        int i = 0;
        int j = 0;
        while (i < m && j < n) {
            char ch1 = s.charAt(i);
            char ch2 = t.charAt(j);
            if (ch1 != ch2) {
                j++;//find next character of s in t
            } else {
                // if same, move both pointers
                i++;
                j++;
            }
        }
        if (i == m) {
            // found all characters of i
            cnt++;
        }
    }

    public int numMatchingSubseq(String t, String[] words) {
        // let no. of words be W
        // TC : O(W.N)
        // SC : O(1)
        for(String s: words){
            isSubsequence(s, t);
        }
        return cnt;
    }
}
