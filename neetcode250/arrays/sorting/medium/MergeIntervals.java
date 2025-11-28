package arrays.sorting.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * MERGE INTERVALS (Brute Force + Optimal)
 * ---------------------------------------
 *
 * Given a list of intervals, merge all overlapping intervals.
 *
 * Example:
 * Input:
 *   [[1,4], [2,5], [7,9], [3,6], [10,12], [8,10],
 *    [15,18], [16,17], [20,25], [24,27], [30,31],
 *    [29,30], [100,200], [150,300]]
 *
 * Output:
 *   [[1,6], [7,12], [15,18], [20,27], [29,31], [100,300]]
 *
 *
 * We implement TWO approaches:
 *
 * 1️⃣ Brute Force (O(n²))
 * 2️⃣ Optimal Sort + Merge (O(n log n))
 *
 */
public class MergeIntervals {

    public static void main(String[] args) {

        // Input intervals (unsorted)
        int[][] intervals = {
                {1, 4},
                {2, 5},
                {7, 9},
                {3, 6},
                {10, 12},
                {8, 10},
                {15, 18},
                {16, 17},
                {20, 25},
                {24, 27},
                {30, 31},
                {29, 30},
                {100, 200},
                {150, 300}
        };

        // Brute Force Result
        int[][] bruteMerged = mergeIntervalsBrute(intervals);
        System.out.println("BRUTE FORCE RESULT:");
        for (int[] interval : bruteMerged) {
            System.out.println(Arrays.toString(interval));
        }

        System.out.println("==================================================");

        // Optimal Result
        int[][] optimalMerged = mergeIntervalsOptimal(intervals);
        System.out.println("OPTIMAL RESULT:");
        for (int[] interval : optimalMerged) {
            System.out.println(Arrays.toString(interval));
        }
    }


    /**
     * =====================================================
     *  BRUTE FORCE MERGE INTERVALS
     * =====================================================
     *
     * ALGORITHM:
     * 1. Sort intervals by start time
     * 2. Use a boolean[] to mark intervals that were merged
     * 3. For each interval i:
     *      - Skip if already merged
     *      - Treat it as current merged interval
     *      - Compare with every interval j > i
     *      - If intervals overlap:
     *            merge them and mark j as merged
     *      - Add merged interval to result
     *
     * OVERLAP CONDITION:
     *   Two intervals [s1, e1], [s2, e2] overlap if:
     *        e1 >= s2
     *
     * TIME COMPLEXITY:
     *      O(n²)  — nested loops
     *
     * SPACE COMPLEXITY:
     *      O(n)   — result + visited array
     */
    public static int[][] mergeIntervalsBrute(int[][] intervals) {

        int n = intervals.length;

        // Step 1: Sort by start time
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        List<int[]> result = new ArrayList<>();
        boolean[] merged = new boolean[n];   // tracks intervals that are included already

        // Step 2: Iterate over all intervals
        for (int i = 0; i < n; i++) {

            // Skip intervals already merged
            if (merged[i]) continue;

            int start = intervals[i][0];
            int end   = intervals[i][1];

            // Step 3: Compare with intervals ahead of i
            for (int j = i + 1; j < n; j++) {

                // If intervals overlap: merge them
                if (end >= intervals[j][0]) {
                    start = Math.min(start, intervals[j][0]);
                    end   = Math.max(end, intervals[j][1]);
                    merged[j] = true; // mark j as merged
                }
            }

            // Step 4: Add merged interval
            result.add(new int[]{start, end});
        }

        // Convert List<int[]> → int[][]
        return result.toArray(new int[result.size()][]);
    }


    /**
     * =====================================================
     *  OPTIMAL MERGE INTERVALS — O(n log n)
     * =====================================================
     *
     * ALGORITHM:
     * 1. Sort intervals by starting time
     * 2. Traverse the array once:
     *      - If result is empty OR no overlap:
     *          simply add the interval
     *      - Else (overlap):
     *          merge with the last interval in result
     *
     * OVERLAP CONDITION:
     *   Let last interval in result = [lastStart, lastEnd]
     *   Let current interval       = [currStart, currEnd]
     *
     *   They overlap if:
     *        currStart <= lastEnd
     *
     * Merging rule:
     *        lastEnd = max(lastEnd, currEnd)
     *
     * TIME COMPLEXITY:
     *    Sorting   → O(n log n)
     *    One-pass  → O(n)
     *
     *    Total     → O(n log n)
     *
     * SPACE COMPLEXITY:
     *    O(n)  for result
     */
    public static int[][] mergeIntervalsOptimal(int[][] intervals) {

        // Mandatory: sort by starting time
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        List<int[]> result = new ArrayList<>();

        for (int[] interval : intervals) {

            // Case 1: result empty OR no overlap → add interval
            if (result.isEmpty() || result.getLast()[1] < interval[0]) {
                result.add(interval);
            }
            // Case 2: overlap → merge with the last interval
            else {
                result.getLast()[1] =
                        Math.max(result.getLast()[1], interval[1]);
            }
        }

        return result.toArray(new int[result.size()][]);
    }
}
