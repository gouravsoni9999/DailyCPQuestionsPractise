class Solution {
    public int maxArea(int[] height) {
        // TC : O(n)
        // SC : O(1)
        int n = height.length;
        // 2 pointer approach
        int left = 0;
        int right = n-1;
        int maxiArea = 0;
        while(left < right){
            int heightOfContainer = Math.min(height[left],height[right]);
            int widthOfContainer = right-left;
            int area = heightOfContainer * widthOfContainer;
            maxiArea = Math.max(maxiArea, area);
            if(height[left] <= height[right]){
                left++;//as we need max. area
            }else{
                right--;
            }
        }
        return maxiArea;
    }
}

class Solution {
    public int maxArea(int[] height) {
        // TC : O(n^2)
        // SC : O(1)
        // brute approach
        int n = height.length;
        int area = 0;
        for(int i = 0;i < n;i++){
            int x = height[i];
            for(int j = i+1;j < n;j++){
                int y = height[j];
                area = Math.max(area, Math.min(x,y)*(j-i));
            }
        }
        return area;
    }
}
