class Solution {
    public int sumSubarrayMins(int[] arr) {
        int mod = 1_000_000_007;
        // brute-force approach
        // TC : O(n^2)
        // SC : O(1)
        int ans = 0;
        int n = arr.length;
        for(int i = 0;i < n;i++){
            ans = (ans % mod + arr[i] % mod) % mod;
            int mini = arr[i];
            for(int j = i+1;j < n;j++){
                mini = Math.min(mini, arr[j]);
                ans = (ans % mod + mini % mod) % mod;
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

    public int sumSubarrayMins(int[] arr) {
        // TC : O(n)
        // SC : O(n)
        n = arr.length;
        int ans = 0;
        int mod = (int) 1e9 + 7;

        int[] nse = findNSE(arr);
        int[] psee = findPSEE(arr); // find previous smaller or equal elements

        long total = 0;

        for (int i = 0; i < n; i++) {
            long left = i - psee[i];
            long right = nse[i] - i;

            total = ( total + ( ( (left * right) % mod ) * arr[i] ) % mod ) % mod;
        }

        return (int)total;
    }
}