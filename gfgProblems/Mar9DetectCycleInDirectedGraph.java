import java.util.*;
import java.util.Queue;
class DFSSolution {
	private boolean dfs(int node, boolean[] vis, boolean[] pathVis, List<List<Integer>> adjList) {
		// mark it visited and path visited
		vis[node] = true;
		pathVis[node] = true;
		
		for (int adjNode : adjList.get(node)) {
			if (vis[adjNode]) {
				if (pathVis[adjNode]) {
					// if visited in same path, cycle detected!
					return true;
				}
			} else {
				// not visited till now
				if (dfs(adjNode, vis, pathVis, adjList) == true) {
					// cycle !
					return true;
				}
			}
		}
		
		// now, remove from pathVis(as it will now not be the part of a path)
		pathVis[node] = false;
		return false; // no cycle detected!
	}
	public boolean isCyclic(int V, int[][] edges) {
	    // TC : O(V + E) (as graph is directed, therefore not 2E)
	    // SC : O(V + E) due to adjList, vis, pathVis
		// first make adjacency list
		List<List<Integer>> adjList = new ArrayList<>();
		for (int i = 0; i < V; i++) {
			adjList.add(new ArrayList<>());
		}
		for (int i = 0; i < edges.length; i++) {
			int u = edges[i][0];
			int v = edges[i][1];
			// directed edge : u -> v
			adjList.get(u).add(v);
		}
		boolean[] vis = new boolean[V]; // all nodes, which were traversed till now, are marked visited here
		boolean[] pathVis = new boolean[V]; // same path, whoever is visited, is marked here
		
		// to traverse all nodes(connected components concept)
		for (int i = 0; i < V; i++) {
			if (!vis[i]) {
				if (dfs(i, vis, pathVis, adjList) == true) {
					// has cycle
					return true;
				}
			}
		}
		
		return false; // no cycle found
	}
}


class RecursiveDFSSolution {
    boolean[] visited;
    boolean[] inRecursion;//in same DFS(what all are visited)
    boolean hasCycleDFS(int v,int[][] edges) {
        visited[v] = true;
        inRecursion[v] = true;
        for(int[] edge: edges){
            if(edge[0] != v){
                continue;
            }
            // else
            int n = edge[1];//neighbour
            if(!visited[n] && hasCycleDFS(n, edges)){
                return true;
            }
            // else see if it is visited and is true in current recursion
            else if (inRecursion[n]){
                return true;
            }
        }
        inRecursion[v] = false;
        return false;
    } 
    boolean isCyclic(int V, int[][] edges) {
        visited = new boolean[V];
        inRecursion = new boolean[V];
        for(int i = 0;i < V;i++){
            if(!visited[i] && hasCycleDFS(i, edges)){
                return true;
            }
        }
        return false;
    }
}
// using bfs
class BFSSolution {
    boolean[] visited;
    boolean[] inRecursion;//in same BFS(what all are visited)
    boolean hasCycleBFS(int v,int[][] edges) {
        visited[v] = true;
        inRecursion[v] = true;
        Queue<Integer> que = new LinkedList<>();
        que.add(v);
        while(!que.isEmpty()){
            int queV = que.poll();
            for(int[] edge: edges){
                if(edge[0] != queV){
                    continue;
                }
                // else
                int n = edge[1];//neighbour
                if(!visited[n] && hasCycleBFS(n, edges)){
                    return true;
                }
                // else see if it is visited and is true in current recursion
                else if (inRecursion[n]){
                    return true;
                }
            }
        }
        
        inRecursion[v] = false;
        return false;
    } 
    boolean isCyclic(int V, int[][] edges) {
        visited = new boolean[V];
        inRecursion = new boolean[V];
        for(int i = 0;i < V;i++){
            if(!visited[i] && hasCycleBFS(i, edges)){
                return true;
            }
        }
        return false;
    }
}

// using Topological sort (using Kahn's algo)
class KahnSolution {
	public boolean isCyclic(int V, int[][] edges) {
	    // TC : O(V + E) 
	    // SC : O(V)
		// using BFS (Kahn's Topo Sort Algo)
		
		// if the graph is cyclic, I cannot generate topo sort list of V nodes
		
		
		int[] indegree = new int[V];
		
		// make adjacency list
		List<List<Integer>> adjList = new ArrayList<>();
		
		for (int i = 0; i < V; i++) {
			adjList.add(new ArrayList<>());
		}
		
		// build adj. list
		for (int i = 0; i < edges.length; i++) {
			int u = edges[i][0];
			int v = edges[i][1];
			
			// u -> v
			adjList.get(u).add(v);
			indegree[v]++;
		}
		
		// make a queue only containing nodes having indegree 0
		Queue<Integer> que = new LinkedList<>();
		for (int i = 0; i < V; i++) {
			if (indegree[i] == 0)
				que.add(i);
		}
		
		int cnt = 0; // stores how many nodes are present in topo sort list
		
		// std. BFS algo
		while (!que.isEmpty()) {
			int node = que.poll();
			cnt++;
			
			// for all adjacent nodes, decrease there indegree
			for (int adjNode : adjList.get(node)) {
				indegree[adjNode]--;
				if (indegree[adjNode] == 0) {
					que.add(adjNode);
				}
			}
			
		}
		
		// if set contains all nodes, no cycle
		return cnt != V;
	}
}
