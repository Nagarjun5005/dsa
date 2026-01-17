package stack.medium;

import java.util.Stack;

/*
 =============================================================================
 PROBLEM:
 Sum of Subarray Maximums

 Given an integer array arr, find the sum of the MAXIMUM value of every
 possible subarray. Since the answer can be large, return it modulo 1e9 + 7.

 Example:
 Input:  arr = [3, 1, 2, 4]

 Subarrays and their maximums:
 [3]         -> 3
 [3,1]       -> 3
 [3,1,2]     -> 3
 [3,1,2,4]   -> 4
 [1]         -> 1
 [1,2]       -> 2
 [1,2,4]     -> 4
 [2]         -> 2
 [2,4]       -> 4
 [4]         -> 4

 Sum = 30
 =============================================================================
*/

public class SumoFSubarrayMaximums {

    /*
     =========================================================================
     APPROACH 1: BRUTE FORCE (BASELINE SOLUTION)

     IDEA:
     - Generate all possible subarrays
     - For each subarray, keep track of the maximum element
     - Add all maximums

     WHY THIS IS BRUTE FORCE:
     - Number of subarrays = O(n²)
     - Maximum is updated inside the inner loop
     - Overall time complexity = O(n²)

     This approach is mainly used to understand the problem.
     =========================================================================
    */
    public static int sumSubarrayMaxBruteForce(int[] arr) {

        int n = arr.length;
        int MOD = 1_000_000_007;
        long sum = 0;

        for (int i = 0; i < n; i++) {
            int max = arr[i];
            for (int j = i; j < n; j++) {
                max = Math.max(max, arr[j]);
                sum = (sum + max) % MOD;
            }
        }
        return (int) sum;
    }

    /*
     =========================================================================
     APPROACH 2: OPTIMAL SOLUTION
     (MONOTONIC STACK + CONTRIBUTION TECHNIQUE)

     CORE IDEA:
     Instead of calculating the maximum for every subarray,
     we calculate how many subarrays each element is the MAXIMUM of.

     For each element arr[i]:
       contribution = arr[i] × (number of subarrays where arr[i] is maximum)

     -------------------------------------------------------------------------
     HOW DO WE COUNT THESE SUBARRAYS?

     For each index i:
     - left[i]  = number of ways to extend to the LEFT while arr[i]
                  remains the maximum
     - right[i] = number of ways to extend to the RIGHT while arr[i]
                  remains the maximum

     Total subarrays where arr[i] is maximum:
         left[i] × right[i]

     Contribution of arr[i]:
         arr[i] × left[i] × right[i]

     -------------------------------------------------------------------------
     WHY MONOTONIC STACK?

     To compute left[] and right[] efficiently, we need:
     - Previous Greater Element (PGE)
     - Next Greater or Equal Element (NGE)

     We use a DECREASING monotonic stack because:
     - Smaller elements cannot block a larger element
     - Larger elements define the boundaries

     -------------------------------------------------------------------------
     IMPORTANT DIFFERENCE FROM SUBARRAY MINIMUMS:

     MINIMUMS:
       - Increasing stack
       - Left pop:  >=
       - Right pop: >

     MAXIMUMS (THIS CODE):
       - Decreasing stack
       - Left pop:  <=
       - Right pop: <

     -------------------------------------------------------------------------
     TIME COMPLEXITY:
     - O(n)

     SPACE COMPLEXITY:
     - O(n)
     =========================================================================
    */
    public static int sumSubarrayMaximums(int[] arr) {

        int n = arr.length;
        int MOD = 1_000_000_007;

        // left[i] = number of choices to extend LEFT
        // such that arr[i] remains the maximum
        int[] left = new int[n];

        // right[i] = number of choices to extend RIGHT
        // such that arr[i] remains the maximum
        int[] right = new int[n];

        Stack<Integer> stack = new Stack<>();

        /*
         ---------------------------------------------------------------
         STEP 1: Compute left[] using Previous Greater Element (PGE)

         Traverse left → right
         Maintain a DECREASING stack

         Pop while stack.top <= arr[i]
         ---------------------------------------------------------------
        */
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] <= arr[i]) {
                stack.pop();
            }
            left[i] = stack.isEmpty() ? i + 1 : i - stack.peek();
            stack.push(i);
        }

        stack.clear();

        /*
         ---------------------------------------------------------------
         STEP 2: Compute right[] using Next Greater or Equal Element (NGE)

         Traverse right → left
         Maintain a DECREASING stack

         Pop while stack.top < arr[i]
         ---------------------------------------------------------------
        */
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && arr[stack.peek()] < arr[i]) {
                stack.pop();
            }
            right[i] = stack.isEmpty() ? n - i : stack.peek() - i;
            stack.push(i);
        }

        /*
         ---------------------------------------------------------------
         STEP 3: Sum contributions of each element
         ---------------------------------------------------------------
        */
        long result = 0;
        for (int i = 0; i < n; i++) {
            result = (result + (long) arr[i] * left[i] * right[i]) % MOD;
        }

        return (int) result;
    }

    /*
     =========================================================================
     MAIN METHOD (TESTING)
     =========================================================================
    */
    public static void main(String[] args) {

        int[] arr = {3, 1, 2, 4};

        System.out.println("Brute Force (Maximums):");
        System.out.println(sumSubarrayMaxBruteForce(arr));

        System.out.println("\nOptimized (Maximums - Monotonic Stack):");
        System.out.println(sumSubarrayMaximums(arr));
    }
}
