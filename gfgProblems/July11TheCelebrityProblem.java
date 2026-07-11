class Solution {
    public int celebrity(int mat[][]) {
        // TC : O(n^2 + n) ~ O(n^2)
        // SC : O(n)
        int n = mat.length;
        // celebrity -> he is known by everyone(except himself)
        // and he doesnot know anyone else
        
        // I know array contains ith person knows who else
        int[] iKnow = new int[n];
        
        // know me array contains is known by whom?
        int[] knowMe = new int[n];
        
        // iterate over the matrix and find 1
        for(int i = 0;i < n;i++){
            for(int j = 0;j < n;j++){
                if(i!=j && mat[i][j] == 1){
                    // don't think of i,i -> myself 
                    // ith person knows jth person
                    iKnow[i]++;
                    knowMe[j]++;
                }
            }
        }
        
        int celeb = -1; 
        // iterate over all people to find celebrity
        // if iKnow[i] = 0 && knowMe[i] == n-1 -> i is celebrity
        for(int i = 0;i < n;i++){
            if(iKnow[i] == 0 && knowMe[i] == n-1){
                celeb = i;
                break;
            }
        }
        
        return celeb;
    }
}

class Solution {
    public int celebrity(int mat[][]) {
        // TC : O(n) + O(n) ~ O(n)
        // SC : O(1)
        // better approach
        int n = mat.length;
        
        // 2 pointers :-> top(0) and down(n-1)
        int top = 0;
        int down = n-1;
        
        // idea is to eliminate who cannot be a celebrity
        
        while(top < down){
            
            if(mat[top][down] == 1){
                // top knows down
                // top cannot be celebrity
                top++; // check others now
            }else if(mat[down][top] == 1){
                // down knows top
                // down cannot be celebrity
                down--; // check others now
            }else{
                // top doesnot know down and vice versa
                // both cannot be celebrity then
                // as someone should be known by other to be a celebrity
                // therefore move both pointers
                top++;
                down--;
            }
        }
        
        if(top == down){
            // if he is celebrity
            // check first he should not know anyone
            for(int i = 0;i < n;i++){
                if(top != i && (mat[top][i] == 1 || mat[i][top] == 0)){
                    // he knows i or i doesnot know him  : therefore not a celebrity
                    return -1;
                }
            }
            // both checks passed : he is celebrity
            return top;
        }
        
        return -1; // as both top and down are at different persons, so no celebrity
    }
}