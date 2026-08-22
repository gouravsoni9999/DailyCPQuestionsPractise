package implementationProblems;
import java.util.*;
public class Aug21MatrixGraphRepresentation {
    public static void main(String[] args) {
        // SC : O(n^2)
        // TC : O(n^2) + O(m)
        int n,m;
        Scanner scn = new Scanner(System.in);
        n = scn.nextInt(); // no. of nodes
        m = scn.nextInt(); // no. of edges
        
        int[][] adj = new int[n+1][n+1];
        for(int i = 0;i < m;i++){
            // m edges
            int u,v;
            u = scn.nextInt();
            v = scn.nextInt();
            adj[u][v] = 1;
            adj[v][u] = 1;
        }
        scn.close();
        System.out.println(Arrays.deepToString(adj));
    }
}
