import java.util.*;

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
        if (parent[node] == node) {
            return node;
        }

        return parent[node] = find(parent[node]);
    }

    void union(int u, int v) {
        int rootU = find(u);
        int rootV = find(v);

        if (rootU != rootV) {
            // different components, union them
            if (rank[rootU] < rank[rootV]) {
                parent[rootU] = rootV;
            } else if (rank[rootU] > rank[rootV]) {
                parent[rootV] = rootU;
            } else {
                // same
                parent[rootU] = rootV;
                rank[rootV]++;
            }
        }
    }
}

class Solution {
    public int removeStones(int[][] stones) {
        // TC : O(n) + O(maxRow + maxCol)
        // SC : O(maxRow + maxCol) + O(n)(for hashset(max storage of 2n nodes))
        int n = stones.length;// n stones

        // determine the maxRow and maxCol where the farthest stone is put
        int maxRow = 0;
        int maxCol = 0;

        for (int[] coord : stones) {
            maxRow = Math.max(maxRow, coord[0]);
            maxCol = Math.max(maxCol, coord[1]);
        }

        DisjointSet ds = new DisjointSet(maxRow + maxCol + 2);

        Set<Integer> stoneNodesSet = new HashSet<>(); // stores which rows and cols actually have stone
        for (int[] coord : stones) {
            int stoneRow = coord[0];
            int stoneCol = coord[1] + maxRow + 1; // as we have made disjoint set acc. to the fact rows come before col

            ds.union(stoneRow, stoneCol);
            stoneNodesSet.add(stoneRow);
            stoneNodesSet.add(stoneCol);
        }

        int cnt = 0;
        // count unique components(through ultimate parents) which actually have stones
        for (int node : stoneNodesSet) {
            if (ds.find(node) == node) {
                // it is an ultimate parent
                cnt++;
            }
        }

        // from every component having stone, we can remove every stone except one
        // which means total stones removed : (c1 - 1) + (c2 - 1) + (c3-1)...
        // where ci is the no. of stones in ith component
        // simplify formula: (c1 + c2 + c3....) - 1*no. of components
        // => n - 1 * cnt
        return n - cnt;
    }
}