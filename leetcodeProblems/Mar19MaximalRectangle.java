import java.util.*;
class Solution {
    int m;
    int n;

    private int[] findNSE(int[] arr) {
        int[] res = new int[n];
        Stack<Integer> stack = new Stack<>();

        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                stack.pop(); // greater or equal element in stack not allowed
            }
            // if not found, ans is n
            res[i] = (stack.isEmpty()) ? n : stack.peek();

            stack.push(i);
        }

        return res;
    }

    private int[] findPSE(int[] arr) {
        int[] res = new int[n];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                stack.pop(); // no greater or equal elements allowed
            }

            // if not found, ans is -1
            res[i] = (stack.isEmpty()) ? -1 : stack.peek();

            stack.push(i);
        }

        return res;
    }

    private int maxAreaInHistogram(int[] heights) {
        int[] nse = findNSE(heights);
        int[] pse = findPSE(heights);
        int maxArea = 0;

        for (int i = 0; i < n; i++) {
            int area = (nse[i] - pse[i] - 1) * heights[i];
            maxArea = Math.max(maxArea, area);
        }

        return maxArea;
    }

    public int maximalRectangle(char[][] matrix) {
        // TC : O(m*(n+n)) ~ O(m*n)
        // SC : O(n)
        // using histogram solution
        m = matrix.length;
        n = matrix[0].length;
        int[] heights = new int[n];
        int maxArea = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // as histogram's base is not there, then whole height becomes 0
                heights[j] = (matrix[i][j] == '0') ? 0 : heights[j] + 1;
            }

            // made the histogram
            int area = maxAreaInHistogram(heights);
            maxArea = Math.max(area, maxArea);
        }

        return maxArea;
    }
}

class Solution {
    int m;
    int n;

    private int findMaxAreaInHistogram(int[] height) {
        // finding max area of rectangle in histogram using stack
        int maxArea = 0;
        Stack<Integer> stack = new Stack<>();// stores indices
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && height[stack.peek()] > height[i]) {
                int currIdx = stack.pop();
                int pse = stack.isEmpty() ? -1 : stack.peek();
                int nse = i;
                maxArea = Math.max(maxArea, height[currIdx] * (nse - pse - 1));// height*width
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            int currIdx = stack.pop();
            int nse = n;
            int pse = stack.isEmpty() ? -1 : stack.peek();
            maxArea = Math.max(maxArea, height[currIdx] * (nse - pse - 1));// height*width
        }
        return maxArea;
    }

    public int maximalRectangle(char[][] matrix) {
        m = matrix.length;
        n = matrix[0].length;
        int[] height = new int[n];
        int maxArea = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '0') {
                    height[j] = 0;// neeche waale ki height 0 hogi to upar tak ke 0 hi hogi
                } else {
                    height[j] += (matrix[i][j] == '1') ? 1 : 0;
                }
            }
            maxArea = Math.max(maxArea, findMaxAreaInHistogram(height));
        }
        return maxArea;
    }
}
