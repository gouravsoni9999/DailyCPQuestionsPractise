class Solution {
    public static int minCost(int[] arr) {
        // code here
        // TC : O(n*log(n))
        // SC : O(n)
        int n = arr.length;
        if(n == 1) 
            // already one rope, no connection cost
            return 0;
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for(int num: arr){
            minHeap.add(num);
        }
        int cost = 0;
        while(minHeap.size() >= 2){
            int firstItem = minHeap.poll();
            int secondItem = minHeap.poll();
            int sum = firstItem + secondItem;
            cost += sum;
            minHeap.add(sum);
        }
        // now only one rope is present
        return cost;
    }
}
