import java.util.*;

class DisjointSet {
    int[] size, parent;

    DisjointSet(int n) {
        size = new int[n];
        Arrays.fill(size, 1);

        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    int find(int node) {
        if (parent[node] == node)
            return node;

        return parent[node] = find(parent[node]);
    }

    void union(int u, int v) {
        int p_u = find(u);
        int p_v = find(v);

        if (p_u != p_v) {
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
    private static int[][] dir = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

    private static int node(int i, int j, int n, int m) {
        return i * m + j;
    }

    public static int[] numOfIslandsII(int n, int m, int[][] A) {
        // using Disjoint Set Data Structure
        // TC : O(n*m + q)
        // SC : O(n*m + q)
        DisjointSet ds = new DisjointSet(n * m);
        boolean[][] visited = new boolean[n][m];
        int cnt = 0; // count no. of islands

        int[] list = new int[A.length];
        int k = 0;

        for (int[] query : A) {
            int i = query[0];
            int j = query[1];

            if (visited[i][j]) {
                continue; // already marked this island as visited
            }

            // if not visited
            cnt++;
            visited[i][j] = true;

            // see all neighbours
            for (int[] d : dir) {
                int x = i + d[0];
                int y = j + d[1];
                if (x >= 0 && y >= 0 && x < n && y < m && visited[x][y]
                        && ds.find(node(x, y, n, m)) != ds.find(node(i, j, n, m))) {
                    // same island
                    cnt--;
                    ds.union(node(i, j, n, m), node(x, y, n, m));// union only if not in same island (disjointset)
                }
            }

            list[k++] = cnt;
        }
        return list;
    }
}
