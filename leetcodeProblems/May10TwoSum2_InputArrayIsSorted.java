class Solution {
    public int[] twoSum(int[] numbers, int target) {
        // TC : O(n)
        // SC : O(1)
        int n = numbers.length;
        int left = 0;
        int right = n-1;
        int[] ans = new int[2];
        while(left < right){
            int x = numbers[left];
            int y = numbers[right];
            if(x+y == target){
                // 1-based indexing
                ans[0] = left+1;
                ans[1] = right+1;
                break;//as we got the answer
            }
            if(x+y > target){
                right--;//this should be decreased so that sum becomes small or equal to target
            }else{
                left++;// this should be increased so that sum becomes large or equal to target
            }
        }
        return ans;
    }
}

class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int n = numbers.length;
        int[] ans = new int[2];
        for(int i = 0;i < n;i++){
            int x = numbers[i];
            int idx = Arrays.binarySearch(numbers, target-x);
            if(idx < 0 || idx == i){
                // either target-x not present or not same 
                continue;//see others
            }else{
                // as it is 1-based indexing, therefore +1
                if(idx > i){
                    ans[0] = i+1;
                    ans[1] = idx+1;
                }else{
                    ans[1] = i+1;
                    ans[0] = idx+1;
                }
                break;//found answer
            }
        }
        return ans;
    }
}

class Solution {
    public int[] twoSum(int[] numbers, int target) {
        // TC : O(n^2)
        // SC : O(1)
	// gives TLE
        int n = numbers.length;
        int[] ans = new int[2];
        for(int i = 0;i < n;i++){
            int x = numbers[i];
            for(int j = i+1;j < n;j++){
                int y = numbers[j];
                if(x+y == target){
                    // since it is 1-based indexing
                    ans[0] = i+1;
                    ans[1] = j+1;
                    break;
                }
            }
        }
        return ans;
    }
}

class Solution {
    public int[] twoSum(int[] numbers, int target) {
        // TC : O(n)
        // SC : O(n)
        int n = numbers.length;
        // using hashing
        Map<Integer, Integer> map = new HashMap<>(); // {num present in array : index where it is present}
        int[] ans = new int[2];
        for(int i = 0;i < n;i++){
            int x = numbers[i];
            int y = target - x;
            if(map.containsKey(y)){
                // as it is 1-based indexing
                ans[0] = map.get(y)+1;
                ans[1] = i+1;
                break;//as we found out answer
            }
            map.put(x, i);
        }
        return ans;        
    }
}
