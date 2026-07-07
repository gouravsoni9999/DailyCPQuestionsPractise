class Solution {
    private long range(int[] nums,int i,int j){
        long maxi = nums[i];
        long mini = nums[i];
        for(int k = i;k <= j;k++){
            maxi = Math.max(maxi, nums[k]);
            mini = Math.min(mini, nums[k]);
        }
        return maxi-mini; 
    }
    public long subArrayRanges(int[] nums) {
        int n = nums.length;
        // brute-force approach
        // TC : O(n*n*n)
        // SC : O(1)
        long ans = 0;
        // generate all subarrays
        for(int i = 0;i < n;i++){
            for(int j = i;j < n;j++){
                ans += range(nums, i, j);
            }
        }
        return ans;
    }
}

class Solution {
    public long subArrayRanges(int[] nums) {
        int n = nums.length;
        // brute-force approach
        // TC : O(n*n)
        // SC : O(1)
        long ans = 0;
        // generate all subarrays
        for(int i = 0;i < n;i++){
            long longest = nums[i];
            long smallest = nums[i];
            for(int j = i+1;j < n;j++){
                longest = Math.max(longest, nums[j]);
                smallest = Math.min(smallest, nums[j]);
                ans += longest - smallest;
            }
        }
        return ans;
    }
}

class Solution {
    int n;

    private int[] findNSE(int[] arr) {
        int[] nse = new int[n];

        Stack<Integer> stack = new Stack<>();
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                stack.pop();
            }
            nse[i] = stack.isEmpty() ? n : stack.peek();
            stack.push(i);
        }

        return nse;
    }

    private int[] findPSEE(int[] arr) {
        int[] psee = new int[n];

        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                stack.pop();
            }
            psee[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }

        return psee;
    }

    public long sumSubarrayMins(int[] arr) {
        n = arr.length;
        int ans = 0;
        int mod = (int) 1e9 + 7;

        int[] nse = findNSE(arr);
        int[] psee = findPSEE(arr); // find previous smaller or equal elements

        long total = 0;

        for (int i = 0; i < n; i++) {
            long left = i - psee[i];
            long right = nse[i] - i;

            total += left*right*arr[i];
        }

        return total;
    }

    public long sumOfMins(int[] arr) {
        n = arr.length;
        int ans = 0;
        int mod = (int) 1e9 + 7;

        int[] nse = findNSE(arr);
        int[] psee = findPSEE(arr); // find previous smaller or equal elements

        long total = 0;

        for (int i = 0; i < n; i++) {
            long left = i - psee[i];
            long right = nse[i] - i;

            total += left*right*arr[i];
        }

        return total;
    }

    private int[] findNGE(int[] arr) {
        int[] nge = new int[n];

        Stack<Integer> stack = new Stack<>();
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && arr[stack.peek()] <= arr[i]) {
                stack.pop();
            }
            nge[i] = stack.isEmpty() ? n : stack.peek();
            stack.push(i);
        }

        return nge;
    }

    private int[] findPGEE(int[] arr) {
        int[] pgee = new int[n];

        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] < arr[i]) {
                stack.pop();
            }
            pgee[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }

        return pgee;
    }

    public long sumOfMaxs(int[] arr) {
        n = arr.length;
        long ans = 0;
        
        int[] nge = findNGE(arr);
        int[] pgee = findPGEE(arr);
        
        long total = 0;
        
        for(int i = 0;i < n;i++){
            long left = i - pgee[i];
            long right = nge[i] - i;
            
            total += left*right*arr[i];
        }
        
        return total;
    }

    public long subArrayRanges(int[] nums) {
        // TC : O(n)
        // SC : O(n)
        // using stack
        // using problems of sum of subarray max. and min.
        // ans = sum(subarray max.) - sum(subarray min.)
        n = nums.length;
        return sumOfMaxs(nums) - sumOfMins(nums);
    }
}