class Solution {
    public int removeDuplicates(int[] nums) {
        // TC : O(n)
        // SC : O(1)
        int n = nums.length;
        int prev = nums[0];
        int k = 1;
        for(int i = 1;i < n;i++){
            if(prev != nums[i]){
                nums[k++] = nums[i];
                prev = nums[i];
            }
        }
        return k;
    }
}

class Solution {
    public int removeDuplicates(int[] nums) {
        // TC : O(n+n+nlogn)
        // SC : O(n)
        Set<Integer> set = new HashSet<>();
        for(int num: nums){
            set.add(num);
        }
        int i = 0;
        for(int num: set){
            nums[i++] = num;
        }
        Arrays.sort(nums, 0, set.size());
        return set.size();
    }
}
