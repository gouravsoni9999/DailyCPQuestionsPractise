class Solution {
    private int calcDayOfYear(String string,int[] daysBefore){
        int month = Integer.parseInt(string.substring(0, 2));
        int date = Integer.parseInt(string.substring(3));

        return daysBefore[month-1] + date;
    }
    public int countDaysTogether(String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {
        // TC : O(5) + O(12) ~ O(1)
        // SC : O(12) ~ O(1)
        int[] daysInMonths = { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
        int[] daysBefore = new int[13];
        for(int i = 1;i < 13;i++){
            daysBefore[i] = daysBefore[i-1] + daysInMonths[i];
        }

        int AliceArrivalDayNo = calcDayOfYear(arriveAlice, daysBefore);
        int AliceDepDayNo = calcDayOfYear(leaveAlice, daysBefore);

        int BobArrivalDayNo = calcDayOfYear(arriveBob, daysBefore);
        int BobDepDayNo = calcDayOfYear(leaveBob, daysBefore);

        // find no. of common dates
        int intersection_start = Math.max(AliceArrivalDayNo, BobArrivalDayNo);
        int intersection_end = Math.min(AliceDepDayNo, BobDepDayNo);

        if(intersection_start <= intersection_end){
            // overlapping intervals
            return intersection_end - intersection_start + 1;
        }

        return 0; // non-overlapping, therefore no common dates
    }
}