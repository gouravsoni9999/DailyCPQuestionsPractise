import java.util.*;
import java.util.Queue;
class LeetCodeDFSSolution {
    int m;
    int n;
    int islandCnt;
    int[][] directions = { { 0, 1 }, { 1, 0 }, { -1, 0 }, { 0, -1 } };

    private boolean isValid(int i, int j) {
        if (i < 0 || i >= m || j < 0 || j >= n)
            return false;
        else
            return true;
    }

    // dfs traversal to mark all connected island cells visited
    private void dfs(char[][] grid, int i, int j) {
        if (!isValid(i, j))
            return;
        if (grid[i][j] != '1')
            return;
        grid[i][j] = '$';
        for (int[] dir : directions) {
            int new_i = i + dir[0];
            int new_j = j + dir[1];
            dfs(grid, new_i, new_j);
        }
    }

    public int numIslands(char[][] grid) {
        // using dfs
        m = grid.length;
        n = grid[0].length;
        islandCnt = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    dfs(grid, i, j);
                    islandCnt++;
                }
            }
        }
        return islandCnt;
    }
}

class LeetcodeBFSSolution {
    int m;
    int n;
    int islandCnt;
    int[][] directions = { { 0, 1 }, { 1, 0 }, { -1, 0 }, { 0, -1 } };

    private boolean isValid(int i, int j) {
        if (i < 0 || i >= m || j < 0 || j >= n)
            return false;
        else
            return true;
    }

    // bfs traversal to mark all connected island cells visited
    private void bfs(char[][] grid, int i, int j) {
        Queue<int[]> que = new LinkedList<>();
        que.add(new int[]{i,j});
        grid[i][j] = '$';
        while(!que.isEmpty()){
            int[] item = que.poll();
            for (int[] dir : directions) {
                int new_i = item[0] + dir[0];
                int new_j = item[1] + dir[1];
                if(!isValid(new_i,new_j) || grid[new_i][new_j] != '1'){
                    continue;
                }
                else{
                    que.add(new int[]{new_i,new_j});
                    grid[new_i][new_j] = '$';
                }
            }
        }
    }

    public int numIslands(char[][] grid) {
        // using dfs
        m = grid.length;
        n = grid[0].length;
        islandCnt = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    bfs(grid, i, j);
                    islandCnt++;
                }
            }
        }
        return islandCnt;
    }
}

// geeks for geeks question : horizontally, vertically, diagonally
class GFGBFSSolution {
	
	int m;
	int n;
	boolean[][] vis;
	
	private void bfs(int i, int j, char[][] grid) {
		
		Queue<int[]> que = new LinkedList<>();
		
		que.add(new int[] {i, j});
		vis[i][j] = true; // mark it visited
		
		while (!que.isEmpty()) {
			int[] coord = que.poll();
			for (int drow = -1; drow <= 1; drow++) {
				for (int dcol = -1; dcol <= 1; dcol++) {
					int x = coord[0] + drow;
					int y = coord[1] + dcol;
					// if {x,y} is not out-of-bounds and is not visited and is land
					if (x >= 0 && y >= 0 && x < m && y < n && !vis[x][y] && grid[x][y] == 'L') {
						que.add(new int[] {x, y});
						vis[x][y] = true;
					}
				}
			}
			
		}
	}
	
	public int countIslands(char[][] grid) {
		// using BFS
        // SC : O(m.n)
        // TC : O(m.n)
		m = grid.length;
		n = grid[0].length;
		vis = new boolean[m][n];
		int islands = 0;
		
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (!vis[i][j] && grid[i][j] == 'L') {
					islands++;
					bfs(i, j, grid);
				}
			}
		}
		return islands;
	}
}
