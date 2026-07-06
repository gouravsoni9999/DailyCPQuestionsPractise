import java.util.*;
class Solution {
    private int findParent(int node, int[] parent){
        while(node != parent[node-1]){
            node = parent[node-1];
        }
        return node;
    }
    private void union(int a,int b,int[] parent){
        int parent_a = findParent(a, parent);
        int parent_b = findParent(b, parent);
        parent[parent_a-1] = parent_b;
    }
    public ArrayList<Integer> DSU(int n, int[][] queries) {
        /*
            TC : O(n + k.n)
            SC : O(n + k)
        */
        int[] parent = new int[n];
        for(int i = 0;i < n;i++){
            parent[i] = i+1; // first all are 
        }
        ArrayList<Integer> res = new ArrayList<>();
        int k = queries.length;
        
        // iterate over queries
        for(int i = 0;i < k;i++){
            if(queries[i].length == 2){
                // FIND Operation
                int idx = queries[i][1];
                int parentIdx = findParent(idx, parent);
                res.add(parentIdx);
            }else{
                // UNION Operation
                int a = queries[i][1];
                int b = queries[i][2];
                union(a, b, parent);
            }
        }
        
        return res;
    }
}
