import java.util.*;
class Solution {
	int INF = (int) 1e8;
	public ArrayList<Integer> bellmanFord(int V, int[][] edges, int src) {
		// TC : O(V x E)
		// SC : O(V)
		ArrayList<Integer> dist = new ArrayList<>();
		
		for (int i = 0; i < V; i++) {
			dist.add(INF);
		}
		
		dist.set(src, 0); // distance from src to src is 0
		
		// relaxations done V-1 times
		for (int i = 0; i < V - 1; i++) {
			// iterate on all edges
			for (int[] e : edges) {
				int u = e[0];
				int v = e[1];
				int wt = e[2];
				
				if (dist.get(u) != INF && dist.get(u) + wt < dist.get(v)) {
					dist.set(v, dist.get(u) + wt); // relax if it is reachable from src -> u -> v
				}
			}
		}
		
		// see Vth time, if still dist is relaxing, means negative cycle present
		for (int[] e : edges) {
			int u = e[0];
			int v = e[1];
			int wt = e[2];
			if (dist.get(u) != INF && dist.get(u) + wt < dist.get(v)) {
				// negative cycle
				return new ArrayList<>(Arrays.asList(-1));
			}
		}
		
		return dist;
		
	}
}
