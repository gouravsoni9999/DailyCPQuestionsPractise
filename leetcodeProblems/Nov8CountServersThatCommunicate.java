class DisjointSet {
    public int[] parent, size;

    DisjointSet(int n) {
        parent = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    public int find(int node) {
        // find ultimate parent of node
        if (node == parent[node]) {
            // he is the ultimate parent
            return node;
        }
        return parent[node] = find(parent[node]);
    }

    public void union(int u, int v) {
        int p_u = find(u);
        int p_v = find(v);
        if (p_u != p_v) {
            // different component, union them
            if (size[p_u] <= size[p_v]) {
                parent[p_u] = p_v;
                size[p_v] += size[p_u];
            } else {
                parent[p_v] = p_u;
                size[p_u] += size[p_v];
            }
        }
    }
}

class Solution {
    public int countServers(int[][] grid) {
        // using Union-Find Data Structure(Union by Size)
        // TC & SC : O(rows + cols)

        int rows = grid.length;
        int cols = grid[0].length;

        DisjointSet ds = new DisjointSet(rows + cols);
        int totalServers = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // find out which cells have server
                if (grid[i][j] == 1) {
                    totalServers++;
                    // a server is present here
                    ds.union(i, rows + j); // union both row number and column number
                }
            }
        }

        int cnt = 0; // count of servers which are alone.

        for (int node = 0; node < rows + cols; node++) {
            // find out is it ultimate parent or not
            if (ds.find(node) == node) {
                if (ds.size[node] == 2) {
                    // single server has 1 row and 1 col, so size = 2
                    cnt++;
                }
            }
        }

        return totalServers - cnt;
    }
}