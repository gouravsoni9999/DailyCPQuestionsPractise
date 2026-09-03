import java.util.*;
import java.util.Stack;
import java.util.Queue;
class DFSSolution {
    
    List<List<Integer>> adjList;
    boolean[] vis;
    Stack<Integer> stack;
    
    private void dfs(int node,int V){
        vis[node] = true; // mark it visited
        
        for(int adjNode : adjList.get(node)){
            if(!vis[adjNode])
                // if not already visited
                dfs(adjNode, V);
        }
        
        stack.push(node);
    }
    
	public ArrayList<Integer> topoSort(int V, int[][] edges) {
        // TC : O(V + E) (only for directed graphs, E is present)
        // SC : O(2*V)
		// making adjacency list
		adjList = new ArrayList<>();
		for (int i = 0; i < V; i++) {
			adjList.add(new ArrayList<>());
		}
		
		// build full adjacency list using given edges
		for (int i = 0; i < edges.length; i++) {
			int u = edges[i][0];
			int v = edges[i][1];
			
			// u -> v
			adjList.get(u).add(v);
		}
		
	    vis = new boolean[V];
	    stack = new Stack<>();
		
	
	    for(int i = 0;i < V;i++){
	        if(!vis[i])
	            dfs(i, V);
	    }	
	    
	    ArrayList<Integer> list = new ArrayList<>();
	    while(!stack.isEmpty()){
	        list.add(stack.pop());
	    }
	    return list;
	}
}

// using BFS (Kahn's Algo)
class BFSSolution {
    public ArrayList<Integer> topoSort(int V, int[][] edges) {
        // TC : O(V + E)
        // SC : O(V)
        // using Kahn's Algo (BFS Approach)
        int[] indegree = new int[V]; // stores indegree of every node
        
        // building adjacency list
        List<List<Integer>> adjList = new ArrayList<>();
        int n = edges.length;
        for(int i = 0;i < V;i++){
            adjList.add(new ArrayList<>());
        }
        for(int i = 0;i < n;i++){
            int u = edges[i][0];
            int v = edges[i][1];
            
            // u -> v
            adjList.get(u).add(v);
            // indegree of v is increased
            indegree[v]++;
        }
        
        // Queue for BFS, only 0 indegree nodes are added in queue
        Queue<Integer> que = new LinkedList<>();
        
        // first, iterate over every node and add them if they are eligible
        for(int i = 0;i < V;i++){
            if(indegree[i] == 0){
                que.add(i);
            }
        }
        
        ArrayList<Integer> orderList = new ArrayList<>(); // stores topo sort of nodes
        
        // general BFS style
        while(!que.isEmpty()){
            int node = que.poll();
            
            orderList.add(node); // added in order
            
            // all adjacent nodes's indegree is decreased due to removal of node
            for(int adjNode : adjList.get(node)){
                indegree[adjNode]--;
                if(indegree[adjNode] == 0){
                    que.add(adjNode); // only add when adjNode's indegree is 0
                }
            }
        }
        
        return orderList;
    }
}