class Solution {
    List<List<Integer>> res;
    int n;

    private void solve(int[] candidates, int target, int start, int sum, List<Integer> temp) {
        if (sum > target) {
            return;// backtrack
        }
        if (sum == target) {
            // found 1 ans
            res.add(new ArrayList<>(temp));
            return; // backtrack as one answer is found
        }
        for (int i = start; i < n; i++) {
            // include
            temp.add(candidates[i]);
            // explore
            solve(candidates, target, i, sum + candidates[i], temp);
            // backtrack
            // exclude
            temp.remove(temp.size() - 1);
            // exploration done by for loop after exclusion
        }
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        n = candidates.length;
        res = new ArrayList<>();
        solve(candidates, target, 0, 0, new ArrayList<>());
        return res;
    }
}

class Solution {
    List<List<Integer>> resList = new ArrayList<>();
    int n;
    private void solve(int i,int[] candidates,int target,List<Integer> list){
        if(target < 0 || i == n){
            return;
        }
        if(target == 0){
            resList.add(new ArrayList<>(list));
            return;
        }
        solve(i+1, candidates, target, new ArrayList<>(list)); // not take
        list.add(candidates[i]);
        solve(i, candidates, target - candidates[i], new ArrayList<>(list)); // take
    }
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        /*
            using recursion + backtracking
            TC : O(2^t . k)
            SC : O(t . k)
            where t is max. no. of recursive calls needed to reach target
            and k is the avg. length of a combination
        */
        n = candidates.length;
        solve(0, candidates, target, new ArrayList<>());
        return resList;
    }
}


