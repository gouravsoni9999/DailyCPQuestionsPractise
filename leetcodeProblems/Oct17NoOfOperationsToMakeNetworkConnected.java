class DisjointSet {
    int[] parent, rank;

    DisjointSet(int n) {
        parent = new int[n];
        rank = new int[n];

        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    int find(int node) {
        // find ultimate parent
        if (node == parent[node])
            return node;

        return parent[node] = find(parent[node]);
    }

    void union(int u, int v) {
        int parent_u = parent[u];
        int parent_v = parent[v];
        if (parent_u != parent_v) {
            // diff. component: union them
            if (rank[parent_u] < rank[parent_v]) {
                parent[parent_u] = parent_v;
            } else if (rank[parent_v] < rank[parent_u]) {
                parent[parent_v] = parent_u;
            } else {
                parent[parent_u] = parent_v;
                rank[parent_v]++;
            }
        }
    }
}

class Solution {
    public int makeConnected(int n, int[][] connections) {
        // TC : O(n + e)
        // SC : O(n)
        // find out no. of connected components and extra edges using dijoint set DS
        int nC = 0; // no. of components
        int extraEdges = 0; // no. of extra edges 

        DisjointSet ds = new DisjointSet(n);
        for (int[] e : connections) {
            int u = e[0];
            int v = e[1];
            int parent_u = ds.find(u);
            int parent_v = ds.find(v);
            if (parent_u == parent_v) {
                // same component, this is an extra edge
                extraEdges++;
            } else {
                // diff. components, union them
                ds.union(u, v);
            }
        }

        // find no. of components
        for (int i = 0; i < n; i++) {
            if (ds.find(i) == i) {
                // this is ultimate parent, representing a component
                nC++;
            }
        }

        if (extraEdges >= nC - 1) {
            // if there are enough extra edges to connect all connected components into 1 component
            return nC - 1; // return min. no. of connections required
        } else {
            return -1; // not possible(not enough edges)
        }
    }
}