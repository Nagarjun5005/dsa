package stack.medium;

import java.util.*;

/*
 =============================================================================
 PROBLEM:
 Sum of Subarray Minimums (LeetCode 907)

 Given an integer array arr, find the sum of the minimum value of every
 possible subarray. Since the answer can be large, return it modulo 1e9 + 7.

 Example:
 Input:  arr = [3, 1, 2, 4]
 Output: 17

 Explanation:
 Subarrays and their minimums:
 [3]         -> 3
 [3,1]       -> 1
 [3,1,2]     -> 1
 [3,1,2,4]   -> 1
 [1]         -> 1
 [1,2]       -> 1
 [1,2,4]     -> 1
 [2]         -> 2
 [2,4]       -> 2
 [4]         -> 4

 Sum = 17
 =============================================================================
*/

public class SumoFSubarrayMinimums {

    /*
     =========================================================================
     APPROACH 1: BRUTE FORCE (BASELINE SOLUTION)

     IDEA:
     - Generate all possible subarrays
     - For each subarray, find its minimum
     - Add all minimums

     WHY THIS IS BRUTE FORCE:
     - Number of subarrays is O(n²)
     - Finding minimum inside each subarray is O(n)
     - Total time complexity becomes O(n³)

     This approach is NOT efficient, but it is very important because:
     - It explains what the problem is really asking
     - The optimized solution is derived from this logic

     TIME COMPLEXITY:
     - O(n³)

     SPACE COMPLEXITY:
     - O(1)
     =========================================================================
    */
    public static int sumSubarrayMinsBruteForce(int[] arr) {

        int n = arr.length;
        int MOD = 1_000_000_007;
        long sum = 0;

        // Generate all subarrays
       for(int i=0;i<n;i++){
           int min=arr[i];
           for(int j=i;j<n;j++){
               min=Math.min(min,arr[j]);
               sum=(sum+min)%MOD;
           }

       }
        return (int) sum;
    }

    /*
     =========================================================================
     APPROACH 2: OPTIMAL SOLUTION (MONOTONIC STACK + CONTRIBUTION TECHNIQUE)

     CORE IDEA (VERY IMPORTANT):
     Instead of finding the minimum for every subarray,
     we ask the reverse question:

     "For how many subarrays is arr[i] the minimum?"

     If arr[i] is the minimum in X subarrays,
     then its total contribution is:
         arr[i] * X

     So the final answer is:
         sum of (arr[i] * number of subarrays where arr[i] is minimum)

     -------------------------------------------------------------------------
     HOW DO WE COUNT THESE SUBARRAYS?

     For each index i:
     - We find how far we can extend to the LEFT while arr[i] remains minimum
     - We find how far we can extend to the RIGHT while arr[i] remains minimum

     These two counts are stored in:
     - left[i]
     - right[i]

     left[i]:
       Number of ways to choose a starting index on the LEFT such that
       arr[i] is still the minimum.

     right[i]:
       Number of ways to choose an ending index on the RIGHT such that
       arr[i] is still the minimum.

     Total subarrays where arr[i] is minimum:
         left[i] * right[i]

     Contribution of arr[i]:
         arr[i] * left[i] * right[i]

     -------------------------------------------------------------------------
     WHY MONOTONIC STACK?

     To compute left[] and right[] efficiently in O(n),
     we need to find:
     - Previous Smaller Element (PSE)
     - Next Smaller or Equal Element (NSE)

     Monotonic increasing stacks allow us to do this efficiently.

     -------------------------------------------------------------------------
     TIME COMPLEXITY:
     - O(n)

     SPACE COMPLEXITY:
     - O(n)
     =========================================================================
    */
    public static int sumSubarrayMins(int[] arr) {

        int n = arr.length;
        int MOD = 1_000_000_007;

        // left[i]  = number of elements we can extend to the left
        //             such that arr[i] is the minimum
        int[] left = new int[n];

        // right[i] = number of elements we can extend to the right
        //             such that arr[i] is the minimum
        int[] right = new int[n];

        Stack<Integer> stack = new Stack<>();

        /*
         ---------------------------------------------------------------
         STEP 1: Compute left[] using Previous Smaller Element

         We traverse from left to right.
         We maintain an INCREASING stack.

         Pop while stack.top >= arr[i]
         After popping:
           - if stack is empty, no smaller element on the left
           - else stack.top is the previous smaller element

         left[i] is the distance to that previous smaller element.
         ---------------------------------------------------------------
        */
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                stack.pop();
            }
            left[i] = stack.isEmpty() ? i + 1 : i - stack.peek();
            stack.push(i);
        }

        stack.clear();

        /*
         ---------------------------------------------------------------
         STEP 2: Compute right[] using Next Smaller or Equal Element

         We traverse from right to left.
         We maintain an INCREASING stack.

         Pop while stack.top > arr[i]
         (Notice the strict '>' to avoid double counting duplicates)

         right[i] is the distance to the next smaller or equal element.
         ---------------------------------------------------------------
        */
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                stack.pop();
            }
            right[i] = stack.isEmpty() ? n - i : stack.peek() - i;
            stack.push(i);
        }

        /*
         ---------------------------------------------------------------
         STEP 3: Compute the final answer using contributions
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
     MAIN METHOD (TESTING BOTH APPROACHES)
     =========================================================================
    */
    public static void main(String[] args) {

        int[] arr = {3, 1, 2, 4};

        System.out.println("Brute Force Result:");
        System.out.println(sumSubarrayMinsBruteForce(arr));

        System.out.println("\nOptimized (Monotonic Stack) Result:");
        System.out.println(sumSubarrayMins(arr));
    }
}
