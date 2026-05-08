class Solution {
    int m;
    int n;
    private void swap(String[] arr,int i, int j){
        String temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    private void generatePermutations(String[] arr,int i,Set<String> set){
        if(i == n){
            set.add(String.join("",arr));
            return;
        }
        for(int j = i;j < n;j++){
            swap(arr,i,j);
            generatePermutations(arr, i+1, set);
            swap(arr, i, j);//backtrack
        }
    }
    public List<Integer> findSubstring(String s, String[] words) {
        // TC : O(n!)+O(m^2)
        // SC : O(n!)
        List<Integer> list = new ArrayList<>();
        m = s.length();
        n = words.length;
        Set<String> set = new HashSet<>();
        generatePermutations(words, 0, set);
        for(int i = 0;i < m;i++){
            String str = "";
            for(int j = i;j < m;j++){
                str += s.charAt(j);
                if(set.contains(str)){
                    list.add(i);
                    break;
                }
            }
        }
        return list;
    }
}

class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        // TC : O((m+n).L)
        // SC : O(n.L) + O(m) + O(L) = O(n.L+m) where n.L is due to maps, m is due to res, L is due to substring
        List<Integer> res = new ArrayList<>();
        int m = s.length();
        int n = words.length;
        int L = words[0].length();//word size
        // make a freqMap of words
        Map<String, Integer> freqMap = new HashMap<>();
        for (String word : words) { // O(n.L)
            freqMap.put(word, freqMap.getOrDefault(word, 0) + 1);
        }

        // since starting point can be from 0 till L-1
        // therefore start from every starting point
        for (int start = 0; start < L; start++) { // O(L)
            Map<String, Integer> currMap = new HashMap<>();//for everytime, it would be used
            int left = start;
            int count = 0;//count no. of words present in window 
            // using sliding window to move right and shrink left whereever possible
            for (int right = left; right <= m - L; right += L) { // O(m/L)
                // right <= m-wordSize is done for extracting word without StringOutOfBoundException
                String word = s.substring(right, right+L);//O(L)
                if(freqMap.containsKey(word)){
                    //put in current window
                    currMap.put(word, currMap.getOrDefault(word, 0)+1);//O(L)
                    count++;
                    // if too many words than in freqMap, shrink by moving left
                    while(currMap.get(word) > freqMap.get(word)){
                        String leftWord = s.substring(left, left+L);
                        currMap.put(leftWord, currMap.get(leftWord)-1);
                        count--;
                        left += L;
                    }

                    if(count == n) res.add(left);
                }else{
                    // invalid word: reset everything for this window
                    currMap.clear();
                    count = 0;
                    left = right + L;// new window starts from right + L
                }
            }
        }
        return res;
    }
}
