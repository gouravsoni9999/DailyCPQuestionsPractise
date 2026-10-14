package implementationProblems;

import java.util.Arrays;

class DSU {
    int[] rank, parent, size;

    DSU(int n) {
        rank = new int[n + 1];
        parent = new int[n + 1];
        size = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        Arrays.fill(size, 1);
    }

    int findUltimateParent(int node) {
        // TC : O(1)
        // SC : O(1)
        if (node == parent[node]) {
            return node;
        }

        return parent[node] = findUltimateParent(parent[node]);
    }

    void unionByRank(int u, int v) {
        // TC : O(1)
        // SC : O(1)
        int ultimateParent_u = findUltimateParent(u);
        int ultimateParent_v = findUltimateParent(v);

        // if they are not belonging to same component, then only union them
        if (ultimateParent_u != ultimateParent_v) {
            if (rank[ultimateParent_u] < rank[ultimateParent_v]) {
                // union 1st component inside 2nd one
                parent[ultimateParent_u] = ultimateParent_v;
            } else if (rank[ultimateParent_v] < rank[ultimateParent_u]) {
                // again union 2nd component inside 2nd one
                parent[ultimateParent_v] = ultimateParent_u;
            } else {
                // both are having same ranks
                // rank[ultimateParent_u] == rank[ultimateParent_v]
                // union anybody to other, and increase rank of other
                parent[ultimateParent_u] = ultimateParent_v;
                rank[ultimateParent_v]++;
            }
        }
    }

    void unionBySize(int u, int v) {
        // TC : O(1)
        // SC : O(1)
        int ultimateParent_u = findUltimateParent(u);
        int ultimateParent_v = findUltimateParent(v);

        if (ultimateParent_u != ultimateParent_v) {
            // different component: union them
            if (size[ultimateParent_u] < size[ultimateParent_v]) {
                // the tree of u is smaller, therefore union in v
                parent[ultimateParent_u] = ultimateParent_v;
                size[ultimateParent_v] += size[ultimateParent_u];
            } else {
                // the tree of v is smaller or equal to u, therefore union in u
                parent[ultimateParent_v] = ultimateParent_u;
                size[ultimateParent_u] += size[ultimateParent_v];
            }
        }
    }
}

public class Oct14DisjointSetUnion_DS {
    public static void main(String[] args) {
        DSU ds = new DSU(7);
        ds.unionBySize(1, 2);
        ds.unionBySize(2, 3);
        ds.unionBySize(4, 5);
        ds.unionBySize(6, 7);
        ds.unionBySize(5, 6);

        if (ds.findUltimateParent(3) == ds.findUltimateParent(7)) {
            System.out.println("3 and 7 belong to same component");
        } else {
            System.out.println("3 and 7 doesnot belong to same component");
        }

        ds.unionBySize(3, 7);

        if (ds.findUltimateParent(3) == ds.findUltimateParent(7)) {
            System.out.println("3 and 7 belong to same component");
        } else {
            System.out.println("3 and 7 doesnot belong to same component");
        }
    }
}
