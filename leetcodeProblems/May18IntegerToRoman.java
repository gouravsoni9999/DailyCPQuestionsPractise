class Solution {
    public String intToRoman(int num) {
        // TC : O(13) = O(1)
        // SC : O(13) = O(1)
        StringBuilder ans = new StringBuilder();
        int[] val = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] symbol = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
        int n = val.length;
        for(int i = 0;i < n;i++){
            if(num == 0) break;
            int times = num/val[i];
            while(times-- > 0){
                ans.append(symbol[i]);
            }
            num = num % val[i];
        }
        return ans.toString();
    }
}
