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
        // 2-pointer approach
        int n = height.length;
        int l = 0, r = n-1;
        int ans = 0;
        int lmax = 0, rmax = 0; // lmax and rmax found so far
        while(l < r){
            lmax = Math.max(lmax, height[l]); // this is boundary on left
            rmax = Math.max(rmax, height[r]); // this is boundary on right
            if(lmax <= rmax){
                // lmax is the deciding boundary that decides how much water will be trapped
                ans += lmax - height[l]; // for that lth idx height of water trapped is boundary - its height
                l++;
            }else{
                // rmax is the deciding boundary that decides how much  water will be trapped
                ans += rmax - height[r];
                r--;
            }
        }
        return ans;
    }
}
