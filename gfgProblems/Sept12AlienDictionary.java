import java.util.*;
import java.util.Queue;
class Solution {
	public String findOrder(String[] words) {
	    
	    // TC : O(C + V + E)
	    // SC : O(V + E)
	    // where C = total no. of chars accross all words 
	    // V = no. of unique characters
	    // E = no. of unique adjacency relationships(edges) built b/w chars
	    
		// store the directed graph using hashMap
		Map<Character, List<Character>> adjMap = new HashMap<>();
		Map<Character, Integer> indegree = new HashMap<>();
		
		// initalize every unique node with indegree 0 and no adj. nodes 
		for (String word : words) {
			for (char ch : word.toCharArray()) {
				indegree.putIfAbsent(ch, 0);
				adjMap.putIfAbsent(ch, new ArrayList<>());
			}
		}
		
		// building graph 
		int n = words.length;
		for (int k = 0; k < n - 1; k++) {
			String s1 = words[k];
			String s2 = words[k + 1];
			
			// check invalid prefix case (eg. ["abc","ab"])
			if(s1.length() > s2.length() && s1.startsWith(s2)){
			    return ""; 
			}
			
			int len = Math.min(s1.length(), s2.length());
			
			for (int i = 0; i < len; i++) {
				char c1 = s1.charAt(i);
				char c2 = s2.charAt(i);
				if (c1 != c2) {
					
					adjMap.get(c1).add(c2);
					indegree.put(c2, indegree.get(c2) + 1);
					
					break;
				}
			}
		}
		
		StringBuilder sb = new StringBuilder(); // stores order
		// using TopoSort for finding the order
		Queue<Character> que = new LinkedList<>();
		
		for (char key : indegree.keySet()) {
			if (indegree.get(key) == 0) {
				que.add(key);
			}
		}
		
		while (!que.isEmpty()) {
			char node = que.poll();
			sb.append(node);
			
			for (char adjNode : adjMap.get(node)) {
				indegree.put(adjNode, indegree.get(adjNode) - 1);
				if (indegree.get(adjNode) == 0) {
					que.add(adjNode);
				}
			}
		}
		
		// cyclic dependency means not getting all characters in toposort
		if(sb.length() < indegree.size()){
		    return ""; // cycle means no correct order
		}
		
		return sb.toString();
	}
}
