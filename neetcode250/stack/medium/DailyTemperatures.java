package stack.medium;

import java.util.Arrays;
import java.util.Stack;

/**
 * Problem: 739. Daily Temperatures
 *
 * Given an array of daily temperatures, return an array such that:
 * for each day i, tells you how many days you have to wait until
 * a warmer temperature.
 *
 * If there is no future day for which this is possible, put 0 instead.
 *
 * This class provides:
 * 1. Brute force solution (O(n²))
 * 2. Optimal stack-based solution using a Monotonic Decreasing Stack (O(n))
 */
public class DailyTemperatures {

    public static void main(String[] args) {

        int[] temperatures = {73, 74, 75, 71, 69, 72, 76, 73};

        int[] brute = dailyTemperaturesBrute(temperatures);
        System.out.println(Arrays.toString(brute));

        int[] stack = dailyTemperaturesStack(temperatures);
        System.out.println(Arrays.toString(stack));
    }

    /**
     * Brute Force Approach
     *
     * For each day, scan forward to find the first day
     * with a higher temperature.
     *
     * Counting Logic:
     * - When a warmer day is found at index j,
     *   number of days waited = j - i
     *
     * Time Complexity: O(n²)
     * Space Complexity: O(1)
     *
     * @param temperatures input array of temperatures
     * @return array where each index stores days to wait for a warmer temperature
     */
    public static int[] dailyTemperaturesBrute(int[] temperatures) {

        int[] result = new int[temperatures.length];

        for (int i = 0; i < temperatures.length; i++) {

            result[i] = 0; // default if no warmer day exists

            for (int j = i + 1; j < temperatures.length; j++) {
                if (temperatures[j] > temperatures[i]) {
                    result[i] = j - i; // distance between indices
                    break;
                }
            }
        }
        return result;
    }

    /**
     * Optimal Stack-Based Approach
     *
     * Uses a Monotonic Decreasing Stack.
     *
     * Stack stores INDICES of days whose warmer temperature
     * has not yet been found.
     *
     * Idea:
     * - Traverse from left to right
     * - While current temperature is greater than the temperature
     *   at the index on top of the stack:
     *      - Pop that index
     *      - Compute days waited = currentIndex - poppedIndex
     *
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     *
     * @param temperatures input array of temperatures
     * @return array where each index stores days to wait for a warmer temperature
     */
    public static int[] dailyTemperaturesStack(int[] temperatures) {

        Stack<Integer> stack = new Stack<>();
        int[] warmerDays = new int[temperatures.length];

        for (int i = 0; i < temperatures.length; i++) {

            /*
             * While current temperature is higher than the temperature
             * of the day at the top index of the stack, we have found
             * the next warmer day for that previous index.
             */
            while (!stack.isEmpty() &&
                    temperatures[i] > temperatures[stack.peek()]) {

                int prevIndex = stack.pop();
                warmerDays[prevIndex] = i - prevIndex;
            }

            /*
             * Push current day index onto stack.
             * This day is now waiting for a warmer temperature.
             */
            stack.push(i);
        }

        /*
         * Remaining indices in stack have no warmer day ahead.
         * Their values remain 0 by default.
         */
        return warmerDays;
    }
}
