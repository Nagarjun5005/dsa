package neetcode250.binarySearch.easy;

/**
 * Problem Statement:
 * ------------------
 * Given a non-negative integer 'x', return the square root of x rounded down to
 * the nearest integer. The returned integer should be the largest integer y
 * such that y * y <= x.
 *
 * Example:
 * --------
 * Input:  x = 38
 * Output: 6  (because 6*6=36 <= 38 but 7*7=49 > 38)
 *
 * You need to implement:
 * 1. Built-in approach using Math.sqrt()
 * 2. Brute-force approach
 * 3. Optimal approach using Binary Search
 *
 * Constraints:
 * ------------
 * 0 <= x <= 2^31 - 1
 */

public class SquareRoot {

    /**
     * Approach 1: Built-in Math.sqrt()
     * --------------------------------
     * Uses Java's built-in Math.sqrt() method to directly get the square root.
     *
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public static double squareBuiltIn(int input) {
        if (input < 0) throw new IllegalArgumentException("Negative numbers not allowed");
        return Math.sqrt(input);
    }

    /**
     * Approach 2: Brute Force
     * -----------------------
     * Steps:
     * 1. Iterate from 1 to input.
     * 2. Keep track of the largest i where i*i <= input.
     * 3. Return i at the end.
     *
     * Time Complexity: O(n)  (where n = input)
     * Space Complexity: O(1)
     */
    public static int squareRootBruteForce(int input) {
        if (input < 0) throw new IllegalArgumentException("Negative numbers not allowed");

        int result = 0;
        for (int i = 1; i * i <= input; i++) {
            result = i;
        }
        return result;
    }

    /**
     * Approach 3: Optimal Binary Search
     * ---------------------------------
     * Steps:
     * 1. Initialize left = 0 and right = input.
     * 2. While left <= right:
     *    a) Find mid = (left + right) / 2.
     *    b) If mid*mid <= input, store mid as result and move left = mid + 1.
     *    c) Else move right = mid - 1.
     * 3. Return result at the end.
     *
     * Why this works:
     * Binary Search reduces the search space by half each step,
     * giving O(log n) time complexity.
     *
     * Time Complexity: O(log n)
     * Space Complexity: O(1)
     */
    public static int squareRootOptimal(int input) {
        if (input < 0) throw new IllegalArgumentException("Negative numbers not allowed");

        int left = 0, right = input;
        int result = 0;
        while (left <= right) {
            int mid = left + (right - left) / 2;

            // Use long to prevent integer overflow for mid*mid
            if ((long) mid * mid <= input) {
                result = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println("Built-in square root of 4: " + squareBuiltIn(4));
        System.out.println("Brute-force square root of 52: " + squareRootBruteForce(52));
        System.out.println("Binary search square root of 38: " + squareRootOptimal(38));
    }
}
