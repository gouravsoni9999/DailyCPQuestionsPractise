class Solution {
    int[] parent;
    int[] key;
    boolean[] mstSet;
    int V;
    private int findMinKey(){
        int index = -1;
        int min = Integer.MAX_VALUE;
        
        for(int i = 0;i < V;i++){
            if(mstSet[i] == false && key[i] < min){
                min = key[i];
                index = i;
            }
        }
        
        return index;
    }
    public int spanningTree(int V, int[][] edges) {
        parent = new int[V];
        key = new int[V];
        mstSet = new boolean[V]; // visited nodes array
        this.V = V;
        
        for(int i = 0;i < V;i++){
            key[i] = Integer.MAX_VALUE;
        }
        
        parent[0] = -1; // it is the first root element where MST formation starts
        key[0] = 0; // going to 0 from 0 takes 0 cost
        
        // an MST contains only V-1 edges
        int cnt = V-1;
        while(cnt-- > 0){
            
            // find min key node
            int minKeyNode = findMinKey();
            mstSet[minKeyNode] = true;
            
            for(int[] e : edges){
                // undirected graph
                if(e[0] != minKeyNode && e[1] != minKeyNode){
                    continue;
                }
                
                int u = minKeyNode;
                int v = (e[0] == minKeyNode) ? e[1] : e[0];
                int w = e[2];
                
                if(mstSet[v] == false && w < key[v]){
                    // if v is not already visited
                    parent[v] = u;
                    key[v] = w;
                }
                
            }
        }
        
        int minCost = 0;
        for(int i = 0;i < V;i++){
            if(key[i] != Integer.MAX_VALUE)
                minCost += key[i];
        }
        // parent array stores the whole MST, starting with idx having -1
        return minCost;
    }
}
