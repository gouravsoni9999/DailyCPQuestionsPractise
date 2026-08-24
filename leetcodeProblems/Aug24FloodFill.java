class Solution {
    int[][] dir = {{0,-1},{1,0},{0,1},{-1,0}};
    int initialColor;
    int[][] matrix;

    private void dfs(int[][] image,int sr, int sc, int newColor){
        // first, make {sr, sc} into newColor
        matrix[sr][sc] = newColor;

        int m = image.length;
        int n = image[0].length;

        for(int[] d : dir){
            int x = sr + d[0];
            int y = sc + d[1];

            // if {x,y} are not out-of-bounds and image[x][y] = initialColor and matrix[x][y] is not newColor(not already visited)
            if(x >= 0 && y >= 0 && x < m && y < n && image[x][y] == initialColor && matrix[x][y] != newColor){
                // call dfs
                dfs(image, x, y, newColor);
            }
        }
    }

    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        // TC : O(m.n)
        // SC : O(m.n) + O(m.n)(worst case stack space)
        // find initial color
        initialColor = image[sr][sc];

        // to avoid tempering, make new matrix
        matrix = image;

        dfs(image, sr, sc, color);

        return matrix; 

    }
}