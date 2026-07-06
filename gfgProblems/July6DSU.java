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

// this code maynot pass the actual problem, becoz question is slightly different
class Solution {
	// path compression using recursion
	private int findParent(int node, int[] parent) {
		if (node == parent[node - 1]) {
			return node;
		}
		// Recursively find the root and compress the path by caching it
		return parent[node - 1] = findParent(parent[node - 1], parent);
	}
	private void union(int a, int b, int[] parent, int[] rank) {
		// union by rank
		int parent_a = findParent(a, parent);
		int parent_b = findParent(b, parent);
		if (parent_a == parent_b)
			return; // both are already part of same tree
		// insert into larger tree
		if (rank[parent_a - 1] > rank[parent_b - 1]) {
			parent[parent_b - 1] = parent_a;
		} else if(rank[parent_b - 1] > rank[parent_a - 1]) {
			parent[parent_a - 1] = parent_b;
		} else{
		    // both have same ranks
		    parent[parent_a - 1] = parent_b;
		    rank[parent_b - 1]++; // only update when both are of same ranks
		}
	}
	public ArrayList<Integer> DSU(int n, int[][] queries) {
		// using optimization techniques like Path compression(everyone linked to root parent directly)
		// and union by rank(for merging smaller tree to larger tree)
		/*
		TC : O(n + k)
		SC : O(n + k)
		*/
		int[] parent = new int[n];
		int[] rank = new int[n]; // union by rank
		for (int i = 0; i < n; i++) {
			parent[i] = i + 1; // first all are
		}
		
		// everybody has all ranks 0
		Arrays.fill(rank, 0);
		
		ArrayList<Integer> res = new ArrayList<>();
		int k = queries.length;
		
		// iterate over queries
		for (int i = 0; i < k; i++) {
			if (queries[i].length == 2) {
				// FIND Operation
				int idx = queries[i][1];
				int parentIdx = findParent(idx, parent);
				res.add(parentIdx);
			} else {
				// UNION Operation
				int a = queries[i][1];
				int b = queries[i][2];
				union(a, b, parent, rank);
			}
		}
		
		return res;
	}
}
