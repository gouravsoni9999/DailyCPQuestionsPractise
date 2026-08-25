import java.util.Scanner;

/**
 * ============================================================================
 * PROBLEM: Task Scheduler with Sliding Window Cooldown
 * ============================================================================
 * 
 * DESCRIPTION:
 * You are given an array `cnt` where `cnt[i]` represents the total execution
 * time required for Task `i`.
 * 
 * You are also given two parameters:
 * - W: The size of a continuous sliding time window.
 * - K: The maximum number of time units ANY single task is allowed to run
 * within any continuous sliding window of size W.
 * 
 * RULES & CONSTRAINTS:
 * 1. Single CPU: The system can execute at most ONE task at any time unit (or
 * stay idle).
 * 2. Preemption: Tasks can be interrupted, interleaved, and resumed freely.
 * 3. Independent Rate Limit: The rule (at most K units in any W window) applies
 * to each task independently. Different tasks do not restrict each other.
 * 
 * GOAL:
 * Find the minimum total time units needed to complete all tasks.
 * 
 * EXAMPLES:
 * ----------------------------------------------------------------------------
 * Example 1:
 * Input: cnt = [10, 2, 2], W = 10, K = 3
 * Output: 31
 * Explanation:
 * - Task 0 requires 10 units:
 * Runs 3 units -> Cooldown until time 10 -> Runs 3 units -> Cooldown -> ...
 * - Total span required just for Task 0 alone is 31 units.
 * - Tasks 1 and 2 can easily fill the idle gaps without increasing total time.
 * 
 * Example 2:
 * Input: cnt = [5], W = 5, K = 2
 * Output: 11
 * Explanation:
 * - Time [0..1]: Task 0 runs (2 units)
 * - Time [2..4]: Cooldown / Idle (3 units)
 * - Time [5..6]: Task 0 runs (2 units)
 * - Time [7..9]: Cooldown / Idle (3 units)
 * - Time [10]: Task 0 runs (1 unit) -> Finished at index 10 (Total = 11 units)
 * 
 * Example 3:
 * Input: cnt = [4, 4], W = 3, K = 2
 * Output: 8
 * Explanation:
 * - Both tasks interleave perfectly without idle time:
 * T1, T1, T2, T2, T1, T1, T2, T2 (Total = 8 units)
 * ============================================================================
 */
public class Aug25TaskSchedulerWithCooldown {
    private static long findTime(int[] cnt, int D, int W, int K) {
        long totalSum = 0;
        long maxBlocks = 0;

        // find totalSum and maxBlocks
        for (int c : cnt) {
            totalSum += c;

            long blocks = (c - 1) / K;

            if (blocks > maxBlocks) {
                maxBlocks = blocks;
            }
        }

        // Sum the tail pieces(remaining pieces) of the most demanding tasks
        long tail = 0;

        for (int c : cnt) {
            long blocks = (c - 1) / K;
            if (blocks == maxBlocks) {
                tail += ((c - 1) % K) + 1;
            }
        }

        // result is whichever is larger
        long frameTime = maxBlocks * W + tail;
        return Math.max(frameTime, totalSum);

    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int D = scn.nextInt();
        int W = scn.nextInt();
        int K = scn.nextInt();

        int[] cnt = new int[D];
        for (int i = 0; i < D; i++) {
            cnt[i] = scn.nextInt();
        }

        scn.close();
        System.out.println("Time taken: " + findTime(cnt, D, W, K));
    }
}
