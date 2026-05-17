class Solution {
    int n;
    
    private int[] findLeftMaxHeightAtEachIdx(int[] height){
        int[] prefixMax = new int[n];
        prefixMax[0] = height[0];
        for(int i = 1;i < n;i++){
            prefixMax[i] = Math.max(prefixMax[i-1], height[i]);
        }
        return prefixMax;
    }
    private int[] findRightMaxHeightAtEachIdx(int[] height){
        int[] suffixMax = new int[n];
        suffixMax[n-1] = height[n-1];
        for(int i = n-2;i >= 0;i--){
            suffixMax[i] = Math.max(suffixMax[i+1], height[i]);
        }
        return suffixMax;
    }
    public int trap(int[] height) {
        // TC : O(n)
        // SC : O(n)
        n = height.length;
        int[] prefixMax = findLeftMaxHeightAtEachIdx(height);
        int[] suffixMax = findRightMaxHeightAtEachIdx(height);
        int waterLogged = 0;
        for(int i = 0;i < n;i++){
            int leftMax = prefixMax[i];
            int rightMax = suffixMax[i];
            // water will be trapped if height[i] < leftMax and height[i] < rightMax
            if(height[i] < leftMax && height[i] < rightMax){
                waterLogged += Math.min(leftMax, rightMax) - height[i];
            }
        }
        return waterLogged;
    }
}

class Solution {
    public int trap(int[] height) {
        // TC : O(n)
        // SC : O(1)
        int n = height.length;
        int leftMax = 0, rightMax = 0, waterLogged = 0;
        int l = 0, r = n-1;
        while(l < r){
            if(height[l] <= height[r]){
                if(leftMax > height[l]){
                    waterLogged += leftMax - height[l];
                }else{
                    leftMax = height[l];
                }
                l++;
            }else{
                if(rightMax > height[r]){
                    waterLogged += rightMax - height[r];
                }else{
                    rightMax = height[r];
                }
                r--;
            }
        }
        return waterLogged;
    }
}
