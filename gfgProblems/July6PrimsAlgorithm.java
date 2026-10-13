import java.util.*;

class Solution {
    int[] parent;
    int[] key;
    boolean[] mstSet;
    int V;

    private int findMinKey() {
        int index = -1;
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < V; i++) {
            if (mstSet[i] == false && key[i] < min) {
                min = key[i];
                index = i;
            }
        }

        return index;
    }

    public int spanningTree(int V, int[][] edges) {
        // TC : O(V*V + V*E)
        // SC : O(V)
        parent = new int[V];
        key = new int[V];
        mstSet = new boolean[V]; // visited nodes array
        this.V = V;

        for (int i = 0; i < V; i++) {
            key[i] = Integer.MAX_VALUE;
        }

        parent[0] = -1; // it is the first root element where MST formation starts
        key[0] = 0; // going to 0 from 0 takes 0 cost

        // an MST contains only V-1 edges
        int cnt = V - 1;
        while (cnt-- > 0) {

            // find min key node
            int minKeyNode = findMinKey();
            mstSet[minKeyNode] = true;

            for (int[] e : edges) {
                // undirected graph
                if (e[0] != minKeyNode && e[1] != minKeyNode) {
                    continue;
                }

                int u = minKeyNode;
                int v = (e[0] == minKeyNode) ? e[1] : e[0];
                int w = e[2];

                if (mstSet[v] == false && w < key[v]) {
                    // if v is not already visited
                    parent[v] = u;
                    key[v] = w;
                }

            }
        }

        int minCost = 0;
        for (int i = 0; i < V; i++) {
            if (key[i] != Integer.MAX_VALUE)
                minCost += key[i];
        }
        // parent array stores the whole MST, starting with idx having -1
        return minCost;
    }
}

class PQSolution {
    public int spanningTree(int V, int[][] edges) {
        // E means no. of edges in edges[][]
        // TC : O(V+E) + O(ElogE)
        // SC : O(V+E) + O(V) + O(E)
        // build the adjacency list -> List[Neighbour, weight]
        List<List<int[]>> adjList = new ArrayList<>();

        for (int v = 0; v < V; v++) {
            adjList.add(new ArrayList<>());
        }

        for (int[] e : edges) {

            int u = e[0];
            int v = e[1];
            int wt = e[2];

            adjList.get(u).add(new int[] { v, wt });
            adjList.get(v).add(new int[] { u, wt });
        }

        // using Prim's Algo
        // but using PriorityQueue

        // adding in pq -> {node, weight}
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> {
            return Integer.compare(a[1], b[1]);
        });

        boolean[] visited = new boolean[V];

        int minCost = 0;
        minHeap.add(new int[] { 0, 0 });

        while (!minHeap.isEmpty()) {
            int[] p = minHeap.poll();

            int u = p[0];
            int wt = p[1];

            if (visited[u])
                continue;

            minCost += wt;

            visited[u] = true; // mark it visited

            for (int[] e : adjList.get(u)) {

                int v = e[0];

                if (!visited[v]) {
                    minHeap.add(e);
                }
            }
        }

        return minCost;
    }
}

class MSTFindPrimsSolution {
    public static ArrayList<ArrayList<Integer>> calculatePrimsMST(int n, int m, ArrayList<ArrayList<Integer>> g) {
        // TC : O((N+M)logN)
        // SC : O(N + M)
        // create an adjacency list
        List<List<int[]>> adjList = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adjList.add(new ArrayList<>());
        }
        for (ArrayList<Integer> e : g) {
            int u = e.get(0);
            int v = e.get(1);
            int wt = e.get(2);

            adjList.get(u).add(new int[] { v, wt });
            adjList.get(v).add(new int[] { u, wt });
        }

        // make a min-Heap to store {edW, node, parent}
        Queue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        pq.add(new int[] { 0, 1, -1 }); // initially no edge, so parent of 1 is -1

        boolean[] vis = new boolean[n + 1];

        ArrayList<ArrayList<Integer>> mst = new ArrayList<>();

        while (!pq.isEmpty()) {
            int edW = pq.peek()[0];
            int node = pq.peek()[1];
            int parent = pq.peek()[2];
            pq.poll();

            if (vis[node]) {
                // already visited, if we include it, will make a cycle
                continue;
            }

            if (parent != -1) {
                // valid parent(no -1)
                // we have found an edge for our MST
                mst.add(new ArrayList<>(Arrays.asList(parent, node, edW)));
            }

            vis[node] = true; // mark it visited

            // iterate over adjNodes
            for (int[] it : adjList.get(node)) {
                int adjNode = it[0];
                int edgeWt = it[1];
                // OPTIMIZATION: Only push to PQ if the neighbor isn't visited yet
                if (!vis[adjNode])
                    pq.add(new int[] { edgeWt, adjNode, node });
            }
        }

        return mst;
    }
}