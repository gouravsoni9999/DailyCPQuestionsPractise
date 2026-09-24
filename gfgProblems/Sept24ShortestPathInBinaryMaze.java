import java.util.*;
import java.util.Queue;
class Solution {
    int INF = (int) 1e9;
    int[][] directions = {{0,1},{1,0},{0,-1},{-1,0}};
    public int shortestPath(int[][] mat, int[] src, int[] dest) {
        // TC : O(m.n)
        // SC : O(m.n)
        int m = mat.length;
        int n = mat[0].length;
        
        int[][] dist = new int[m][n];
        
        // all are at infinite distance from src
        for(int i = 0;i < m;i++){
            Arrays.fill(dist[i], INF);
        }
        
        dist[src[0]][src[1]] = 0; // distance from src to src is 0
        
        // make a Queue DS for Dijkstra's Algo
        Queue<int[]> que = new LinkedList<>(); // stores {dist, {row,col}}
        
        // add src to que, iff src is 1
        if(mat[src[0]][src[1]] == 1)
            que.add(new int[]{0, src[0], src[1]});
        
        while(!que.isEmpty()){
            int[] curr = que.poll();
            
            int wt = curr[0];
            int row = curr[1];
            int col = curr[2];
            
            if(row == dest[0] && col == dest[1]){
                //reached destination
                return wt;
            }
            
            // traverse to adjacent coord
            for(int[] dir : directions){
                int x = row + dir[0];
                int y = col + dir[1];
                
                if(x >= 0 && y >= 0 && x < m && y < n && mat[x][y] == 1){
                    // if new coord is valid
                    
                    if(x == dest[0] && y == dest[1]){
                        // reached destination!
                        return wt+1;
                    }
                    
                    if(wt + 1 < dist[x][y]){
                        dist[x][y] = wt+1;
                        que.add(new int[]{dist[x][y], x, y});
                    }
                }
            }
        }
        
        return -1; // cannot reach destination
        
    }
}