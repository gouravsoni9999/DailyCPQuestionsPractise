import java.util.*;
import java.util.Queue;

class Solution {
    public int[] assignTasks(int[] servers, int[] tasks) {
        // TC : O((n+m)logn)
        // SC : O(m + n)
        int n = servers.length;
        int m = tasks.length;
        int[] ans = new int[m]; // stores index of that server, which has been assigned jth task

        // make freeServer min-Heap storing {wt, idx} sorted by wt and then by idx
        Queue<int[]> freeServerHeap = new PriorityQueue<>((a, b) -> {
            if (a[0] == b[0]) {
                return Integer.compare(a[1], b[1]);
            } else {
                return Integer.compare(a[0], b[0]);
            }
        });

        // make busyServer min-Heap storing {finishingTime, wt, idx} sorted by
        // finishingTime and then by wt and then by idx
        Queue<int[]> busyServerHeap = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) {
                return Integer.compare(a[0], b[0]);
            } else if (a[1] != b[1]) {
                return Integer.compare(a[1], b[1]);
            } else {
                // a[2] != b[2]
                return Integer.compare(a[2], b[2]);
            }
        });

        // fill freeServer before starting
        for (int i = 0; i < n; i++) {
            int wt = servers[i];
            int idx = i;
            freeServerHeap.add(new int[] { wt, idx });
        }

        for (int time = 0; time < m; time++) {
            // process every task
            // but first free those busy servers who have finishingTime <= time
            while (!busyServerHeap.isEmpty() && busyServerHeap.peek()[0] <= time) {
                int[] it = busyServerHeap.poll();
                // put back in free server heap
                freeServerHeap.add(new int[] { it[1], it[2] }); // putting back wt and idx
            }

            // if all servers are busy
            if (freeServerHeap.isEmpty()) {
                // pop out from busyServer, the first one
                int finTime = busyServerHeap.peek()[0];
                int wt = busyServerHeap.peek()[1];
                int idx = busyServerHeap.peek()[2];
                busyServerHeap.poll();

                ans[time] = idx; // this server idx would process this task
                // again push in busyServerHeap
                busyServerHeap.add(new int[] { finTime + tasks[time], wt, idx });
            }

            // else there is a free server
            else {
                // pop out from free server
                int[] it = freeServerHeap.poll();
                int wt = it[0];
                int idx = it[1];

                ans[time] = idx;
                // push in busyServerHeap
                busyServerHeap.add(new int[] { time + tasks[time], wt, idx });
            }
        }

        return ans;
    }
}