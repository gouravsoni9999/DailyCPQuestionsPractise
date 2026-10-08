import java.util.*;
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