import java.util.*;
import java.util.Stack;
class Pair{
    int v;
    int wt;
    Pair(int v,int wt){
        this.v = v;
        this.wt = wt;
    }
}

class Solution {
    private void dfs(int node,boolean[] vis,List<List<Pair>> adjList,Stack<Integer> stack){
        vis[node] = true; // mark as visited
        
        for(Pair adjPair : adjList.get(node)){
            int adjNode = adjPair.v;
            if(!vis[adjNode]){
                dfs(adjNode, vis, adjList, stack);
            }
        }
        stack.push(node);
    }
    public ArrayList<Integer> shortestPath(int V, int[][] edges) {
        // TC : O(V + E) -> dfs() -> topoSort and stack operations both
        // SC : O(V + E)
        ArrayList<Integer> dist = new ArrayList<>(); // stores distance b/w vertex 0 to vertex i
        int E = edges.length; // no. of edges
        
        // make adjacency list
        List<List<Pair>> adjList = new ArrayList<>();
        for(int i = 0;i < V;i++){
            adjList.add(new ArrayList<>());
        }
        for(int i = 0;i < E;i++){
            int u = edges[i][0];
            int v = edges[i][1];
            int wt = edges[i][2];
            adjList.get(u).add(new Pair(v, wt));
        }
        
        boolean[] vis = new boolean[V];
        Stack<Integer> stack = new Stack<>();
        // find the topoSort (using DFS)
        for(int i = 0;i < V;i++){
            if(!vis[i]){
                dfs(i, vis, adjList, stack);
            }
        }
        
        int INF = (int) 1e9;
        
        for(int i = 0;i < V;i++){
            dist.add(INF); // infinity value
        }
        
        dist.set(0, 0); // set 0 - 0 dist to be 0
        
        // pop elements from stack and relax distance
        while(!stack.isEmpty()){
            int node = stack.pop();
            // relax all adjNodes
            for(Pair adjPair : adjList.get(node)){
                int adjNode = adjPair.v;
                int wt = adjPair.wt;
                
                if(wt + dist.get(node) < dist.get(adjNode)){
                    // relax 
                    dist.set(adjNode, wt + dist.get(node));
                }
            }
        }
        
        // set all infinite values to -1
        for(int i = 0;i < V;i++){
            if(dist.get(i) == INF){
                dist.set(i, -1);
            }
        }
        
        return dist;
    }
}