/*
given an array : [1,2,1,1,3]
it has breaks = 3

minimize the breaks by converting either 1,2 or 3 into anybody else and find out min. no. of breaks

eg. 1->2
[2,2,2,2,3]
it has breaks = 1 (min. of all)
*/

import java.util.*;

public class Aug25BreaksProblem {

    private static int findMinBreaks(int[] arr, int n) {
        int totalBreaks = 0; // this first finds inital no. of breaks
        Map<String, Integer> map = new HashMap<>(); // stores {"min(x,y)#max(x,y)",cnt} -> adjacent pairs (1,2) and
                                                    // (2,1) are same here and there frequencies are stored

        // last element is not iterated, because arr[i+1] -> out of bounds
        for (int i = 0; i <= n - 2; i++) {
            if (arr[i] != arr[i + 1]) {
                totalBreaks++;
                String key = Math.min(arr[i], arr[i + 1]) + "#" + Math.max(arr[i], arr[i + 1]);
                map.put(key, map.getOrDefault(key, 0) + 1);
            }
        }

        // find that pair which is coming max. times, and we will convert them
        // therefore, the breaks left would be totalBreaks - maxBreak
        int maxBreak = 0;
        for (String key : map.keySet()) {
            maxBreak = Math.max(maxBreak, map.get(key));
        }

        return totalBreaks - maxBreak;

    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);

        int n = scn.nextInt();

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scn.nextInt();
        }

        scn.close();
        System.out.println("Breaks: " + findMinBreaks(arr, n));
    }
}