import java.util.*;
class DSU {
    int n;
    int[] parent, size;

    DSU(int n) {
        this.n = n;
        parent = new int[n];
        size = new int[n];
        Arrays.fill(size, 1);
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    int findParent(int node) {
        if (parent[node] == node)
            return node;

        return parent[node] = findParent(parent[node]);
    }

    void union(int u, int v) {
        int rootU = findParent(u);
        int rootV = findParent(v);

        if (rootU != rootV) {
            // diff. component, union them
            if (size[rootU] < size[rootV]) {
                parent[rootU] = rootV;
                size[rootV] += rootU;
            } else {
                parent[rootV] = rootU;
                size[rootU] += rootV;
            }
        }
    }
}

class DisjointSetSolution {
    public int minSwaps(int[] nums) {
        // TC : O(nlogn + nlogm) where m is max. value in array
        // SC : O(n)
        int n = nums.length;

        // determine the digit sum of every element in nums
        int[] digitSum = new int[n];
        for (int i = 0; i < n; i++) {
            int x = nums[i];
            int sum = 0;
            while (x > 0) {
                sum += x % 10;
                x = x / 10;
            }
            digitSum[i] = sum;
        }

        // store indices in an array
        Integer[] indices = new Integer[n];
        for (int i = 0; i < n; i++) {
            indices[i] = i;
        }

        // sort indices array to find out actual position of nums[i] after sorting : sorting by (digitSum, value)
        Arrays.sort(indices, (a, b) -> {
            if (digitSum[a] != digitSum[b]) {
                // sort by digitSum in ascending order
                return Integer.compare(digitSum[a], digitSum[b]);
            } else {
                // sort by value in ascending order
                return Integer.compare(nums[a], nums[b]);
            }
        });

        // now indices[] arr. has indices of target positions of og. index

        // now finding no. of cycles(permutation cycles) in this graph

        // using Disjoint Set (union-find) DS
        int cycles = 0;
        DSU dsu = new DSU(n);
        for (int i = 0; i < n; i++) {
            dsu.union(i, indices[i]);
        }
        for (int i = 0; i < n; i++) {
            if (dsu.findParent(i) == i) {
                // i is the ultimate parent of one of the component
                cycles++; // as each component has cycle
            }
        }

        return n - cycles;
    }
}

class Solution {
    public int minSwaps(int[] nums) {
        int n = nums.length;
        
        // determine the digit sum of every element in nums
        int[] digitSum = new int[n];
        for(int i = 0;i < n;i++){
            int x = nums[i];
            int sum = 0;
            while(x > 0){
                sum += x%10;
                x = x/10;
            }
            digitSum[i] = sum;
        }

        // store indices in an array
        Integer[] indices = new Integer[n];
        for(int i = 0;i < n;i++){
            indices[i] = i;
        }

        // sort indices array to find out actual position of nums[i] after sorting : sorting by (digitSum, value)
        Arrays.sort(indices, (a,b)->{
            if(digitSum[a] != digitSum[b]){
                // sort by digitSum in ascending order
                return Integer.compare(digitSum[a], digitSum[b]);
            }else{
                // sort by value in ascending order
                return Integer.compare(nums[a], nums[b]);
            }
        });

        // now indices[] arr. has indices of target positions of og. index

        // now finding no. of cycles(permutation cycles) in this graph,(every component in this graph has a cycle)
        boolean[] visited = new boolean[n];
        int cycles = 0;
        for(int i = 0;i < n;i++){
            if(!visited[i]){
                visited[i] = true;
                // traverse on all adjacent nodes
                int j = indices[i];
                while(!visited[j]){
                    visited[j] = true;
                    j = indices[j];
                }
                // completed the traversal of one cycle/component
                cycles++;
            }
        } 

        return n - cycles;
    }
}