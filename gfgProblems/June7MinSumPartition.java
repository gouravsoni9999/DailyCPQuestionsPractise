class Solution {
    int totalSum;
    int n;
    boolean[] prev;
    private void subSetSum(int[] arr){
        prev = new boolean[totalSum+1];
        for(int i = 0;i < n;i++){
            boolean[] curr = new boolean[totalSum+1];
            for(int target = 0;target <= totalSum;target++){
                if(target == 0){
                    curr[target] = true;
                }else if(i == 0){
                    curr[target] = (arr[i] == target);
                }else{
                    boolean take = false;
                    if(target - arr[i] >= 0){
                        take = prev[target-arr[i]];
                    }
                    boolean not_take = prev[target];
                    curr[target] = take | not_take;
                }
            }
            prev = curr;
        }
    }
    public int minDifference(int arr[]) {
        // code here
	// find prev[] array containing that for that array, and target 0 to totalSum, how many valid targets(sum) I can actually achieve
        n = arr.length;
        totalSum = 0;
        for(int num: arr){
            totalSum += num;
        }
        subSetSum(arr);
	// now target is S1 and totalSum-target is S2
	// find min of all
        int minDiff = Integer.MAX_VALUE;
        for(int target = 0;target <= totalSum;target++){
            if(prev[target] == false){
                continue;
            }
            int s1 = target;
            int s2 = totalSum - target;
            minDiff = Math.min(minDiff, Math.abs(s1-s2));
        }
        return minDiff;
    }
}

class Solution {
    int totalSum;
    int n;
    boolean[] prev;
    private void subSetSum(int[] arr){
        prev = new boolean[totalSum+1];
        for(int i = 0;i < n;i++){
            boolean[] curr = new boolean[totalSum+1];
            for(int target = 0;target <= totalSum;target++){
                if(target == 0){
                    curr[target] = true;
                }else if(i == 0){
                    curr[target] = (arr[i] == target);
                }else{
                    boolean take = false;
                    if(target - arr[i] >= 0){
                        take = prev[target-arr[i]];
                    }
                    boolean not_take = prev[target];
                    curr[target] = take | not_take;
                }
            }
            prev = curr;
        }
    }
    public int minDifference(int arr[]) {
        // code here
	// TC : O(n * totalSum)
	// SC : O(totalSum)
        n = arr.length;
        totalSum = 0;
        for(int num: arr){
            totalSum += num;
        }
        subSetSum(arr);
        int minDiff = Integer.MAX_VALUE;
	// slightly optimized as there are some duplications in the problem
	// therefore take only half
        for(int target = 0;target <= totalSum/2;target++){
            if(prev[target] == false){
                continue;
            }
            int s1 = target;
            int s2 = totalSum - target;
            minDiff = Math.min(minDiff, Math.abs(s1-s2));
        }
        return minDiff;
    }
}

