import java.util.*;
import java.util.Queue;
class Solution {
    public int[] shortestPath(int[][] edges, int N, int M) {

        // TC and SC : O(N + M)
        int[] dist = new int[N]; // stores shortest dist from src to any node
        Arrays.fill(dist, Integer.MAX_VALUE); // first, store all nodes having distance as infinity to 0 (src)
        

        // making adjacency list
        List<List<Integer>> adjList = new ArrayList<>();
        for(int i = 0;i < N;i++){
            adjList.add(new ArrayList<>());
        }

        // build using edges
        for(int i = 0;i < M;i++){
            int u = edges[i][0];
            int v = edges[i][1];
            // u - v
            adjList.get(u).add(v);
            adjList.get(v).add(u);
        }

        // using BFS
        Queue<Integer> que = new LinkedList<>(); // only stores node
        que.add(0); 
        dist[0] = 0;// dist of node 0 to node 0 is 0

        while(!que.isEmpty()){
            int node = que.poll();
            int distFromSrc = dist[node];

            for(int adjNode : adjList.get(node)){
                if(distFromSrc + 1 < dist[adjNode]){
                    // update new shorter distance
                    dist[adjNode] = distFromSrc + 1;
                    // add in que for further BFS calls
                    que.add(adjNode);
                }
            }
        }

        // convert all infinite distance to -1(not reachable)
        for(int i = 0;i < N;i++){
            if(dist[i] == Integer.MAX_VALUE){
                dist[i] = -1;
            }
        }

        return dist;
    }
}
