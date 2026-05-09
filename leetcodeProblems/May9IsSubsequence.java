class Solution {
    public boolean isSubsequence(String s, String t) {
        // TC : O(n)
        // SC : O(1)
        int m = s.length();
        int n = t.length();
        int i = 0;
        int j = 0;
        while(i < m && j < n){
            char ch1 = s.charAt(i);
            char ch2 = t.charAt(j);
            if(ch1 != ch2){
                j++;//find next character of s in t
            }
            else{
                // if same, move both pointers
                i++;
                j++;
            }
        }
        if(i == m){
            // found all characters of i
            return true;
        }
        return false;
    }
}

class Solution {
    public boolean isSubsequence(String s, String t) {
	// TC : O(n+mlog(n))
	// SC : O(n)
        int m = s.length();
        int n = t.length();
        // first create char to idx mappings
        Map<Character, List<Integer>> map = new HashMap<>();
        for(int i = 0;i < n;i++){
            char ch = t.charAt(i);
            if(!map.containsKey(ch)){
                map.put(ch, new ArrayList<>());
            }
            map.get(ch).add(i);
        }
        // then search for chars of s in map such that it should always be greater than prev idx
        int currTIdx = -1;
        int i = 0;
        for(;i < m;i++){
            char ch = s.charAt(i);
            if(!map.containsKey(ch)){
                return false;//not present in t
            }else{
                List<Integer> indices = map.get(ch);
                // if ch present in t: find idx of ch > prev
                int idx = Collections.binarySearch(indices,currTIdx+1);
                if(idx < 0){
                    // prev not present in list
                    // find insertion point
                    idx = -idx-1;//gives index where that prev should actually be present
                }
                // if insertion point is at the end, no index t is large enough
                if(idx == indices.size()) return false;
                currTIdx = indices.get(idx);//update prev to actual index in string t
            }
        }
        if(i == m){
            return true;
        }
        return false;
    }
}
