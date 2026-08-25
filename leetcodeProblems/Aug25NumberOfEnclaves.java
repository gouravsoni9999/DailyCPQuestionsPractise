import java.util.Queue;
import java.util.LinkedList;
class Solution {
    public int numEnclaves(int[][] grid) {
        // TC : O(m.n)
        // SC : O(m.n)
        int m = grid.length;
        int n = grid[0].length;

        // ultimately, find all 1s which are not attached to anyboundary 1s(the traversal of the connected lands should not direct to that 1)

        boolean[][] vis = new boolean[m][n];

        // I will use BFS(DFS is also possible)
        Queue<int[]> que = new LinkedList<>();

        // traverse all boundaries and enqueue all elements having 1 in que
        for(int i = 0; i < m;i++){
            for(int j = 0;j < n;j++){
                // first row, first col, last row or last col
                if(i == 0 || j == 0 || i == m-1 || j == n-1){
                    if(grid[i][j] == 1){
                        que.add(new int[]{i,j});
                        vis[i][j] = true;
                    }
                }
            }
        }

        int[][] dir = {{1,0},{0,1},{-1,0},{0,-1}};

        // BFS starts
        while(!que.isEmpty()){
            int i = que.peek()[0];
            int j = que.peek()[1];

            que.poll();

            for(int[] d : dir){
                int x = i + d[0];
                int y = j + d[1];

                if(x >= 0 && y >= 0 && x < m && y < n && !vis[x][y] && grid[x][y] == 1){
                    que.add(new int[] {x,y});
                    vis[x][y] = true;
                }
            }
        }

        // all non-visited, non-boundary 1s cannot reach boundary
        int cnt = 0;
        for(int i = 1;i <= m-2;i++){
            for(int j = 1;j <= n-2;j++){
                if(!vis[i][j] && grid[i][j] == 1){
                    cnt++;
                }
            }
        }

        return cnt;
    }
}