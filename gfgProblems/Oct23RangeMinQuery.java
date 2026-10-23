import java.util.*;
class Solution {
	int n;
	private void buildTree(int i, int[] arr, int[] segTree, int l, int r) {
		if (l == r) {
			// leaf node
			segTree[i] = arr[l];
			return;
		}
		
		int mid = l + (r - l)/2;
		
		buildTree(2*i + 1, arr, segTree, l, mid);
		buildTree(2*i + 2, arr, segTree, mid + 1, r);
		
		segTree[i] = Math.min(segTree[2*i + 1], segTree[2*i + 2]);
	}
	private int query(int i, int[] segTree, int s, int e, int l, int r) {
		if (l > e || r < s) {
			// out-of-bounds
			return Integer.MAX_VALUE;
		}
		
		if (l >= s && r <= e) {
			// fully overlapping
			return segTree[i];
		}
		
		// partially overlapping
		int mid = l + (r - l)/2;
		int leftMin = query(2*i + 1, segTree, s, e, l, mid);
		int rightMin = query(2*i + 2, segTree, s, e, mid + 1, r);
		return Math.min(leftMin, rightMin);
	}
	public ArrayList<Integer> rangeMinQuery(int[] arr, int[][] queries) {
		// using segment tree
        // TC : O(n) + O(qlogn) where q is no. of queries
        // SC : O(n + q)
		n = arr.length;
		int[] segTree = new int[4*n];
		
		buildTree(0, arr, segTree, 0, n - 1);
		
		ArrayList<Integer> res = new ArrayList<>();
		for (int[] q : queries) {
			int s = q[0];
			int e = q[1];
			
			res.add(query(0, segTree, s, e, 0, n - 1));
		}
		
		return res;
	}
}