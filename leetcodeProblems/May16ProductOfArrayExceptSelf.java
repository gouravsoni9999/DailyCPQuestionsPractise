class Solution {
    public int[] productExceptSelf(int[] nums) {
        // TC : O(n)
        // SC : O(1) no extra space except output array is taken
        int n = nums.length;
        int[] ans = new int[n];
        int prod = 1;
        ans[0] = 1;
        for(int i = 1;i < n;i++){
            prod = prod * nums[i-1];
            ans[i] = prod;
        }
        int right = 1;
        for(int i = n-2;i >= 0;i--){
            right = right * nums[i+1];
            ans[i] = ans[i] * right;
        }
        return ans;
    }
}

class Solution {
    public int[] productExceptSelf(int[] nums) {
        // TC : O(n)
        // SC : O(n)
        int n = nums.length;
        int[] left = new int[n];
        int[] right = new int[n];
        left[0] = 1;
        right[n-1] = 1;
        for(int i = 1;i < n;i++){
            left[i] = left[i-1] * nums[i-1];
        }
        for(int i = n-2;i >= 0;i--){
            right[i] = right[i+1] * nums[i+1];
        }
        for(int i = 0;i < n;i++){
            left[i] = left[i] * right[i];
        }
        return left;
    }
}

class Solution {
    public int[] productExceptSelf(int[] nums) {
        // brute-force approach
        // TC : O(n^2)
        // SC : O(1)
        int n = nums.length;
        int[] res = new int[n];
        for(int i = 0;i < n;i++){
            int ans = 1; // iterate over the whole array and find product
            for(int j = 0;j < n;j++){
                if(j == i) continue;
                ans = ans * nums[j];
            }
            res[i] = ans;
        }
        return res;
    }
}

class Solution {
    public int[] productExceptSelf(int[] nums) {
        // TC : O(n)
        // SC : O(1)
        int n = nums.length;
        int[] res = new int[n];
        int zeroCnt = 0;
        int prod = 1;
        int prod_without_zero = 1;
        for(int num: nums){
            if(num == 0){
                zeroCnt++;
            }else{
                prod_without_zero = prod_without_zero * num;
            }
            prod = prod * num;
        }
        for(int i = 0;i < n;i++){
            if(nums[i] == 0){
                if(zeroCnt > 1){
                    // more than 1 zero present
                    res[i] = 0; // therefore prod = 0
                }
                else{
                    // one zero present
                    res[i] = prod_without_zero;
                }
            }else{
                // non-zero value
                if(zeroCnt > 0){
                    // if array contains atleast one zero
                    res[i] = 0;
                }else{
                    // if array doesn't contain any zero
                    res[i] = prod/nums[i];
                }
            }
        }
        return res;
    }
}
