import java.util.*;
import java.util.Queue;
class DFSSolution {
	private boolean dfs(int src, int parent, int V, List<List<Integer>> adjList, boolean[] visited) {
		// using DFS
		
		visited[src] = true; // mark 0 as visited
		
		for (int neigh : adjList.get(src)) {
			if (!visited[neigh]) {
				if (dfs(neigh, src, V, adjList, visited)) {
					return true; // cycle detected
				}
			} else if (neigh != parent) {
				// if neigh was already visited and is not a parent
				return true; // cycle detected
			}
		}
		
		return false; // no cycle detected
	}
	public boolean isCycle(int V, int[][] edges) {
		// TC : O(N + 2E) // nodes + summation of degree(which is summation of adjacent nodes)
		// SC : O(N) + O(N) recurive stack space
		// creating adjacency list
		List<List<Integer>> adjList = new ArrayList<>();
		for (int i = 0; i < V; i++) {
			adjList.add(new ArrayList<>());
		}
		
		for (int[] e : edges) {
			int u = e[0];
			int v = e[1];
			adjList.get(u).add(v);
			adjList.get(v).add(u);
		}
		
		boolean[] visited = new boolean[V];
		
		for (int i = 0; i < V; i++) {
			if (!visited[i] && dfs(i, -1, V, adjList, visited)) {
				// if i is not visited and cycle is detected in that component
				return true;
			}
		}
		
		return false;
	}
}


class BFSSolution {
    private boolean BFS(int src, int V,List<List<Integer>> adjList, boolean[] visited){
        // using BFS
        Queue<int[]> que = new LinkedList<>();
        que.add(new int[]{src,-1}); // {node, parent}
        visited[src] = true; // mark 0 as visited
       
        while(!que.isEmpty()){
            int node = que.peek()[0];
            int parent = que.peek()[1];
            que.poll();
            
            for(int neigh : adjList.get(node)){
                if(!visited[neigh]){
                    visited[neigh] = true; // mark it visited
                    que.add(new int[] {neigh, node});
                }else if(neigh != parent){
                    // it has already been visited and is also not the parent
                    // cycle
                    return true;
                }
            }
        }
        
        return false; // no cycle detected
    }
    public boolean isCycle(int V, int[][] edges) {
        // TC : O(N + 2E) // nodes + summation of degree(which is summation of adjacent nodes)
        // SC : O(N)
        // creating adjacency list
        List<List<Integer>> adjList = new ArrayList<>();
        for(int i = 0;i < V;i++){
            adjList.add(new ArrayList<>());
        }
        
        for(int[] e : edges){
            int u = e[0];
            int v = e[1];
            adjList.get(u).add(v);
            adjList.get(v).add(u);
        }
        
        boolean[] visited = new boolean[V];
        
        for(int i = 0;i < V;i++){
            if(!visited[i] && BFS(i, V, adjList, visited)){
                // if i is not visited and cycle is detected in that component
                return true;
            }
        }
        
        return false;
    }
}