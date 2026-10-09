import java.util.*;
import java.util.Queue;
class Solution {
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {

        // distance array
        int[][] dist = new int[n][n];

        for(int i = 0;i < n;i++){
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }

        // update adj. matrix using edges[][]
        for (int[] e : edges) {
            int u = e[0];
            int v = e[1];
            int wt = e[2];
            dist[u][v] = wt;
            dist[v][u] = wt;
        }

        // make all same edge dist. 0
        for (int i = 0; i < n; i++) {
            dist[i][i] = 0;
        }

        // updating and relaxing edges acc. to Floyd Warshal
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (dist[i][k] != Integer.MAX_VALUE && dist[k][j] != Integer.MAX_VALUE) {
                        dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                    }
                }
            }
        }

        // for every city, find out which city is connected within threshold dist. 
        int cityNo = -1; // which city is that(the optimal one)
        int cntCity = n; // no. of cities connected within threshold dist.(the optimal one)

        for (int city = 0; city < n; city++) {
            int cnt = 0; // finding current city's cnt
            for (int adjCity = 0; adjCity < n; adjCity++) {
                if (adjCity != city) {
                    // should not be same city
                    if (dist[city][adjCity] <= distanceThreshold) {
                        cnt++;
                    }
                }
            }

            if (cnt <= cntCity) {
                // found out better city
                cntCity = cnt;
                cityNo = city;
            }
        }

        return cityNo;
    }
}

class DijkstraSolution {
    int INF = (int) 1e9;

    // O(ElogV)
    private int dijkstra(int srcCity, int n, List<List<int[]>> adjList, int distanceThreshold) {
        Queue<int[]> que = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0])); // {dist, node}

        int[] dist = new int[n];
        Arrays.fill(dist, INF);

        que.add(new int[] { 0, srcCity });
        dist[srcCity] = 0;

        while (!que.isEmpty()) {
            int node = que.peek()[1];
            int dis = que.peek()[0];
            que.poll();

            for (int[] it : adjList.get(node)) {
                int adjNode = it[0];
                int edW = it[1];

                if (edW + dis < dist[adjNode]) {
                    dist[adjNode] = edW + dis;
                    que.add(new int[] { dist[adjNode], adjNode });
                }
            }
        }

        int cnt = 0;
        for (int city = 0; city < n; city++) {
            if (dist[city] <= distanceThreshold) {
                cnt++;
            }
        }

        return cnt;
    }

    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        // TC : O(V.ElogV)
        // SC : O(V + E)
        // using Dijkstra's Algo

        // make adjList
        List<List<int[]>> adjList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int[] e : edges) {
            int u = e[0];
            int v = e[1];
            int wt = e[2];

            adjList.get(u).add(new int[] { v, wt });
            adjList.get(v).add(new int[] { u, wt });
        }

        int cityNo = -1;
        int minCityCnt = n;

        for (int city = 0; city < n; city++) {
            int cnt = dijkstra(city, n, adjList, distanceThreshold);
            if (cnt <= minCityCnt) {
                minCityCnt = cnt;
                cityNo = city;
            }
        }

        return cityNo;
    }
}