package arrays.twoPointers.medium;

import java.util.Arrays;

/**
 * TWO SUM II – Input Array Is Sorted
 * =================================
 *
 * Problem Statement:
 * ------------------
 * Given a 1-indexed array of integers `numbers` that is already sorted
 * in non-decreasing order, find two numbers such that they add up to
 * a specific target number.
 *
 * Return the indices of the two numbers (1-based indexing).
 *
 * Constraints:
 * ------------
 * - Exactly one solution exists.
 * - You may not use the same element twice.
 * - Array is already sorted.
 *
 *
 * Example:
 * --------
 * Input:
 *   numbers = [2, 7, 11, 15]
 *   target = 9
 *
 * Output:
 *   [1, 2]
 *
 *
 * APPROACHES:
 * -----------
 * 1. Brute Force (Nested Loops)
 * 2. Optimized Two Pointers (Opposite-Direction / Converging Pointers)
 *
 *
 * PATTERN:
 * --------
 * Opposite-Direction Two Pointers
 *
 * @author Charu
 */
public class TwoSumInputSorted {

    public static void main(String[] args) {

        int[] numbers = {2, 7, 11, 15};
        int target = 9;

        // Brute force solution
        int[] bruteResult = twoSumBruteForce(numbers, target);
        System.out.println("Brute Force Result: " + Arrays.toString(bruteResult));

        // Two-pointer optimized solution
        int[] twoPointerResult = twoSumTwoPointers(numbers, target);
        System.out.println("Two Pointer Result: " + Arrays.toString(twoPointerResult));
    }

    /**
     * APPROACH 1: Brute Force
     *
     * Idea:
     * -----
     * - Try all possible pairs using two nested loops.
     * - Check if numbers[i] + numbers[j] == target.
     *
     * Time Complexity:
     * ----------------
     * O(n²)
     *
     * Space Complexity:
     * -----------------
     * O(1)
     *
     * @param numbers sorted input array
     * @param target  target sum
     * @return indices (1-based) of the two numbers
     */
    public static int[] twoSumBruteForce(int[] numbers, int target) {

        for (int i = 0; i < numbers.length; i++) {

            for (int j = i + 1; j < numbers.length; j++) {

                if (numbers[i] + numbers[j] == target) {
                    // +1 because problem uses 1-based indexing
                    return new int[]{i + 1, j + 1};
                }
            }
        }

        // According to problem statement, this should never happen
        return new int[]{-1, -1};
    }

    /**
     * APPROACH 2: Two Pointers (Optimized)
     *
     * Pattern:
     * --------
     * Opposite-Direction / Converging Two Pointers
     *
     * Idea:
     * -----
     * - Since the array is sorted:
     *     - Use one pointer at the start (smallest value)
     *     - Use one pointer at the end (largest value)
     *
     * - Compute the sum of the two values:
     *     - If sum == target → answer found
     *     - If sum > target  → move end pointer left
     *     - If sum < target  → move start pointer right
     *
     * Why it works:
     * -------------
     * Sorting gives a guaranteed direction to move pointers
     * without missing the correct pair.
     *
     * Time Complexity:
     * ----------------
     * O(n)
     *
     * Space Complexity:
     * -----------------
     * O(1)
     *
     * @param numbers sorted input array
     * @param target  target sum
     * @return indices (1-based) of the two numbers
     */
    public static int[] twoSumTwoPointers(int[] numbers, int target) {

        int start = 0;
        int end = numbers.length - 1;

        // Move pointers towards each other
        while (start < end) {

            int sum = numbers[start] + numbers[end];

            if (sum == target) {
                return new int[]{start + 1, end + 1};
            }
            else if (sum > target) {
                end--;      // need a smaller sum
            }
            else {
                start++;    // need a larger sum
            }
        }

        // According to problem statement, this should never happen
        return new int[]{-1, -1};
    }
}

