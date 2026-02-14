package binarySearch.medium;

/**
 * File Name: CountOccurrence1.java
 *
 * Problem:
 * Given a sorted array of integers, count the number of
 * occurrences of a given target.
 *
 * Approach:
 * We reuse the FirstAndLastPosition class.
 *
 * 1. Find first and last occurrence using binary search.
 * 2. If target not found → return 0.
 * 3. Else count = (last - first + 1)
 *
 * Time Complexity  : O(log n)
 * Space Complexity : O(1)
 *
 * Why O(log n)?
 * Because searchRange internally uses binary search.
 */

public class CountOccuurrence1 {

    public static void main(String[] args) {

        int arr[] = {5, 7, 7, 8, 8, 10};
        int target = 8;

        int countOccurrences = countOccurrences(arr, target);

        System.out.println("Count of " + target + " = " + countOccurrences);
    }

    /**
     * Returns number of times target appears in sorted array.
     *
     * If target does not exist, returns 0.
     */
    public static int countOccurrences(int[] arr, int k) {

        // Get first and last occurrence
        int[] range = FirstAndLastPosition.searchRange(arr, k);

        // If target not found
        if (range[0] == -1) {
            return 0;
        }

        // Count = lastIndex - firstIndex + 1
        return range[1] - range[0] + 1;
    }
}
