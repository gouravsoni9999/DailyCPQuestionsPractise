class Solution {
    public int largestAltitude(int[] gain) {
	/*
		using greedy approach
		TC :  O(n)
		SC :  O(n)
	*/
        int maxAltitude = 0;
        int n = gain.length;
        int[] altitude = new int[n+1];
        for(int i = 0;i < n;i++){
            altitude[i+1] = altitude[i] + gain[i];
            maxAltitude = Math.max(maxAltitude, altitude[i+1]);
        }
        return maxAltitude;
    }
}
