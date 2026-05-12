class Solution {
    public int majorityElement(int[] nums) {
        // TC : O(nlogn)+O(n)
        // SC : O(1)
        Arrays.sort(nums);
        int n = nums.length;
        int cnt = (int)Math.floor(n/2.0);
        int j = 0;
        while(j < n){
            int i = j;
            while((j+1 < n) && (nums[j] == nums[j+1])){
                j++;
            }
            if(j-i+1 > cnt){
                return nums[j];//found majority element
            }
            j++;//else see other elements
        }
        return -1;//no element found
    }
}

class Solution {
    public int majorityElement(int[] nums) {
        // brute solution
        // TC : O(n^2)
        // SC : O(1)
        int n = nums.length;
        int minCnt = (int)Math.floor(n/2.0);
        for(int i = 0;i < n;i++){
            int cnt = 0;
            for(int j = 0; j < n;j++){
                if(nums[i] == nums[j]){
                    cnt++;
                }
            }
            if(cnt > minCnt){
                return nums[i];
            }
        }
        return -1;
    }
}

class Solution {
    public int majorityElement(int[] nums) {
        // TC : O(n)+O(k) assuming k is no. of unique elements
        // SC : O(k)
        int n = nums.length;
        int cnt = (int)Math.floor(n/2.0);
        Map<Integer, Integer> map = new HashMap<>();
        for(int num: nums){
            map.put(num, map.getOrDefault(num,0)+1);
        }
        for(int key: map.keySet()){
            if(map.get(key) > cnt){
                return key;
            }
        }
        return -1;
    }
}

class Solution {
    public int majorityElement(int[] nums) {
        // TC : O(2n)
        // SC : O(1)
        // boyer-moore's voting algo
        int n = nums.length;
        int minCnt = (int)Math.floor(n/2.0);
        int element = nums[0];
        int cnt = 1;
        // iterate over array, if element is found cnt++, else cnt--, until 0
        for(int i = 1;i < n;i++){
            if(cnt == 0){
                // starting again
                cnt = 1;
                element = nums[i];
            }
            else if (nums[i] == element){
                cnt++;
            }else{
                cnt--;
            }
        }
        // verify that element is majority element or not
        // this verification should not be done if qn. states that there always exist a majority element
        int count = 0;
        for(int i = 0;i < n;i++){
            if(nums[i] == element){
                count++;
            }
        }
        if(count > minCnt){
            return element;
        }
        return -1;
    }
}
