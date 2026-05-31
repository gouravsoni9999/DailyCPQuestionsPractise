class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        // brute approach
        // TC : O(n*k)   ~ O(n^2) in Worst Case
        // SC : O(n-k+1) ~ O(1) auxilary space(don't consider output storage space)   
        int n = nums.length;
        int[] res = new int[n - k + 1];
        int l = 0; // iterator for res array
        for (int i = 0; i <= n - k; i++) {
            int getMax = nums[i];
            for (int j = i + 1; j < n && j < i + k; j++) {
                getMax = Math.max(getMax, nums[j]);
            }
            res[l++] = getMax;
        }
        return res;
    }
}

class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        // TC : O(nlogn)
        // SC : O(n)
        int n = nums.length;
        int[] res = new int[n-k+1];
        int l = 0; // res array iterator
        // max heap approach
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a,b)->Integer.compare(b[0],a[0]));
        for(int j = 0;j < n;j++){
            maxHeap.add(new int[]{nums[j],j});
            while(maxHeap.peek()[1] <= j-k){
                // pop it, as it is out of window
                maxHeap.poll();
            }
            if(j >= k-1){
		// one of the end of a window
		// therefore push in the result
                res[l++] = maxHeap.peek()[0];
            }
        }
        return res;
    }
}
