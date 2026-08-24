class Solution {
    int m;
    int n;
    int[][] visited;
    int[][] directions = {{1,0},{0,1},{-1,0},{0,-1}};
    private void dfs(char[][] board,int i,int j){
        if(i<0||i>=m||j<0||j>=n)return;
        if(board[i][j] == 'X')return;
        if(visited[i][j] == 1)return;//already visited
        visited[i][j] = 1;//mark it visited
        for(int[] dir: directions){
            int new_i = i + dir[0];
            int new_j = j + dir[1];
            dfs(board,new_i,new_j);
        }
    }
    public void solve(char[][] board) {
        // TC : O(m.n) and SC
        m = board.length;
        n = board[0].length;
        visited = new int[m][n];//all are by-default 0s
        // check boundary ones and mark those Os as can't be made X
        for(int i = 0;i < m;i++){
            if(board[i][n-1] == 'O') 
                dfs(board, i, n-1);
            if(board[i][0] == 'O')
                dfs(board, i, 0);
        }
        for(int j = 0;j < n;j++){
            if (board[m-1][j] == 'O')
                dfs(board, m-1, j);
            if (board[0][j] == 'O')
                dfs(board,0,j);
        }
        for(int i = 0;i < m;i++){
            for(int j = 0;j < n;j++){
                if(board[i][j] == 'O' && visited[i][j] == 0){
                    board[i][j] = 'X';
                }
            }
        }
    }
}
class Solution {
    int m;
    int n;
    int[][] visited;
    int[][] directions = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };

    private boolean isValid(int i, int j) {
        if (i < 0 || j < 0 || i >= m || j >= n)
            return false;
        return true;
    }

    private void bfs(char[][] board, int i, int j) {
        if (!isValid(i, j))
            return;
        if (board[i][j] == 'X')
            return;
        if (visited[i][j] == 1)
            return;//already visited
        Queue<int[]> que = new LinkedList<>();
        que.add(new int[] { i, j });
        visited[i][j] = 1;//mark it visited
        while (!que.isEmpty()) {
            int[] item = que.poll();
            for (int[] dir : directions) {
                int new_i = item[0] + dir[0];
                int new_j = item[1] + dir[1];
                if (isValid(new_i, new_j) && board[new_i][new_j] == 'O' && visited[new_i][new_j] != 1) {
                    que.add(new int[] { new_i, new_j });
                    visited[new_i][new_j] = 1;//mark it visited
                }
            }
        }
    }

    public void solve(char[][] board) {
        m = board.length;
        n = board[0].length;
        visited = new int[m][n];//all are by-default 0s
        // check boundary ones and mark those Os as can't be made X
        //process left and right boundaries
        for (int i = 0; i < m; i++) {
            if (visited[i][n-1] == 0 && board[i][n - 1] == 'O')
                bfs(board, i, n - 1);
            if (visited[i][0] == 0 && board[i][0] == 'O')
                bfs(board, i, 0);
        }
        //process up and down boundaries
        for (int j = 0; j < n; j++) {
            if (visited[m-1][j] == 0 && board[m - 1][j] == 'O')
                bfs(board, m - 1, j);
            if (visited[0][j] == 0 && board[0][j] == 'O')
                bfs(board, 0, j);
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O' && visited[i][j] == 0) {
                    board[i][j] = 'X';
                }
            }
        }
    }
}
