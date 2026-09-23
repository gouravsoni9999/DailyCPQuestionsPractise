import java.util.*;

class Solution {
    public ArrayList<Integer> shortestPath(int V, int[][] edges, int src, int dest) {
        // using Dijkstra's algo
        // TC : O(ElogV) + O(V)
        // SC : O(V + E)

        // making of adjacency list
        List<List<int[]>> adjList = new ArrayList<>();

        for (int i = 0; i <= V; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int wt = edge[2];

            adjList.get(u).add(new int[] { v, wt });
            adjList.get(v).add(new int[] { u, wt });
        }

        // using PriorityQueue(min-Heap) acc. to distance
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1])); // stores [node,
                                                                                                   // distance]

        // make parent[] and dist[]
        int[] parent = new int[V + 1];
        int[] dist = new int[V + 1];
        for (int i = 0; i <= V; i++) {
            parent[i] = i; // initially, everybody is its own parent
            dist[i] = (int) 1e9;
        }
        // add src to minHeap
        dist[src] = 0;
        minHeap.add(new int[] { src, 0 });

        while (!minHeap.isEmpty()) {
            int[] curr = minHeap.poll();
            int node = curr[0];
            int wt = curr[1]; // distance from src to current node

            // iterate over all adjacent nodes
            for (int[] it : adjList.get(node)) {
                int adjNode = it[0];
                int distance = it[1];

                if (wt + distance < dist[adjNode]) {
                    dist[adjNode] = wt + distance; // relaxation
                    parent[adjNode] = node; // update the parent
                    minHeap.add(new int[] { adjNode, dist[adjNode] }); // push the updated adjNode in minHeap
                }
            }
        }

        // make res list
        ArrayList<Integer> list = new ArrayList<>();

        if (dist[dest] == (int) 1e9) {
            // no way possible for reaching from src to dest
            list.add(-1);
            return list;
        }

        int node = dest;

        while (parent[node] != node) {
            list.add(node);
            node = parent[node];
        }

        list.add(src);

        Collections.reverse(list);
        return list;
    }
}
