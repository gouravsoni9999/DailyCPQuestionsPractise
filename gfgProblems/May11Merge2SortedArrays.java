class Solution {
    public void mergeArrays(int a[], int b[]) {
        // code here
        // TC : O(m+n)
        // SC : O(m+n)
        int m = a.length;
        int n = b.length;
        int[] temp = new int[m+n];
        int i = 0;
        int j = 0;
        int k = 0;
        while(i < m && j < n){
            if(a[i] <= b[j]){
                temp[k++] = a[i++];
            }else{
                temp[k++] = b[j++];
            }
        }
        while(i < m){
            temp[k++] = a[i++];
        }
        while(j < n){
            temp[k++] = b[j++];
        }
        
        for(k = 0;k < m+n;k++){
            if(k < m) a[k] = temp[k];
            else b[k-m] = temp[k];
        }
    }
}

class Solution {
    private void swap(int[] a,int i,int[] b,int j){
        int temp = a[i];
        a[i] = b[j];
        b[j] = temp;
    }
    public void mergeArrays(int a[], int b[]) {
        // TC : O(min(m,n))+O(mlogm)+O(nlogn)
        // SC : O(1)
        // code here
        int m = a.length;
        int n = b.length;
        
        int i = m-1;
        int j = 0;
        
        while(i >= 0 && j < n){
            if(a[i] > b[j])
                swap(a,i,b,j);
            else
                break;//now everything on there left and right are on their correct place
            i--;
            j++;
        }
        Arrays.sort(a);
        Arrays.sort(b);
    }
}

class Solution {
    private void swapIfGreater(int[] a,int[] b,int idx1,int idx2){
        if(a[idx1] > b[idx2]){
            // if greater, then only swap
            int temp = a[idx1];
            a[idx1] = b[idx2];
            b[idx2] = temp;
        }
    }
    public void mergeArrays(int a[], int b[]) {
        // code here
        // using gap method: part of shell sort
        
        // TC : O(log(m+n) * (m+n))
        // baar baar 2 se divide karne pe log(m+n) and (m+n) for inner while loop
        // SC : O(1)
        int m = a.length;
        int n = b.length;
        
        int len = m+n;
        
        int gap = (int)Math.ceil(len/2.0);
        while(gap > 0){
            int left = 0;
            int right = left + gap;
            while(right < len){
                if(left < m && right >= m){
                    // left is in a and right is in b
                    swapIfGreater(a,b,left,right-m);
                    
                }else if(left >= m){
                    // left and right both are in b
                    swapIfGreater(b,b,left-m,right-m);
                    
                }else{
                    // left and right both are in a
                    swapIfGreater(a,a,left,right);
                }
                left++;
                right++;
            }
            if(gap == 1) break;
            gap = (int)Math.ceil(gap/2.0);
        }
    }
}
