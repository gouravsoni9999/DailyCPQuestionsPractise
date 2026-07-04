class Solution {
	public static int[] count_NGE(int arr[], int indices[]) {
		// TC : O(m*n)
		// SC : O(m)
		int n = arr.length;
		int m = indices.length;
		
		int[] cntNge = new int[m];
		
		for (int j = 0; j < m; j++) {
			
			int idx = indices[j];
			
			for (int i = idx + 1; i < n; i++) {
				if (arr[i] > arr[idx]) {
					cntNge[j]++;
				}
			}
		}
		
		return cntNge;
	}
}

