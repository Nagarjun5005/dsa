package stack.medium;

import java.util.Stack;

/*
 =============================================================================
 PROBLEM:
 Sum of Subarray Ranges (LeetCode 2104)

 Given an integer array nums, the RANGE of a subarray is defined as:
     (maximum element - minimum element)

 The task is to return the SUM of ranges of all possible subarrays.

 -------------------------------------------------------------------------
 EXAMPLE:
 nums = [1, 4, 3, 2]

 Subarrays and their ranges:
 [1]         -> 0
 [1,4]       -> 3
 [1,4,3]     -> 3
 [1,4,3,2]   -> 3
 [4]         -> 0
 [4,3]       -> 1
 [4,3,2]     -> 2
 [3]         -> 0
 [3,2]       -> 1
 [2]         -> 0

 Sum = 13
 =============================================================================
*/

public class SumOfSubarrayRanges {

    /*
     =========================================================================
     MAIN METHOD
     Used to test both brute-force and optimized solutions
     =========================================================================
    */
    public static void main(String[] args) {

        int[] nums = {1, 4, 3, 2};

        long brute = subArrayRangesBrute(nums);
        System.out.println("Brute Force Result: " + brute);

        long optimized = subArrayRangesStack(nums);
        System.out.println("Optimized Stack Result: " + optimized);
    }

    /*
     =========================================================================
     APPROACH 1: BRUTE FORCE

     IDEA:
     - Generate all subarrays
     - Track the maximum and minimum for each subarray
     - Add (max - min) to the sum

     OPTIMIZATION:
     - Instead of recomputing min/max from scratch,
       maintain running max and min as the subarray expands

     TIME COMPLEXITY:
     - O(n²)

     SPACE COMPLEXITY:
     - O(1)
     =========================================================================
    */
    public static long subArrayRangesBrute(int[] nums) {

        int n = nums.length;
        long sum = 0;

        for (int i = 0; i < n; i++) {
            int largest = nums[i];
            int smallest = nums[i];

            for (int j = i + 1; j < n; j++) {
                largest = Math.max(largest, nums[j]);
                smallest = Math.min(smallest, nums[j]);
                sum += (largest - smallest);
            }
        }
        return sum;
    }

    /*
     =========================================================================
     APPROACH 2: OPTIMAL SOLUTION (MONOTONIC STACK)

     KEY INSIGHT:
     Sum of Subarray Ranges can be rewritten as:

         Sum of Subarray Maximums
       - Sum of Subarray Minimums

     Each part is computed independently using the
     "contribution technique".

     TIME COMPLEXITY:
     - O(n)

     SPACE COMPLEXITY:
     - O(n)
     =========================================================================
    */
    public static long subArrayRangesStack(int[] nums) {
        return sumOfSubArrayMaximums(nums) - sumOfSubArrayMinimums(nums);
    }

    /*
     =========================================================================
     SUM OF SUBARRAY MINIMUMS

     CONTRIBUTION IDEA:
     For each element nums[i], determine how many subarrays
     it acts as the MINIMUM for.

     contribution = nums[i] * left[i] * right[i]

     STACK STRATEGY:
     - Monotonic INCREASING stack
     - Left boundary  : Previous Smaller Element
     - Right boundary : Next Smaller Element

     POP CONDITIONS:
     - Left pass  : >=
     - Right pass : >

     TIME COMPLEXITY: O(n)
     SPACE COMPLEXITY: O(n)
     =========================================================================
    */
    private static long sumOfSubArrayMinimums(int[] nums) {

        int n = nums.length;
        int[] left = new int[n];
        int[] right = new int[n];
        Stack<Integer> stack = new Stack<>();

        // Previous Smaller Element (left boundary)
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] >= nums[i]) {
                stack.pop();
            }
            left[i] = stack.isEmpty() ? i + 1 : i - stack.peek();
            stack.push(i);
        }

        stack.clear();

        // Next Smaller Element (right boundary)
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && nums[stack.peek()] > nums[i]) {
                stack.pop();
            }
            right[i] = stack.isEmpty() ? n - i : stack.peek() - i;
            stack.push(i);
        }

        long result = 0;
        for (int i = 0; i < n; i++) {
            result += (long) nums[i] * left[i] * right[i];
        }

        return result;
    }

    /*
     =========================================================================
     SUM OF SUBARRAY MAXIMUMS

     CONTRIBUTION IDEA:
     For each element nums[i], determine how many subarrays
     it acts as the MAXIMUM for.

     contribution = nums[i] * left[i] * right[i]

     STACK STRATEGY:
     - Monotonic DECREASING stack
     - Left boundary  : Previous Greater Element
     - Right boundary : Next Greater Element

     POP CONDITIONS:
     - Left pass  : <=
     - Right pass : <

     TIME COMPLEXITY: O(n)
     SPACE COMPLEXITY: O(n)
     =========================================================================
    */
    private static long sumOfSubArrayMaximums(int[] nums) {

        int n = nums.length;
        int[] left = new int[n];
        int[] right = new int[n];
        Stack<Integer> stack = new Stack<>();

        // Previous Greater Element (left boundary)
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] <= nums[i]) {
                stack.pop();
            }
            left[i] = stack.isEmpty() ? i + 1 : i - stack.peek();
            stack.push(i);
        }

        stack.clear();

        // Next Greater Element (right boundary)
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i]) {
                stack.pop();
            }
            right[i] = stack.isEmpty() ? n - i : stack.peek() - i;
            stack.push(i);
        }

        long result = 0;
        for (int i = 0; i < n; i++) {
            result += (long) nums[i] * left[i] * right[i];
        }

        return result;
    }
}
