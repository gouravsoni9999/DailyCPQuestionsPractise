import java.util.*;

class BruteForceSolution {

    static void dfs(int node, List<List<Integer>> adjList, boolean[] vis) {
        vis[node] = true;

        for (int adjNode : adjList.get(node)) {
            if (!vis[adjNode])
                dfs(adjNode, adjList, vis);
        }
    }

    static ArrayList<Integer> articulationPoints(int V, int[][] edges) {
        // TC : O(V.(V + E))
        // SC : O(V + E)

        // make adjList
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

        ArrayList<Integer> articulationPts = new ArrayList<>(); // stores all articulation points in the graph

        // brute-force

        // Traverse over all nodes, hypothetically remove them from graph and find out
        // how many dfs calls are needed to be made
        for (int node = 0; node < V; node++) {
            // remove node (hypothetically)

            boolean[] vis = new boolean[V];
            vis[node] = true; // so that dfs() doesnot visit this node

            int components = 0;

            // run dfs() over unvisited adjacent neighbours only
            for (int adjNode : adjList.get(node)) {
                if (!vis[adjNode]) {
                    dfs(adjNode, adjList, vis);
                    components++;
                }
            }

            if (components > 1) {
                // articulation point found!
                articulationPts.add(node);
            }

        }

        if (articulationPts.isEmpty())
            articulationPts.add(-1);

        return articulationPts;
    }
}

class TarjanAlgoSolution {

    static void dfs(int node, int parentNode, int[] disc, int[] low, boolean[] vis, List<List<Integer>> adjList,
            boolean[] isArticulationPt, int timer) {
        vis[node] = true;

        disc[node] = low[node] = timer;
        int child = 0;

        for (int adjNode : adjList.get(node)) {
            if (adjNode == parentNode) {
                continue; // ignore
            }
            if (!vis[adjNode]) {
                dfs(adjNode, node, disc, low, vis, adjList, isArticulationPt, timer + 1);
                // update low(as adjNode have found a backedge)
                low[node] = Math.min(low[node], low[adjNode]);

                // check articulation point or not
                if (low[adjNode] >= disc[node] && parentNode != -1) {
                    isArticulationPt[node] = true;
                }

                child++;
            } else {
                // back edge found!
                low[node] = Math.min(low[node], disc[adjNode]);
            }

        }

        if (parentNode == -1 && child > 1) {
            // found multiple childs of root element, therefore root is an articulation
            // point
            isArticulationPt[node] = true;
        }
    }

    static ArrayList<Integer> articulationPoints(int V, int[][] edges) {
        // TC : O(V + E)
        // SC : O(V + E)
        // using Tarjan's algo

        // make adjList
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

        ArrayList<Integer> articulationPts = new ArrayList<>(); // stores all articulation points in the graph

        int[] disc = new int[V]; // discovery time of that node while dfs
        int[] low = new int[V]; // lowest ancestor time, ignoring updation by parent
        boolean[] isArticulationPt = new boolean[V];

        Arrays.fill(disc, -1);
        Arrays.fill(low, -1);

        boolean[] vis = new boolean[V];
        int timer = 0;

        // dfs
        for (int i = 0; i < V; i++) {
            if (!vis[i])
                dfs(i, -1, disc, low, vis, adjList, isArticulationPt, timer);
        }

        for (int i = 0; i < V; i++) {
            if (isArticulationPt[i])
                articulationPts.add(i);
        }

        if (articulationPts.isEmpty())
            articulationPts.add(-1);
        articulationPts.add(-1);

        return articulationPts;
    }
}
