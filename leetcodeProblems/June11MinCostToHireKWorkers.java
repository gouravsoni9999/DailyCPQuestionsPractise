class Solution {
    public double mincostToHireWorkers(int[] quality, int[] min_wage, int k) {
        /*
            Brute Force Approach
            TC : O(n^2 * log(k) + n * k * log(k))
	    SC : O(k)
        */
        int n = quality.length;
        double result = Double.MAX_VALUE;
        // fix every index, so that I always include him(in his iteration)
        for(int manager = 0;manager < n;manager++){
            double manager_ratio = (double)min_wage[manager]/quality[manager];
            PriorityQueue<Double> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
            for(int worker = 0;worker < n;worker++){
                double worker_wage = manager_ratio * quality[worker];
                if(worker_wage >= min_wage[worker]){
                    maxHeap.add(worker_wage);
                    if(maxHeap.size() > k){
                        maxHeap.poll();
                    }
                }
            }
           
            if(maxHeap.size() >= k){
                double sum_wages = 0;
                for(int i = 0;i < k;i++){
                    sum_wages += maxHeap.poll();
                }
                result = Math.min(result, sum_wages);
            }
        }
        return result;
    }
}

class Solution {
    public double mincostToHireWorkers(int[] quality, int[] min_wage, int k) {
        /*
            Brute Force (Made - Better) Approach
            TC : O(n) + O(n*log(n)) + O(n*n*log(k) + n*k*log(k))
            SC : O(k) + O(2 * n)
        */
        int n = quality.length;
        double result = Double.MAX_VALUE;

        int[][] workerInfo = new int[n][2]; // stores {min_wage[i], quality[i]}

        for (int worker = 0; worker < n; worker++) {
            workerInfo[worker] = new int[] { min_wage[worker], quality[worker] };
        }

        // sort acc. to ratios(min_wage/quality) in ascending order
        Arrays.sort(workerInfo, (a, b) -> {
            return Double.compare((double) a[0] / a[1], (double) b[0] / b[1]);
        });

        // fix from kth index, so that I always include him(in his iteration)
        for (int manager = k - 1; manager < n; manager++) {
            double manager_ratio = (double) workerInfo[manager][0] / workerInfo[manager][1];
            PriorityQueue<Double> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
            for (int worker = 0; worker <= manager; worker++) {
                double worker_wage = (double) manager_ratio * workerInfo[worker][1];

                maxHeap.add(worker_wage);
                if (maxHeap.size() > k) {
                    maxHeap.poll();
                }
            }

            double sum_wages = 0;
            for (int i = 0; i < k; i++) {
                sum_wages += maxHeap.poll();
            }
            result = Math.min(result, sum_wages);
        }
        return result;
    }
}

class Solution {
    public double mincostToHireWorkers(int[] quality, int[] min_wage, int k) {
        /*
            Optimal Approach
            TC : O(n) + O(n*log(n)) + O(k*log(k)) + O((n-k)log(k))
            SC : O(k) + O(2 * n)
        */
        int n = quality.length;
        double result = Double.MAX_VALUE;

        int[][] workerInfo = new int[n][2]; // stores {min_wage[i], quality[i]}

        for (int worker = 0; worker < n; worker++) {
            workerInfo[worker] = new int[] { min_wage[worker], quality[worker] };
        }

        // sort acc. to ratios(min_wage/quality) in ascending order
        Arrays.sort(workerInfo, (a, b) -> {
            return Double.compare((double) a[0] / a[1], (double) b[0] / b[1]);
        });

        // store only qualtities
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        int sumOfQualities = 0;
        for (int i = 0; i < k; i++) {
            maxHeap.add(workerInfo[i][1]); // store qualities of first k workers
            sumOfQualities += workerInfo[i][1];
        }

        // find for (k-1)th person
        double manager_ratio = (double) workerInfo[k - 1][0] / workerInfo[k - 1][1];
        result = manager_ratio * sumOfQualities;

        // fix from kth index, so that I always include him(in his iteration)
        for (int manager = k; manager < n; manager++) {
            manager_ratio = (double) workerInfo[manager][0] / workerInfo[manager][1];

            maxHeap.add(workerInfo[manager][1]); // add quality of current manager in maxHeap
            sumOfQualities += workerInfo[manager][1];
            if (maxHeap.size() > k) {
                sumOfQualities -= maxHeap.poll(); // remove the quality which is greatest
            }
            result = Math.min(result, manager_ratio * sumOfQualities);
        }
        return result;
    }
}
