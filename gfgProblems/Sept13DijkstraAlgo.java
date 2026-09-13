import java.util.*;

class Pair {
    int node;
    int weight;

    Pair(int node, int weight) {
        this.node = node;
        this.weight = weight;
    }
}

class Solution {
    public ArrayList<Integer> dijkstra(int V, int[][] edges, int src) {
        // TC : O(ElogV)
        // SC : O(E + V)

        // make the adjacency list
        List<List<Pair>> adjList = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int wt = edge[2];

            // undirected edge u - v
            adjList.get(u).add(new Pair(v, wt));
            adjList.get(v).add(new Pair(u, wt));

        }

        ArrayList<Integer> dist = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            dist.add((int) 1e9);
        }
        dist.set(src, 0); // dist from src to src is 0

        // using Priority Queue : contains nodes sorted by distance from src
        PriorityQueue<Pair> minHeap = new PriorityQueue<>((a, b) -> Integer.compare(a.weight, b.weight));
        minHeap.add(new Pair(src, 0)); // src is starting node and therefore is added

        while (!minHeap.isEmpty()) {
            int node = minHeap.peek().node;
            int distance = minHeap.peek().weight; // dist from src
            minHeap.poll();

            // OPTIMIZATION: Skip processing if we already found a better path to this node
            if (distance > dist.get(node)) {
                continue;
            }

            for (Pair adjEdge : adjList.get(node)) {
                int adjNode = adjEdge.node;
                int weight = adjEdge.weight;

                if (weight + distance < dist.get(adjNode)) {
                    // relaxation
                    dist.set(adjNode, weight + distance);
                    minHeap.add(new Pair(adjNode, weight + distance));
                }
            }
        }

        return dist;
    }
}
