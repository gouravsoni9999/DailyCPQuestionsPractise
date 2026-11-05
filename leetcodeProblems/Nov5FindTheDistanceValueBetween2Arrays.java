class LinearSearchSolution {
    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        // TC : O(m*n)
        // SC : O(1)
        int cnt = 0;

        for (int x : arr1) {

            boolean ok = true;

            for (int y : arr2) {
                ok = ok & (x - y) > d;
            }

            cnt += ok ? 1 : 0;
        }

        return cnt;
    }
}