class Solution {
    public int romanToInt(String s) {
        // TC : O(n) where n is the no. of characters in string s
        // SC : O(k) = O(n) where k no. of unique roman numerals will be stored in list 
        Map<Character, Integer> map = new HashMap<>();
        map.put('I',1);
        map.put('V',5);
        map.put('X',10);
        map.put('L',50);
        map.put('C',100);
        map.put('D',500);
        map.put('M',1000);
        List<Integer> list = new ArrayList<>();
        for(char ch: s.toCharArray()){
            int currVal = map.get(ch);
            if(list.isEmpty()) 
                list.add(currVal);
            else{
                int lastVal = list.get(list.size()-1);
                if(lastVal < currVal){
                    // the case where small comes first then big comes
                    list.set(list.size()-1, currVal-lastVal);
                }else{
                    // the case where either greater or equal to value comes first
                    list.add(currVal);
                }
            }
        }
        int ans = 0;
        for(int num: list){
            ans += num;
        }
        return ans;
    }
}

class Solution {
    public int romanToInt(String s) {
        // TC : O(n) where n is the no. of characters in string s
        // SC : O(1)
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
        int ans = 0;
        int n = s.length();
        int i = 0;
        while(i < n) {
            int currVal = map.get(s.charAt(i));
            int nextVal = 0;
            if(i+1 < n)
                nextVal = map.get(s.charAt(i+1));
            if(currVal < nextVal){
                ans -= currVal;
            }else{
                ans += currVal;
            }
            i++;
        }
        return ans;
    }
}
