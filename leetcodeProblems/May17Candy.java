class Solution {
    public int candy(int[] ratings) {
        // TC : O(n)
        // SC : O(n)
        int n = ratings.length;
        int[] L2R = new int[n];
        int[] R2L = new int[n];
        Arrays.fill(L2R, 1);
        Arrays.fill(R2L, 1);
        // fill L2R on the basis of left neighbour
        for(int i = 1;i < n;i++){
            // since i = 0 doesn't have any left neighbour start from i=1
            if(ratings[i-1] < ratings[i]){
                L2R[i] = L2R[i-1] + 1;
            }
        }
        // fill R2L on the basis of right neighbour
        for(int i = n-2;i >= 0;i--){
            // as i = n-1 doesn't have any right neighbour start from i = n-2
            if(ratings[i+1] < ratings[i]){
                R2L[i] = R2L[i+1] + 1;
            }
        }
        // from L2R and R2L, that child must get max. of both
        int[] res = new int[n];
        for(int i = 0;i < n;i++){
            res[i] = Math.max(L2R[i], R2L[i]);
        }
        int sum = 0;
        for(int i = 0;i < n;i++){
            sum += res[i];
        }
        return sum;
    }
}

class Solution {
    public int candy(int[] ratings) {
        // TC : O(n)
        // SC : O(n)
        int n = ratings.length;
        int[] L2R = new int[n];
        int[] R2L = new int[n];
        Arrays.fill(L2R, 1);
        Arrays.fill(R2L, 1);
        // fill L2R on the basis of left neighbour
        for(int i = 1;i < n;i++){
            // since i = 0 doesn't have any left neighbour start from i=1
            if(ratings[i-1] < ratings[i]){
                L2R[i] = L2R[i-1] + 1;
            }
        }
        // fill R2L on the basis of right neighbour
        for(int i = n-2;i >= 0;i--){
            // as i = n-1 doesn't have any right neighbour start from i = n-2
            if(ratings[i+1] < ratings[i]){
                R2L[i] = R2L[i+1] + 1;
            }
        }
        // from L2R and R2L, that child must get max. of both
        int sum = 0;
        for(int i = 0;i < n;i++){
            sum += Math.max(L2R[i], R2L[i]);
        }
        return sum;
    }
}

class Solution {
    public int candy(int[] ratings) {
        // TC : O(n)
        // SC : O(n)
        int n = ratings.length;
        int[] count = new int[n];
        Arrays.fill(count, 1);
        // since i = 0 doesnot have any left neighbour
        // check if left neighbour is strictly smaller, if yes, then update count
        for (int i = 1; i < n; i++) {
            if (ratings[i] > ratings[i - 1])
                count[i] = Math.max(count[i], count[i - 1] + 1);
        }
        // since i = n-1 doesnot have any right neighbour
        // check if right neighbour is strictly smaller, if yes, then update count
        for (int i = n - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1])
                count[i] = Math.max(count[i], count[i + 1] + 1);
        }

        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += count[i];
        }
        return sum;
    }
}


class Solution {
    public int candy(int[] ratings) {
        // TC : O(n)
        // SC : O(1)
        int n = ratings.length;
        int candy = n; // each child gets atleast one candy
        int i = 1;
        while (i < n) {
            if (ratings[i] == ratings[i - 1]){
                i++;
                continue;
            }
            // increasing slope: peak
            int peak = 0;
            while (i < n && ratings[i] > ratings[i - 1]) {
                peak++;
                candy += peak;
                i++;
            }
            // decreasing slope: dip
            int dip = 0;
            while (i < n && ratings[i] < ratings[i - 1]) {
                dip++;
                candy += dip;
                i++;
            }
            // Reconcile the highest point shared by peak and dip
            candy -= Math.min(peak, dip);
        }
        return candy;
    }
}
