import java.util.*;
class DSU {
    int V;
    int[] parent;
    int[] rank;
    
    // constructor
    public DSU(int V){
        this.V = V;
        this.parent = new int[V];
        this.rank = new int[V];
        
        for(int i = 0;i < V;i++){
            parent[i] = i;
            rank[i] = 0;
        }
    }
    
    public int find(int node){
        // recursively find out and use path compression(optimization)
        if(node == parent[node]){
            // base case
            return node;
        }
        return parent[node] = find(parent[node]);
    }
    
    public void union(int x,int y){
        int parent_x = find(x);
        int parent_y = find(y);
        
        if(parent_x == parent_y){
            return; // both are part of the group (already)
        }
        
        // if both are from different groups(disjoint sets)
        // union them, but smaller should be merged to larger one
        // so that union by rank(optimization) is implemented
        if(rank[parent_x] > rank[parent_y]){
            parent[parent_y] = parent_x;
        }else if(rank[parent_y] > rank[parent_x]){
            parent[parent_x] = parent_y;
        }else{
            // both ranks are same
            // merge in any one and increase its rank
            parent[parent_x] = parent_y;
            rank[parent_y]++;
        }
    }
}
class Solution {
    static int kruskalsMST(int V, int[][] edges) {
        // TC : O(eloge) + O(e)
        // SC : O(V)
        // kruskal's Algo works on edges (not vertices)
        // using DSU Data Structure
        
        // sort edges in ascending order (we want min. cost spanning tree)
        Arrays.sort(edges, (a,b)->{
            return Integer.compare(a[2], b[2]);
        });
        
        int minCost = 0;
        int count = 0; // MST edges count should not exceed V-1
        
        DSU dsu = new DSU(V);
        
        // traverse all edges (already sorted)
        for(int[] e : edges){
            int u = e[0];// node 1
            int v = e[1];// node 2
            int w = e[2];// weight
            
            // only connect when both are from different groups
            // otherwise we can get a cycle in MST
            if(dsu.find(u) != dsu.find(v)){
                dsu.union(u, v);
                minCost += w;
                count++; // one more edge added in MST
                if(count == V-1){
                    // got my MST
                    break;
                }
            }
        }
        
        return minCost;
    }
}
