class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        // TC : O(m+n)
        // SC : O(m+n)
        // store sorted array in temp array
        int[] temp = new int[m + n];
        int i = 0;//iterator for nums1
        int j = 0;//iterator for nums2
        int k = 0;//iterator for temp 
        while (i < m && j < n) {
            if (nums1[i] <= nums2[j]) {
                temp[k++] = nums1[i++];
            } else {
                temp[k++] = nums2[j++];
            }
        }
        // copy remaining elements
        while (i < m) {
            temp[k++] = nums1[i++];
        }
        while (j < n) {
            temp[k++] = nums2[j++];
        }
        // copy back to nums1
        for (k = 0; k < m + n; k++) {
            nums1[k] = temp[k];
        }
    }
}

class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        // swap and sort
        // TC : O(n) + O((m+n)log(m+n))
        // SC : O(1)
        for(int i = 0;i < n;i++){
            nums1[m+i] = nums2[i]; 
        }
        Arrays.sort(nums1);
    }
}

class Solution {
    private void swap(int[] a,int i,int[] b,int j){
        int temp = a[i];
        a[i] = b[j];
        b[j] = temp;
    }
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        // swap and sort
        // TC : O(min(m,n))+O(mlogm)+O(nlogn)+O(n)
        // SC : O(1)
        int i = m-1;
        int j = 0;
        while(i >= 0 && j < n){
            if(nums1[i] > nums2[j]){
                swap(nums1, i, nums2, j);// they were in wrong arrays
            }else{
                break;// other elements are in correct arrays
            }
            i--;
            j++;
        }
        Arrays.sort(nums1, 0, m);//sort only first m elements
        Arrays.sort(nums2);
        for(i = 0;i < n;i++){
            nums1[m+i] = nums2[i];
        }
    }
}

class Solution {
    private void swapIfGreater(int[] arr,int left, int right){
        if(arr[left] > arr[right]){
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
        }
    }
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        // TC : O(n) + O((m+n) * log(m+n))
        // SC : O(1)
        for(int i = 0;i < n;i++){
            nums1[m+i] = nums2[i];// copy in nums1
        }
        int len = m+n;
        int gap = (int)Math.ceil(len/2.0);
        while(gap > 0){
            int left = 0;
            int right = left+gap;
            while(right < len){
                swapIfGreater(nums1, left, right);
                left++;
                right++;
            }
            if(gap == 1) break;
            gap = (int)Math.ceil(gap/2.0);
        }
    }
}
