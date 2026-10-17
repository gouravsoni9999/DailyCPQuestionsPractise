class Solution {
    public String maxSumOfSquares(int num, int sum) {
        if(sum > 9 * num){
            // not possible 
            return "";
        }

        // find out how many nines will be required in this sum
        int nines = sum/9;

        // find out remaining sum left after all nines
        int rem = sum % 9;

        StringBuilder sb = new StringBuilder();

        // first, put all 9s in ans
        while(nines-- > 0){
            sb.append('9');
        }

        if(rem > 0){
            // still sum is left
            sb.append((char) ('0' + rem));
        }

        int zeroes = num - sb.length(); // find out how many zeroes should I append now
        while(zeroes-- > 0){
            sb.append('0');
        }

        return sb.toString();
    }
}