import java.util.*;
import java.util.Queue;
class DSU{
    int[] parent, rank;
    int n;

    // constructor initailizing parent and rank
    DSU(int n){
        this.n = n;
        parent = new int[n];
        rank = new int[n]; // all are 0s(nothing attached to them)

        // initially everybody is in their own set
        for(int i = 0;i < n;i++){
            parent[i] = i;
        }
    }

    // find representative of i
    int findParent(int i){
        int root = parent[i];

        if(parent[root] == root){
            return root; // he is the parent(representative)
        }

        // path compression
        return parent[i] = findParent(root);
    }

    // union 2 disjoint sets x and y
    void union(int x,int y){

        // find representatives of the 2 sets
        int xRoot = findParent(x); 
        int yRoot = findParent(y);

        if(xRoot == yRoot){
            return; // x and y are already in same set
        }

        // union by rank : smaller rank set is inserted in bigger rank set
        if(rank[xRoot] < rank[yRoot]){
            parent[xRoot] = yRoot;
        }else if(rank[xRoot] > rank[yRoot]) {
            parent[yRoot] = xRoot;
        }else{
            // rank[xRoot] == rank[yRoot]
            // put any set in anyone and update rank
            parent[xRoot] = yRoot;
            rank[yRoot]++;
        }
    }
}

class DSUSolution {
    int findCircleNum(int[][] isConnected) {
        /*
            TC : O(n^2 . 1)(for loop with union()-> takes alpha(n) ~ 1) + O(n)(set adding)
            SC : O(n)(parent, rank, set) + O(logn)(recursion stack space for findParent)
        */
        // using Disjoint-Set Union Data Structure to solve this problem
        // and using 2 optimizations -> union by rank and path compression

        int n = isConnected.length;
        DSU dsu = new DSU(n);

        // union all related elements in disjoint sets
        for(int i = 0;i < n;i++){
            for(int j = 0;j < n;j++){
                if(i == j || isConnected[i][j] == 0){
                    continue; // no need
                }

                // as both are connected, union them
                dsu.union(i, j);
            }
        }

        Set<Integer> provincesSet = new HashSet<>();
        for(int i = 0;i < n;i++){
            // find ultimate representative of every set and add in set 
            provincesSet.add(dsu.findParent(i));
        }

        return provincesSet.size();
    }
}

class BFSSolution {
    int n;

    private void BFS(int u, int[][] adjMatrix, boolean[] visited) {
        Queue<Integer> levelQ = new LinkedList<>();
        levelQ.add(u);
        visited[u] = true;
        while (!levelQ.isEmpty()) {
            int node = levelQ.poll();
            //neighbours of node
            for (int v = 0; v < n; v++) {
                if (adjMatrix[u][v] == 1 && !visited[v]) {
                    BFS(v,adjMatrix,visited);
                }
            }
        }
    }

    public int findCircleNum(int[][] isConnected) {
        n = isConnected.length; // n no. of cities
        int count = 0; // no. of provinces count
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                BFS(i, isConnected, visited);
                count++;
            }
        }
        return count;
    }
}

class DFSSolution {
    int n;
    private void DFS(int u,int[][] adjMatrix,boolean[] visited){
        visited[u] = true;
        for(int v = 0;v < n;v++){
            if (!visited[v] && adjMatrix[u][v] == 1){
                DFS(v,adjMatrix,visited);
            }
        }        
    }
    public int findCircleNum(int[][] isConnected) {
        n = isConnected.length; // n no. of cities
        int count = 0; // no. of provinces count
        boolean[] visited = new boolean[n];
        for(int i = 0;i < n;i++){
            if (!visited[i]){
                DFS(i,isConnected,visited);
                count++;
            }
        }
        return count;

    }
}

