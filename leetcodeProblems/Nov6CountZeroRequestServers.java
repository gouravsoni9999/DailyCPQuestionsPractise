import java.util.*;
class Solution {
    int[] req; // counts server requests in range for every server

    public int[] countServers(int n, int[][] logs, int x, int[] queries) {
        // TC : O(mlogm) + O(qlogq) + O(q + 2m)
        // SC : O(q) + O(n)
        req = new int[n + 1];
        int m = logs.length;
        // sort logs array in terms of time
        Arrays.sort(logs, (a, b) -> Integer.compare(a[1], b[1]));

        // sort queries acc. to time, while maintaining their og pos
        int q = queries.length;
        int[][] query = new int[q][2];

        for (int i = 0; i < q; i++) {
            query[i][0] = queries[i];
            query[i][1] = i; // store original position's index
        }
        Arrays.sort(query, (a, b) -> Integer.compare(a[0], b[0])); // sort acc. to time

        int[] arr = new int[q]; // stores res of queries
        // now, use sliding window
        int i = 0;
        int j = 0;

        int zeroRequestServersCnt = n;

        // touching every log only 2 times: one by i and one by j : O(q + 2m)
        for (int[] qry : query) {
            int qTime = qry[0];
            int idx = qry[1];

            // range of query
            int start = qTime - x;
            int end = qTime;

            // expand and move j till logs[j][1] gets > end
            while (j < m && logs[j][1] <= end) {
                int serverId = logs[j][0];
                if (req[serverId] == 0) {
                    zeroRequestServersCnt--; // now, it server is not idle
                }
                req[serverId]++; // in range, server logs[j][0] request is not present, reduce by one

                j++;
            }

            // shrink and move i till logs[i][1] gets >= start
            while (i < m && logs[i][1] < start) {
                int serverId = logs[i][0];
                req[serverId]--; // in range, server logs[i][0] request is not present, reduce by one
                if (req[serverId] == 0) {
                    // now, server is idle
                    zeroRequestServersCnt++;
                }
                i++;
            }

            arr[idx] = zeroRequestServersCnt;
        }

        return arr;
    }
}