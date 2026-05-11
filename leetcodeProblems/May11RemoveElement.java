class Solution {
    public int removeElement(int[] nums, int val) {
        // TC : O(n)
        // SC : O(1)
        int k = 0;// this pointer points to non-val values
        int n = nums.length;
        for(int i = 0;i < n;i++){
            if(nums[i] != val){
                nums[k] = nums[i];
                k++;
            }
        }
        return k;
    }
}
