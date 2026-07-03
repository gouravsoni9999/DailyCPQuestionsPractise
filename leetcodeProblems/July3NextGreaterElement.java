import java.util.*;
class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        /*

            TC : O(n + m)
            SC : O(n) + O(n) ~ O(n)

        */
        Stack<Integer> stack = new Stack<>();// monotonically decreasing stack
        Map<Integer, Integer> map = new HashMap<>(); // stores {nums2[i], NGE}

        int n = nums2.length;
        for(int i = n-1;i >= 0;i--){
            if(stack.isEmpty()){
                // no greater element
                map.put(nums2[i], -1);
            }
            else if (stack.peek() > nums2[i]){
                map.put(nums2[i], stack.peek());
            }

            else{
                while(!stack.isEmpty() && stack.peek() <= nums2[i]){
                    stack.pop();
                }
                if(!stack.isEmpty()){
                    map.put(nums2[i], stack.peek());
                }
            }

            stack.push(nums2[i]);
        }

        int m = nums1.length;
        int[] res = new int[m];
        for(int i = 0;i < m;i++){
            res[i] = map.getOrDefault(nums1[i], -1);
        }

        return res;
    }
}

class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        // brute force approach
        // TC : O(n^2)
        // SC : O(1)
        int m = nums1.length;
        int n = nums2.length;

        Map<Integer, Integer> map = new HashMap<>();

        for(int i = 0;i < n;i++){
            for(int j = i+1;j < n;j++){
                if(nums2[i] < nums2[j]){
                    map.put(nums2[i], nums2[j]);
                    break;
                }
            }
        }

        int[] res = new int[m];

        for(int i = 0;i < m;i++){
            res[i] = map.getOrDefault(nums1[i], -1);
        }

        return res;
    }
}