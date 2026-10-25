import java.util.*;
class UnionFind {

    int[] parent, size;

    UnionFind(int n) {
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
        int parent_u = find(u);
        int parent_v = find(v);

        if (parent_u != parent_v) {
            // different components, union them
            if (size[parent_u] <= size[parent_v]) {
                parent[parent_v] = parent_u;
                size[parent_u] += size[parent_v];
            } else {
                parent[parent_u] = parent_v;
                size[parent_v] += size[parent_u];
            }
        }
    }
}

class Solution {
    int n;

    int[][] dir = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };

    private int findNodeNumber(int i, int j) {
        return n * i + j;
    }

    private boolean isValid(int i, int j) {
        return (i >= 0 && j >= 0 && i < n && j < n);
    }

    public int largestIsland(int[][] grid) {
        // TC : O(n^2)
        // SC : O(n^2)
        n = grid.length;

        // using Disjoint Set Data Structure : unionBySize can only be done here
        UnionFind uf = new UnionFind(n * n);

        // make existing islands connected
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    int nodeNum = findNodeNumber(i, j);
                    // make adjacent islands connected
                    for (int[] d : dir) {
                        int adjR = i + d[0];
                        int adjC = j + d[1];

                        if (isValid(adjR, adjC) && grid[adjR][adjC] == 1) {
                            int adjNodeNum = findNodeNumber(adjR, adjC);
                            uf.union(nodeNum, adjNodeNum);
                        }
                    }
                }
            }
        }

        // try to make every 0 -> 1
        int maxSize = 0;
        boolean hasZero = false;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                if (grid[i][j] == 0) {
                    hasZero = true;
                    Set<Integer> parentsSet = new HashSet<>();
                    // find all adjacent 1-based islands
                    for (int[] d : dir) {
                        int adjR = i + d[0];
                        int adjC = j + d[1];
                        if (isValid(adjR, adjC) && grid[adjR][adjC] == 1) {
                            int nodeNum = findNodeNumber(adjR, adjC);
                            parentsSet.add(uf.find(nodeNum));
                        }
                    }
                    // find size of the component after making grid[i][j] = 1(imagine)
                    int size = 1; // for that particular node
                    for (int parentNode : parentsSet) {
                        size += uf.size[parentNode];
                    }
                    maxSize = Math.max(maxSize, size);
                }
            }
        }

        if (!hasZero)
            // no 0s in grid
            // thus, only 1s in grid
            return n * n; // only 1 island which is the whole grid

        return maxSize;
    }
}