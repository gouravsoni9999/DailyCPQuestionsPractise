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

/*
    Story Points:
    
    1. when new element comes in the window, make space for it
    as size can't be greater than k
    2. now, no need to have all elements in deque smaller than nums[i], as nobody can become max. element
    3. now push i in deque(only idx)
    4. if (i >= k-1) then deque.front() is the answer of the window
*/
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        // using deque
        // TC : O(n) as every element is processed once only(one time only pushed and one time only popped)
        // SC : O(k)

        int n = nums.length;
        // create a strictly decreasing deque (monotonic deque)
        Deque<Integer> deq = new ArrayDeque<>();// stores index
        int[] res = new int[n-k+1];
        int l = 0; // res iterator
        for(int i = 0;i < n;i++){

            // check window size is not more than k
            while(!deq.isEmpty() && deq.getFirst() <= i-k){
                // front value is not part of window now
                deq.pollFirst();
            }

            // check if at the back part of deque, only elements greater than nums[i] is present, if smaller are present delete them 
            while(!deq.isEmpty() && nums[i] > nums[deq.peekLast()]){
                deq.pollLast(); // poll the last value
            }

            // add idx in deque
            deq.addLast(i);

            if(i >= k-1){
                // this is a complete window, push back in result
                res[l++] = nums[deq.getFirst()];
            }
        }
        return res;
    }
}
