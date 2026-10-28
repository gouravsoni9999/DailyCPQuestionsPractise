import java.util.*;

class BruteForceSolution {

    private void dfs(int u, List<List<Integer>> adj, int skipU, int skipV, boolean[] vis) {
        // we cannot use same edge, as we have hypothetically removed it
        vis[u] = true; // mark it visited

        for (int v : adj.get(u)) {
            // skip the edge we are testing
            if ((u == skipU && v == skipV) || (u == skipV && v == skipU)) {
                continue;
            }
            if (!vis[v])
                dfs(v, adj, skipU, skipV, vis);
        }
    }

    private boolean isBridge(int u, int v, List<List<Integer>> adj, int totalVertices) {
        // remove this edge, and check if I can reach from u to v
        // if graph disconnects, it is bridge(ie, if I cannot reach from u to v)
        boolean[] vis = new boolean[totalVertices];

        // start from one endpoint
        dfs(u, adj, u, v, vis);

        if (!vis[v]) {
            return true; // I cannot reach v from u, the graph has disconnected
        }

        return false; // I can still reach v from u, the graph is not disconnected
    }

    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> edges) {
        // TC : O(E.(V+E))
        // SC : O(V + E)
        // Brute-force approach

        // create adj list
        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>());
        }
        for (List<Integer> e : edges) {
            int u = e.get(0);
            int v = e.get(1);

            adjList.get(v).add(u);
            adjList.get(u).add(v);
        }
        List<List<Integer>> bridges = new ArrayList<>();

        // check every edge
        for (int u = 0; u < n; u++) {
            for (int v : adjList.get(u)) {
                // u - v
                // to avoid duplicate checks: u < v
                if (u < v && isBridge(u, v, adjList, n)) {
                    // add into our bridges list
                    List<Integer> bridge = new ArrayList<>();
                    bridge.add(u);
                    bridge.add(v);
                    bridges.add(bridge);
                }
            }
        }

        return bridges;
    }
}

class TarjanAlgoSolution {

    private void dfs(int node, int parentNode, int timer, int[] disc, int[] low, List<List<Integer>> bridges, List<List<Integer>> adjList,boolean[] vis) {

        // mark node visited
        vis[node] = true;

        // both discovery time and low time initially are equal to timer
        disc[node] = low[node] = timer;

        for (int adjNode : adjList.get(node)) {
            // ignore parentNode
            if (adjNode == parentNode)
                continue;

            if (!vis[adjNode]) {
                dfs(adjNode, node, timer + 1, disc, low, bridges, adjList, vis); // timer increases after every dfs call

                // low of node is updated if adjNode's low is more less
                low[node] = Math.min(low[node], low[adjNode]); // there is alternate back-edge which knows there is one shortcut

                // check if edge is bridge or not
                if (low[adjNode] > disc[node]) {
                    // bridge is present
                    List<Integer> bridge = new ArrayList<>();
                    bridge.add(node);
                    bridge.add(adjNode);
                    bridges.add(bridge);
                }
            } else {
                // back edge, update low of node, which can be more short then back edge(alternative edge)
                low[node] = Math.min(low[node], disc[adjNode]);
            }

        }

    }

    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> edges) {
        // TC : O(V + E)
        // SC : O(V + E)
        // Tarjan's bridge finding algo

        // create adj list
        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>());
        }
        for (List<Integer> e : edges) {
            int u = e.get(0);
            int v = e.get(1);

            adjList.get(v).add(u);
            adjList.get(u).add(v);
        }

        List<List<Integer>> bridges = new ArrayList<>();
        int timer = 0;

        int[] disc = new int[n]; // contains discovery time of every node while dfs()
        Arrays.fill(disc, -1);

        int[] low = new int[n]; //  The earliest discovery time reachable from i, either through its descendants or via back edges.
        Arrays.fill(low, -1);

        int parent = -1; // stores from where the call was made to the neighbour

        boolean[] vis = new boolean[n]; // dfs() call visited array

        // dfs call
        for (int node = 0; node < n; node++) {
            if (!vis[node])
                dfs(node, parent, timer, disc, low, bridges, adjList, vis);
        }

        return bridges;
    }
}
