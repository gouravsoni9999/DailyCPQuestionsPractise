import java.util.*;

class Solution {
    public String[] createGrid(int m, int n) {
        // TC : O(m*n)
        // SC : O(m*n)
        char[][] grid = new char[m][n];
        for(char[] g : grid)
            Arrays.fill(g, '.');

        // only valid path, go straight right and then down until last cell is reached
        for(int i = 1;i <= m-1;i++){
            for(int j = 0;j <= n-2;j++){
                // mark all these coord as '#'
                grid[i][j] = '#';
            }
        }

        String[] res = new String[m];
        for(int row = 0;row < m;row++){
            res[row] = new String(grid[row]);
        }

        return res;
    }
}