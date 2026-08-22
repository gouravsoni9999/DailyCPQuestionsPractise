package implementationProblems;

import java.util.*;

public class Aug21AdjacencyListGraphRep {
    public static void main(String[] args) {
        // SC : O(2E) -> in case of undirected graph and O(E) in case of directed graph
        // TC : O(n) + O(m)
        int n, m;
        Scanner scn = new Scanner(System.in);
        n = scn.nextInt();
        m = scn.nextInt();
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            int u = scn.nextInt();
            int v = scn.nextInt();
            adj.get(u).add(v);
            adj.get(v).add(u);
        }
        System.out.println(adj.toString());
    }
}
