class Solution {
    public void rotate(int[] nums, int k) {
	// TC : O(n)
	// SC : O(n)
        int n = nums.length;
        k = k % n;
        int[] res = new int[n];
        for(int i = 0;i < n;i++){
            res[i] = nums[(n+(i-k))%n];
        }
        for(int i = 0;i < n;i++){
            nums[i] = res[i];
        }
    }
}
class Solution {
    int n;

    private void swap(int[] nums, int s, int e) {
        int temp = nums[s];
        nums[s] = nums[e];
        nums[e] = temp;
    }

    private void reverse(int[] nums, int s, int e) {
        if (s >= e)
            return;
        while (s < e) {
            swap(nums, s, e);
            s++;
            e--;
        }
    }

    public void rotate(int[] nums, int k) {
	// TC : O(n-k) + O(k) + O(n) = O(2n)
	// SC : O(1)
        n = nums.length;
        k = k % n;
        reverse(nums, 0, n - k - 1);
        reverse(nums, n - k, n - 1);
        reverse(nums, 0, n - 1);
    }
}

class Solution {
    int n;
    private void swap(int[] nums,int s,int e){
        int temp = nums[s];
        nums[s] = nums[e];
        nums[e] = temp;
    }
    private void reverse(int[] nums,int s,int e){
        if(s >= e) return;
        swap(nums, s, e);
        s++;
        e--;
        reverse(nums, s, e);
    }
    public void rotate(int[] nums, int k) {
        // TC : O(n) + O(n) + O(n) = O(n)
        // SC : O(n/2)+O(n/2) + O(n/2) = O(n) stack space
        n = nums.length;
        k = k % n;
        reverse(nums, 0, (n-1)-k);
        reverse(nums, n-k, n-1);
        reverse(nums, 0, n-1);
    }
}

class Solution {
    public void rotate(int[] nums, int k) {
        // TC : O(n.k) = O(n^2)
        // SC : O(1)
        int n = nums.length;
        k = k % n;
        while(k-- > 0){
            int temp = nums[n-1];
            // remaining elements are right-shifted
            for(int i = n-1; i >= 1;i--){
                nums[i] = nums[i-1];
            }
            nums[0] = temp;
        }
    }
}
