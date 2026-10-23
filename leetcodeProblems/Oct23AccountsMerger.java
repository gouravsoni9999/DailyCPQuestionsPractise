import java.util.*;

class DisjointSet {
    int[] parent, rank;

    DisjointSet(int n) {
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    int find(int node) {
        // find ultimate parent of node
        if (parent[node] == node) {
            return node;
        }

        return parent[node] = find(parent[node]);
    }

    void union(int u, int v) {
        int parent_u = find(u);
        int parent_v = find(v);
        if (parent_u != parent_v) {
            // different component
            // union them
            if (rank[parent_u] < rank[parent_v]) {
                // union in v
                parent[parent_u] = parent_v;
            } else if (rank[parent_v] < rank[parent_u]) {
                parent[parent_v] = parent_u;
            } else {
                // both are same
                parent[parent_u] = parent_v;
                rank[parent_v]++;
            }
        }
    }

}

class Solution {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        // Let N be the number of accounts and K be the maximum number of emails in an
        // account.
        // TC :O(N.KlogK) where KlogK due to sorting
        // SC : O(N)
        int n = accounts.size();
        Map<String, Integer> mapMailToNode = new HashMap<>();
        DisjointSet ds = new DisjointSet(n);
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < accounts.get(i).size(); j++) {
                String mail = accounts.get(i).get(j);
                if (!mapMailToNode.containsKey(mail)) {
                    // new mail, simply put in map
                    mapMailToNode.put(mail, i);
                } else {
                    // mail already exist, union both nodes
                    int u = i;
                    int v = mapMailToNode.get(mail);
                    ds.union(u, v);
                }
            }
        }

        // create a map, containing node -> mails mapping
        Map<Integer, List<String>> mapNodeToMails = new HashMap<>();
        for (int node = 0; node < n; node++) {
            mapNodeToMails.put(node, new ArrayList<>());
        }

        for (String mail : mapMailToNode.keySet()) {
            int node = mapMailToNode.get(mail);
            int parentNode = ds.find(node);

            mapNodeToMails.get(parentNode).add(mail);
        }

        List<List<String>> res = new ArrayList<>();

        for (int node = 0; node < n; node++) {
            // sort every list in hashMap
            List<String> list = mapNodeToMails.get(node);
            if (list.isEmpty()) {
                continue;
            }
            Collections.sort(list);
            list.add(0, accounts.get(node).get(0));
            res.add(list);
        }

        return res;
    }
}