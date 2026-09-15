import java.util.*;

class Pair {
    int node;
    int weight;

    Pair(int node, int weight) {
        this.node = node;
        this.weight = weight;
    }
}

class PriorityQueueSolution {
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

class TreeSetSolution {
	int INF = (int) 1e9;
	public ArrayList<Integer> dijkstra(int V, int[][] edges, int src) {
		// TC : O(ElogV)
		// SC : O(V + E)
		
		// make adjList
		List<List<int[]>> adjList = new ArrayList<>();
		for (int i = 0; i < V; i++) {
			adjList.add(new ArrayList<>());
		}
		
		// adding edges in adjList
		for (int[] edge : edges) {
			int u = edge[0];
			int v = edge[1];
			int wt = edge[2];
			
			// since it is an undirected graph
			adjList.get(u).add(new int[] {v, wt});
			adjList.get(v).add(new int[] {u, wt});
		}
		
		ArrayList<Integer> dist = new ArrayList<>(); // stores distance from src
		for (int i = 0; i < V; i++) {
			dist.add(INF);
		}
		
		// using TreeSet to store {node, dist} in ascending order of dist
		// if dist is same, but nodes are different then also we write to sort through nodes, otherwise TreeSet would mark it as duplicate
		TreeSet<int[]> set = new TreeSet<>((a, b) -> {
			if (a[1] != b[1]) {
				return Integer.compare(a[1], b[1]);
			} else {
				// if distances are same, sort according to node ID
				return Integer.compare(a[0], b[0]);
			}
		});
		
		// src is 0 distance away from itself
		set.add(new int[] {src, 0});
		dist.set(src, 0);
		
		while (!set.isEmpty()) {
			// take out the smallest distance node
			int[] it = set.first();
			int node = it[0];
			int dis = it[1];
			set.remove(it); // removes the smallest one
			
			for (int[] neigh : adjList.get(node)) {
				int adjNode = neigh[0];
				int edgeW = neigh[1];
				
				if (dis + edgeW < dist.get(adjNode)) {
					// erase if it already existed in set
					if (dist.get(adjNode) != INF) {
						// some other node reached it already
						set.remove(new int[] {adjNode, dist.get(adjNode)});
					}
					// update the dist through node
					dist.set(adjNode, dis + edgeW);
					// push into the set
					set.add(new int[] {adjNode, dis + edgeW});
				}
			}
		}
		
		return dist;
	}
}