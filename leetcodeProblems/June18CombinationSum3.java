class Solution {
    List<List<Integer>> res;
    List<Integer> list;
    private void solve(int i,int k,int n){
        
        if(n == 0){
            if(k == 0){
                res.add(new ArrayList<>(list));
            }
            return;
        }

        if(n < 0 || k < 0 || i > 9){
            return;
        }

        // take i
        list.add(i);
        solve(i+1, k-1, n-i);

        list.remove(list.size()-1); // backtrack

        // not take i
        solve(i+1, k, n);
    }
    public List<List<Integer>> combinationSum3(int k, int n) {
        /*
            using recursion + backtracking
            TC : O(2^9) // either take a digit or not
            SC : O(k) // 9 recursion depth(at max) and O(k) for making a list
        */
        res = new ArrayList<>();
        list = new ArrayList<>();
        solve(1, k, n);
        return res;
    }
}
