class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        // TC : O(n^2 + n^2log(n^2))
        // SC : O(n^2)
        List<Integer> list = new ArrayList<>();
        for(int[] arr: matrix){
            for(int num: arr){
                list.add(num);
            }
        }
        Collections.sort(list);
        return list.get(k-1);
    }
}

class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        // TC : O(n^2*log(k))
        // SC : O(k)
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        for(int[] arr: matrix){
            for(int num: arr){
                maxHeap.add(num);
                if(maxHeap.size() > k){
                    // remove the max element
                    maxHeap.poll();
                }
            }
        }
        return maxHeap.poll();
    }
}

class Solution {
    int n;
    private int countSmallerOrEqualElements(int[][] matrix,int val){
        int cnt = 0;
        for(int i = 0;i < n;i++){
            // returns idx of first occurance of val in matrix[i]
            int idx = Arrays.binarySearch(matrix[i],val);
            // find the last occurance if idx is whole no
            if(idx >= 0){
                while(idx < n && matrix[i][idx] == val){
                    idx++;
                }
                cnt += idx;
            }else{
                // if not present
                int insertionPoint = -idx-1;
                cnt += insertionPoint;
            }
        }
        return cnt;
    }
    public int kthSmallest(int[][] matrix, int k) {
        // TC : O(log(r-l) * nlog(n))
	// where r is matrix[n-1][n-1] and l is matrix[0][0]
        // SC : O(1)
        n = matrix.length;
        // do a binary search on finding kth smallest element
        int l = matrix[0][0];
        int r = matrix[n-1][n-1];
        int ans = -1;

        while(l <= r){
            int mid = l + (r-l)/2;
            int cnt = countSmallerOrEqualElements(matrix, mid);
            if(cnt >= k){
                ans = mid; 
                r = mid-1;
            }else{
                l = mid+1;
            }
        }
        return ans;
    }
}

class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        // TC : O(n*log(n))+O(k*log(n))
        // SC : O(n)
        // using minHeap
        int n = matrix.length;
        // make a minHeap: stores {matrix[i][j],i,j}
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a,b)->Integer.compare(a[0],b[0]));
        // store first element of every row in minHeap
        for(int row = 0;row < n;row++){
            minHeap.add(new int[]{matrix[row][0], row, 0});
        }
        // pop k-1 times from minHeap
        for(int times = 0;times < k-1;times++){
            int[] curr = minHeap.poll();
            int r = curr[1];
            int c = curr[2];
            // push next element in same column(if present)
            if(c+1 < n){
                minHeap.add(new int[]{matrix[r][c+1],r,c+1});
            }
        }
        return minHeap.peek()[0];
    }
}
