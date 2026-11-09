import java.util.*;
import java.util.Queue;
class Solution {
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        // TC : O(klogk + nlogk)
        // SC : O(k)
        int n = arrival.length;
        List<Integer> res = new ArrayList<>(); // consists of ids of busiest servers
        // take treeset to insert or delete elements in logn time
        TreeSet<Integer> available_servers = new TreeSet<>();
        for (int i = 0; i < k; i++) {
            // k.log(k)
            available_servers.add(i);
        }

        // pq 
        Queue<int[]> busyServers = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0])); // compares acc. to FinishingTime(0th index) // stores {finishingTime, serverId}

        int[] countRequests = new int[k];

        // n.logk
        for (int i = 0; i < n; i++) {
            int time = arrival[i];

            while (!busyServers.isEmpty() && busyServers.peek()[0] <= time) {
                // this server is now free
                int serverId = busyServers.peek()[1];

                busyServers.poll(); // remove from busyServer
                // add in freeServers TreeSet
                available_servers.add(serverId);
            }

            int target = i % k;

            // finds first value >= target
            Integer lowerBound = available_servers.ceiling(target);

            // Wrap-around logic: if none found >= target, check from 0
            if (lowerBound == null) {
                lowerBound = available_servers.ceiling(0);
            }

            // Only drop the request if absolutely NO servers are free anywhere
            if (lowerBound == null) {
                continue;
            }

            available_servers.remove(lowerBound); // remove it, as it is now busy

            busyServers.add(new int[] { time + load[i], lowerBound });

            countRequests[lowerBound]++;
        }

        // find max amount of requests
        int maxi = 0;
        for (int val : countRequests) {
            maxi = Math.max(maxi, val);
        }
        for (int i = 0; i < k; i++) {
            if (countRequests[i] == maxi) {
                res.add(i);
            }
        }
        return res;
    }
}