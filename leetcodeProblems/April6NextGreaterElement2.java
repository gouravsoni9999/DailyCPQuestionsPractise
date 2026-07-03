class Solution {
    public int[] nextGreaterElements(int[] nums) {

        // brute force approach
        // TC : O(n^2)
        // SC : O(n)

        int n = nums.length;

        int[] nge = new int[n];
        Arrays.fill(nge, -1);

        for (int i = 0; i < n; i++) {
            for(int j = 1;j < n;j++){
                int idx = (i + j) % n;
                if(nums[idx] > nums[i]){
                    nge[i] = nums[idx];
                    break;
                }
            }
        }

        return nge;
    }
}

class Solution {
    public int[] nextGreaterElements(int[] nums) {
        /*
            TC : O(n)
            SC : O(n)
        */
        // using monotonically decreasing stack
        Stack<Integer> stack = new Stack<>();

        int n = nums.length;
        int[] nge = new int[n];

        // first fill stack with all nums[i]
        for(int i = n-1;i >= 0;i--){
            while(!stack.isEmpty() && stack.peek() <= nums[i]){
                stack.pop();
            }
            stack.push(nums[i]);
        }   
        // then do regular NGE problem
        for(int i = n-1;i >= 0;i--){
            while(!stack.isEmpty() && stack.peek() <= nums[i]){
                stack.pop();
            }
            nge[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(nums[i]);
        }

        return nge;
    }
}
